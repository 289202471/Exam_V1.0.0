package com.zong.question;

import com.zong.analysis.Analysis;
import com.zong.common.FilePath;
import com.zong.common.QuestionType;
import com.zong.domain.QuestionInfo;
import com.zong.utils.PdfUtils;

import java.io.IOException;
import java.util.*;

public class QuestionsPool {
    public static List<QuestionInfo> choices=new ArrayList<QuestionInfo>();
    public static List<QuestionInfo> multiples=new ArrayList<QuestionInfo>();
    public static List<QuestionInfo> trueorfalses=new ArrayList<QuestionInfo>();
    public static Vector allQuestions=new Vector();

    //初始化题库
    public static Vector initQuestionPool() throws IOException {
       List<QuestionInfo> allQuestionsInfo= Analysis.getAllQuestionsInfo(PdfUtils.readPdfToTxt(FilePath.PATH));
       allQuestions=classifyQuestions(allQuestionsInfo);
       return allQuestions;
    }

    //进行题目分类
    public static Vector classifyQuestions(List<QuestionInfo> allQuestions) {
        for (int i = 0; i < allQuestions.size(); i++) {
            if (allQuestions.get(i).getType().equals(QuestionType.CHOICE)) {
                choices.add(allQuestions.get(i));
            }
            if (allQuestions.get(i).getType().equals(QuestionType.MULTIPLE)) {
                multiples.add(allQuestions.get(i));
            }
            if (allQuestions.get(i).getType().equals(QuestionType.TRUEORFALSE)) {
                trueorfalses.add(allQuestions.get(i));
            }
        }
        Vector result = new Vector();
        result.add(choices);
        result.add(multiples);
        result.add(trueorfalses);
        return result;
    }
}
