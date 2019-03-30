package com.zong.domain;

import java.util.*;

public class QuestionInfo {

    private String question;//题目
    private String type;//题目类型
    private List<String> choices;//题目选项
    private String answer;//标准答案
    private Float score;//分数

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
