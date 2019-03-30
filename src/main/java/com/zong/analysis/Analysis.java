package com.zong.analysis;

import com.zong.common.QuestionType;
import com.zong.domain.QuestionInfo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analysis {
    // 判断一个字符串是否是一道题
    public static boolean IsQuestion(String content) {
        boolean flag = false;
        //java 中正则表达式需要使用（ .*什么东西+.* ）来表示 在前面加一个 ^  表示用这个字符串开头的
        Pattern p = Pattern.compile(".*^\\d+.*.*\\.+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    //从一个字符串链表中解析出来所有的题目和选项 有题目分类 返回值是题目的链表
    public static List<QuestionInfo> getAllQuestionsInfo(List<String> lines){

        List<QuestionInfo> allQuestions=new ArrayList<QuestionInfo>();
        QuestionInfo thisQuestion=null;
        List<String> thisChoice=null;
        String markType=null;//标记当前题型
        Float score=0.0f;//当前题型多少分

        for(String line:lines){

            //相当于是一个移动指针的过程，这个后面考虑一下封装到一个函数里
            if(line.equals(QuestionType.CHOICE)){
                markType=QuestionType.CHOICE;
                score=1.0f;
//                thisQuestion.setType(QuestionType.CHOICE);
            }
            if(line.equals(QuestionType.MULTIPLE)){
                markType=QuestionType.MULTIPLE;
                score=0.5f;
//               thisQuestion.setType(QuestionType.MULTIPLE);
            }
            if(line.equals(QuestionType.TRUEORFALSE)){
                markType=QuestionType.TRUEORFALSE;
                score=0.5f;
//                thisQuestion.setType(QuestionType.TRUEORFALSE);
            }
            if(IsQuestion(line)){
                //如果发现了一道新题，就把上一道题保存起来
                if(thisQuestion!=null){
                    allQuestions.add(thisQuestion);
                }
                thisQuestion=new QuestionInfo();//创建下一道题
                thisQuestion.setType(markType);//设置类型与分数
                thisQuestion.setScore(score);
                System.out.println(line);
                thisQuestion.setQuestion(line);
                if(thisChoice!=null){
                    thisQuestion.setChoices(thisChoice);
                }
                thisChoice =new ArrayList<String>();
                thisQuestion.setChoices(thisChoice);
            }
            else{
                if(thisChoice!=null){
                    //如果这个答案是标准答案
                    if(line.contains(QuestionType.RIGHTANSWER)){
                        thisQuestion.setAnswer(line);
                    }else {
                        thisChoice.add(line);
                    }
                }
            }
        }
        return allQuestions;
    }
    //从一个字符串链表中解析出来所有的题目和选项 有题目分类 返回值是一些字符串的链表
    public static Vector getQuestionsAndAnswerbyType(List<String> lines){
        List<String> choices=new ArrayList<String>();
        List<String> trueOrFalse=new ArrayList<String>();
        List<String> multiple=new ArrayList<String>();

        List<ArrayList<String>> answers=new ArrayList<ArrayList<String>>() ;
        List<String> rightAnswers=new ArrayList<String>();

        List<String> thisquestion=null;
        ArrayList<String> thisAnswer=null;
        for(String line:lines){
            //相当于是一个移动指针的过程，这个后面考虑一下封装到一个函数里
            if(line.equals(QuestionType.CHOICE)){
                thisquestion=choices;
            }
            if(line.equals(QuestionType.MULTIPLE)){
                thisquestion=trueOrFalse;
            }
            if(line.equals(QuestionType.TRUEORFALSE)){
                thisquestion=multiple;
            }
            if(IsQuestion(line)){
                System.out.println(line);
                thisquestion.add(line);
                if(thisAnswer!=null){
                    answers.add(thisAnswer);
                }
                thisAnswer =new ArrayList<String>();
            }
            else{
                if(thisAnswer!=null){
                    //如果这个答案是标准答案
                    if(line.contains(QuestionType.RIGHTANSWER)){
                        rightAnswers.add(line);
                    }else {
                        thisAnswer.add(line);
                    }
                }
            }
        }
        Vector v=new Vector();
        v.add(choices);
        v.add(trueOrFalse);
        v.add(multiple);
        v.add(answers);
        v.add(rightAnswers);
        return v;
    }

    //从一个字符串链表中解析出来所有的题目和选项 无题目分类
    public static Vector getQuestionsAndAnswer(List<String> lines){
        List<String> questions=new ArrayList<String>();
        List<ArrayList<String>> answers=new ArrayList<ArrayList<String>>() ;

        ArrayList<String> thisAnswer=null;
        for(String line:lines){
            if(IsQuestion(line)){
                 System.out.println(line);
                 questions.add(line);
                 if(thisAnswer!=null){
                     answers.add(thisAnswer);
                 }
                thisAnswer =new ArrayList<String>();
             }
             else{
                if(thisAnswer!=null){
                    thisAnswer.add(line);
                }
             }
         }
        Vector v=new Vector();
        v.add(questions);
        v.add(answers);
        return v;
    }
}
