package com.zong.paper;

import com.zong.common.QuestionType;
import com.zong.domain.Paper;
import com.zong.domain.QuestionInfo;

import java.util.*;

public class PaperController {


    //随机生成一张试卷
    public static Paper genOnePaper(){
         return null;
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
