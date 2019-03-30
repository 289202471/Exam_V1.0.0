package com.zong.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    // 判断一个字符串是否含有数字
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
}
