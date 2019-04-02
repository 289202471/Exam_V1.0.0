package com.zong.paper;

import com.zong.common.PaperType;
import com.zong.common.QuestionType;
import com.zong.domain.Paper;
import com.zong.domain.QuestionInfo;
import com.zong.question.QuestionsPool;

import java.util.*;

public class PaperController {


    //从传递进来的问题链表中随机选择一定数量的问题
    public static List<QuestionInfo> getQuestionsRandom(int num, List<QuestionInfo> questions){
        List<QuestionInfo> result=new ArrayList<QuestionInfo>();
        List<Integer> temp=new ArrayList<Integer>();

        int base = questions.size();//用来生成随机数的基数

        //在10到20之间产生20个随机数；
        for(int i=0;i<num;i++)		 //控制产生的随机数的个数
        {
            Random random=new Random();	 //使用Random函数产生随机数；
            int b=(int)(Math.random()*base);//[0,670)

            if(temp.contains(b)) {
                i--;
                continue;
            }
            else{
                temp.add(b);
                result.add(questions.get(b));
            }
        }
        return result;
    }
    //随机提取一定数量的题目
    public static List<QuestionInfo> getQuestionsRandom(int num,QuestionType type){
        List<QuestionInfo> result=null;
        if(type.equals(QuestionType.CHOICE)){
            result= QuestionsPool.choices;
        }
        if(type.equals(QuestionType.MULTIPLE)){
            result=QuestionsPool.multiples;
        }
        if(type.equals(QuestionType.TRUEORFALSE)){
            result=QuestionsPool.trueorfalses;
        }

        //获取随机题型
        result=getQuestionsRandom(num,result);
        return result;
    }
    //随机生成一张试卷
    public static Paper genOnePaper(){
         Paper paper=new Paper();

         List<QuestionInfo> choices=getQuestionsRandom(PaperType.NUM_CHOICE,QuestionsPool.choices);
         paper.setChoices(choices);
         List<QuestionInfo> muliple=getQuestionsRandom(PaperType.NUM_MULTIPLE,QuestionsPool.multiples);
         paper.setMuliple(muliple);
         List<QuestionInfo> trueorfalse=getQuestionsRandom(PaperType.NUM_TRUEORFALSE,QuestionsPool.trueorfalses);
         paper.setTrueorfalse(trueorfalse);

         List<QuestionInfo> all=new ArrayList<QuestionInfo>();
         all.addAll(choices);
         all.addAll(muliple);
         all.addAll(trueorfalse);
         paper.setAllQuestions(all);

         return paper;
    }

    //根据特定题目生成一张试卷
    public static Paper genOnePaper(List<QuestionInfo> questions){
        Paper paper=new Paper();
        //添加所有题目信息
        paper.setAllQuestions(questions);

        //题目分类
        for(QuestionInfo question:questions){
            if(question.getType().equals(QuestionType.CHOICE)){
                paper.getChoices().add(question);
            }
            if(question.getType().equals(QuestionType.TRUEORFALSE)){
                paper.getTrueorfalse().add(question);
            }
            if(question.getType().equals(QuestionType.MULTIPLE)){
                paper.getMuliple().add(question);
            }
        }

        return paper;
    }

    //生成示例试卷
    public static Paper getPaper(List<QuestionInfo> allQuestions){
        List<QuestionInfo> need=new ArrayList<QuestionInfo>();

        for(int i=0;i<allQuestions.size();i++){
            //20道选择题
            if(allQuestions.get(i).getType().equals(QuestionType.CHOICE)&&need.size()<20){
                for(int j=i;j<i+20;j++){
                    if(allQuestions.get(j).getType().equals(QuestionType.CHOICE)){
                        need.add(allQuestions.get(j));
                    }else{
                        break;
                    }
                }
            }
            //5道判断
            if(allQuestions.get(i).getType().equals(QuestionType.MULTIPLE)&&need.size()<25){
                for(int j=i;j<i+5;j++){
                    if(allQuestions.get(j).getType().equals(QuestionType.MULTIPLE)){
                        need.add(allQuestions.get(j));
                    }else{
                        break;
                    }
                }
            }

            //5道多选
            if(allQuestions.get(i).getType().equals(QuestionType.TRUEORFALSE)){
                for(int j=i;j<i+5;j++){
                    if(allQuestions.get(j).getType().equals(QuestionType.TRUEORFALSE)){
                        need.add(allQuestions.get(j));
                    }else{
                        break;
                    }
                }
            }
            if(need.size()==30) break;
        }
        Paper paper= PaperController.genOnePaper(need);
        return paper;
    }

}
