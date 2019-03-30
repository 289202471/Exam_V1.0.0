package com.zong.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.util.*;

public class WordUtils {
    //doc
    public static String getDoc(String path) throws Exception {
        String docValue=null;
        //将文本内容转化为字节流
        FileInputStream fis=new FileInputStream(path);
        //word的字节流不是文本文档里面的字节流，不可以直接使用
        HWPFDocument doc=new HWPFDocument(fis);
        Range range=doc.getRange();
        //获取内容
        docValue=range.text();
        return docValue;
    }

    //docx
    public static String getDocx(String path) throws Exception {
       StringBuilder sb=new StringBuilder();
        OPCPackage op=POIXMLDocument.openPackage(path);
        ZipSecureFile.setMinInflateRatio(-1.0d);//调整解析比率，不然会报错。
        XWPFDocument docx=new XWPFDocument(op);
        List<XWPFParagraph> paragraphs=docx.getParagraphs();

        for(XWPFParagraph paragraph:paragraphs){
            sb.append(paragraph.getText());
            sb.append("\n");
        }
        docx.close();
        return sb.toString();
    }

    public static String readWord(String path) {
        String buffer = "";
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                ZipSecureFile.setMinInflateRatio(-1.0d);//调整解析比率，不然会报错。
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer;
    }

}
