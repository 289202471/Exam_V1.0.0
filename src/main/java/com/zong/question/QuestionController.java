package com.zong.question;

import com.zong.domain.QuestionInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionController {
    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     * @param cardNum 待检验的原始卡号
     * @return 返回是否包含
     */
    private static boolean judgeContainsStr(String cardNum) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m= Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }


    //判断这道题答对没有
    public static boolean judgeAnswer(QuestionInfo question,String answer){
        String array[]=question.getAnswer().split(answer);

        for(int i=0;i<array.length;i++){
            if(judgeContainsStr(array[i])){//如果某一个子串中有字母，那么这个多选题就做的不对
                return false;
            }
        }
        return true;
    }
}
