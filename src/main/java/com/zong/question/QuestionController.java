package com.zong.question;

import com.zong.analysis.Analysis;
import com.zong.common.FilePath;
import com.zong.domain.QuestionInfo;
import com.zong.utils.PdfUtils;

import java.io.IOException;
import java.util.*;

public class QuestionController {
    //初始化题库
    public static List<QuestionInfo> initQuestionPool() throws IOException {
       List<QuestionInfo> allQuestionsInfo= Analysis.getAllQuestionsInfo(PdfUtils.readPdfToTxt(FilePath.PATH));
       return allQuestionsInfo;
    }
}
