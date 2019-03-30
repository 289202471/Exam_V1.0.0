package com.zong.utils;

import java.io.IOException;
import java.util.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class PdfUtils  {
    /**
         * 读取pdf内容
         * @param pdfPath
         */
public static List<String> readPdfToTxt(String pdfPath) throws IOException {
    PdfReader reader = null;
    StringBuffer buff = new StringBuffer();
    reader = new PdfReader(pdfPath);
    PdfReaderContentParser parser = new PdfReaderContentParser(reader);
    int num = reader.getNumberOfPages();// 获得页数
    TextExtractionStrategy strategy;
    for (int i = 1; i <= num; i++) {
    strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
    buff.append(strategy.getResultantText());
    }

    //转化成字符串链表
    List<String> result = new ArrayList<String>();
    Collections.addAll(result, buff.toString().split("\n"));
//    for(int i=0;i<result.size();i++){
//        System.out.println(result.get(i));
//    }
    return result;
 }
}
