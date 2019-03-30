package com.zong.utils;

import java.io.*;
import java.util.*;

public class TxtUtils {
    public static List<String> getQuestions(String path) throws Exception {
        /**
         * 读入TXT文件
         */
//        String pathname = "input.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        List<String> result=new ArrayList<String>();

//        FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
//        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        String line;
        //网友推荐更加简洁的写法
        while ((line = br.readLine()) != null) {
            result.add(line);
            // 一次读入一行数据
            System.out.println(line);
        }
        return result;
    }
}




