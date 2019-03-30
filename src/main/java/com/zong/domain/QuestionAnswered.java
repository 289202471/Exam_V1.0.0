package com.zong.domain;

public class QuestionAnswered extends QuestionInfo {
    private String state;
    private Float actual;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Float getActual() {
        return actual;
    }

    public void setActual(Float actual) {
        this.actual = actual;
    }
}
