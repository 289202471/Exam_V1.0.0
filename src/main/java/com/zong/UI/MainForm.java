package com.zong.UI;

import com.zong.UI.view.PaperCatalog;
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
import javax.swing.table.DefaultTableModel;
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

    //数据结构题库
    private static List<QuestionInfo> allQuestions;
    //当前界面上显示的试卷
    private static Paper paper;
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
                //获取题目信息
                String content=getQuestionViewSelected(no);
                //将题目与选项显示在界面上
                questionsTextPane.setText(content);
            }
        });
    }

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
