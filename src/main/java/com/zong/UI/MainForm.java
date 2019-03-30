package com.zong.UI;

import com.zong.UI.view.PaperCatalog;
import com.zong.UI.view.PaperCatalogItem;
import com.zong.analysis.Analysis;
import com.zong.common.FilePath;
import com.zong.common.QuestionType;
import com.zong.common.QuestionTypeNo;
import com.zong.domain.Paper;
import com.zong.domain.QuestionInfo;
import com.zong.paper.PaperController;
import com.zong.question.QuestionController;
import com.zong.utils.PdfUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainForm {
    private JTextPane questionsTextPane;
    private JButton aButton;
    private JButton dButton;
    private JButton bButton;
    private JButton cButton;
    private JLabel rightLabel;
    private JLabel wrongLabel;
    private JLabel scoreLabel;
    private JLabel scoreValue;
    private JLabel rightValue;
    private JLabel wrongValue;
    private JPanel MainFromPanel;
    private JScrollPane ScrollPane;
    private JTable CompleteTable;
    private JLabel A_Label;
    private JLabel B_Label;
    private JLabel C_Label;
    private JLabel D_Label;
    private JButton submitButton;

    //数据结构题库
    private static List<QuestionInfo> allQuestions;
    //当前界面上显示的试卷（未作）
    private static Paper paper;
    //已经完成的试卷
    private static Paper paperAnswered;
    //试题界面当前显示的题目
    private static int NO;
    private static QuestionInfo nowQuestion;
    //文本格式题库
//    private static Vector allQuestionsInfo=null;
//    private static List<String> choices=null;
//    private static List<String> trueorfalse=null;
//    private static List<String> multiple=null;
//    private static List<ArrayList<String>> answers=null;
//    private static List<String> rightAnswers=null;

    public MainForm() {
        CompleteTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //获取用户选中的那一行题的编号
                int no=Integer.parseInt(CompleteTable.getValueAt(CompleteTable.getSelectedRow(), 0).toString());
                System.out.println(CompleteTable.getValueAt(CompleteTable.getSelectedRow(), 0).toString());
                //缓存当前题目信息
                nowQuestion=paper.getAllQuestions().get(no);
                NO=no;
                //获取题目信息
                String content=getQuestionViewSelected(no);
                //将题目与选项显示在界面上
                questionsTextPane.setText(content);
                //重置按钮
                resetButtonBehaviour();
            }
        });
        aButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                changeButtonBehaviour(PaperCatalogItem.A_BUTTON);
            }
        });
        bButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                changeButtonBehaviour(PaperCatalogItem.B_BUTTON);
            }
        });
        cButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                changeButtonBehaviour(PaperCatalogItem.C_BUTTON);
            }
        });
        dButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                changeButtonBehaviour(PaperCatalogItem.D_BUTTON);
            }
        });
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String answer=getButtonBehaviour();
                if(!answer.equals(PaperCatalogItem.NO)){
                   if(nowQuestion.getAnswer().contains(answer)){//题做对了
                         float score=nowQuestion.getScore();
                        changePaperCatalogBehaviour(NO,score,PaperCatalogItem.ANSWERED);
                       //更新题目信息，这次将标准答案添加到题目下方
                       String content=getQuestionViewSelected(NO);
                       //将题目与选项显示在界面上
                       questionsTextPane.setText(content+"\n"+nowQuestion.getAnswer());
                       rightValue.setText(String.valueOf(paper.getRight()+1));//在界面上设置正确数量
                       paper.setRight(paper.getRight()+1);//在试卷中设置正确数
                   }else{//题做错了
                       float score=0.0f;
                       changePaperCatalogBehaviour(NO,score,PaperCatalogItem.ANSWERED);
                       highlightMistake(CompleteTable,NO);
                       //更新题目信息，这次将标准答案添加到题目下方
                       String content=getQuestionViewSelected(NO);
                       //将题目与选项显示在界面上
                       questionsTextPane.setText(content+"\n"+nowQuestion.getAnswer());
                       wrongValue.setText(String.valueOf(paper.getWrong()+1));//在界面上设置错误数量
                       paper.setWrong(paper.getWrong()+1);//在试卷中设置错题数
                   }
                }
            }
        });
    }

    //改变某一个单元格的颜色,将错误变成红色
    public static void highlightMistake(JTable table, final int rowIn) {
        try {
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer() {
                //重写getTableCellRendererComponent 方法
                @Override
                public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) {
//                    setBackground(Color.RED);

//                    Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table,
//                            value,isSelected, hasFocus, row, column);
//                    if(row==0&&column==1) {
//                        foreground = Color.red;
//                        background = Color.green;
//                    }else{
//                        foreground = Color.BLACK;
//                        background = Color.WHITE;
//                    }
//                    renderer.setForeground(foreground);
//                    renderer.setBackground(background);
//                    return  renderer;

                    if(row+1==rowIn){//如果不确定一下会对每一行都染上颜色
                        setForeground(Color.RED);
                    }else{
                        setForeground(Color.BLACK);
                    }
                    return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
                }
            };
            //对每行的每一个单元格
            int columnCount = table.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(dtcr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改题目在当前表格中的状态
    private void changePaperCatalogBehaviour(int no,float actual,String state){
          CompleteTable.setValueAt(state,no-1,3);
          CompleteTable.setValueAt(actual,no-1,2);
    }
    //重置当前按钮的所有行为
    private void resetButtonBehaviour(){
        aButton.setEnabled(true);
        bButton.setEnabled(true);
        cButton.setEnabled(true);
        dButton.setEnabled(true);
    }
    //获取当前按钮的行为，答案
    private String getButtonBehaviour(){
        if(!aButton.isEnabled()) return PaperCatalogItem.A_BUTTON;
        if(!bButton.isEnabled()) return PaperCatalogItem.B_BUTTON;
        if(!cButton.isEnabled()) return PaperCatalogItem.C_BUTTON;
        if(!dButton.isEnabled()) return PaperCatalogItem.D_BUTTON;
        return PaperCatalogItem.NO;
    }
    //改变按钮的行为
    private void changeButtonBehaviour(String type){
        if(type.equals(PaperCatalogItem.A_BUTTON)){
            aButton.setEnabled(false);
            bButton.setEnabled(true);
            cButton.setEnabled(true);
            dButton.setEnabled(true);
        }
        if(type.equals(PaperCatalogItem.B_BUTTON)){
            aButton.setEnabled(true);
            bButton.setEnabled(false);
            cButton.setEnabled(true);
            dButton.setEnabled(true);
        }
        if(type.equals(PaperCatalogItem.C_BUTTON)){
            aButton.setEnabled(true);
            bButton.setEnabled(true);
            cButton.setEnabled(false);
            dButton.setEnabled(true);
        }
        if(type.equals(PaperCatalogItem.D_BUTTON)){
            aButton.setEnabled(true);
            bButton.setEnabled(true);
            cButton.setEnabled(true);
            dButton.setEnabled(false);
        }
    }
    //获取题目信息
    private static String getQuestionViewSelected(int no){
         String type=paper.getAllQuestions().get(no).getType();
         String content=paper.getAllQuestions().get(no).getQuestion();
         String choices="";
         for(int i=0;i<paper.getAllQuestions().get(no).getChoices().size();i++){
             choices+=paper.getAllQuestions().get(no).getChoices().get(i)+"  \n";
         }
         String result=type+"\n"+content+"\n"+choices;
        return result;
    }

    public static void main(String[] args) {
        //初始化界面
        MainForm mainForm=new MainForm();
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(mainForm.MainFromPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        //初始化题库
        initQuestionPool();

        //生成一张示例试卷(20 choice(0.5) ; 5 mutiple(1) ; 5trueorfalse(1)  )
        paper=PaperController.getPaper(allQuestions);

        //展示试卷目录
        DefaultTableModel model = PaperCatalog.getPaperCatalogView(paper);
        model.isCellEditable(4,30);
        mainForm.CompleteTable.setModel(model);
//        mainForm.CompleteTable.setEnabled(false);

        //
        int a=0;
    }

    //初始化题库
    private static void initQuestionPool(){
        try {
            allQuestions= QuestionController.initQuestionPool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
