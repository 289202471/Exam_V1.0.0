package com.zong.domain;

import java.util.*;

public class Paper {

    private Vector allQuestions_Str;
    private List<QuestionInfo> allQuestions;
    private float score;

    private int right;
    private int wrong;

    private List<QuestionInfo> rightQuestions;
    private List<QuestionInfo> wrongQuestions;


    private List<QuestionInfo> choices;
    private int rightChoices;
    private int wrongChoices;
    private List<QuestionInfo> rightChoicesQuestions;
    private List<QuestionInfo> wrongChoicesQuestions;

    private List<QuestionInfo> trueorfalse;
    private int rightTrueorfalse;
    private int wrongTrueorfalse;
    private List<QuestionInfo> rightTrueorfalseQuestions;
    private List<QuestionInfo> wrongTrueorfalseQuestions;

    private List<QuestionInfo> muliple;
    private int rightMuliple;
    private int wrongMuliple;
    private List<QuestionInfo> rightMulipleQuestions;
    private List<QuestionInfo> wrongMulipleQuestions;

    public Paper() {
        this.score=0;
        this.right=0;
        this.wrong=0;
        this.choices=new ArrayList<QuestionInfo>();
        this.trueorfalse=new ArrayList<QuestionInfo>();
        this.muliple=new ArrayList<QuestionInfo>();
        this.allQuestions=new ArrayList<QuestionInfo>();
        this.allQuestions_Str=new Vector();
        this.rightQuestions=new ArrayList<QuestionInfo>();
        this.wrongQuestions=new ArrayList<QuestionInfo>();

        this.rightChoices=0;
        this.rightMuliple=0;
        this.rightTrueorfalse=0;
        this.rightChoicesQuestions=new ArrayList<QuestionInfo>();
        this.rightMulipleQuestions=new ArrayList<QuestionInfo>();
        this.rightTrueorfalseQuestions=new ArrayList<QuestionInfo>();

        this.wrongChoices=0;
        this.wrongMuliple=0;
        this.wrongTrueorfalse=0;
        this.wrongChoicesQuestions=new ArrayList<QuestionInfo>();
        this.wrongMulipleQuestions=new ArrayList<QuestionInfo>();
        this.wrongTrueorfalseQuestions=new ArrayList<QuestionInfo>();
    }

    public List<QuestionInfo> getRightQuestions() {
        return rightQuestions;
    }

    public void setRightQuestions(List<QuestionInfo> rightQuestions) {
        this.rightQuestions = rightQuestions;
    }

    public List<QuestionInfo> getWrongQuestions() {
        return wrongQuestions;
    }

    public void setWrongQuestions(List<QuestionInfo> wrongQuestions) {
        this.wrongQuestions = wrongQuestions;
    }

    public int getRightChoices() {
        return rightChoices;
    }

    public void setRightChoices(int rightChoices) {
        this.rightChoices = rightChoices;
    }

    public int getWrongChoices() {
        return wrongChoices;
    }

    public void setWrongChoices(int wrongChoices) {
        this.wrongChoices = wrongChoices;
    }

    public List<QuestionInfo> getRightChoicesQuestions() {
        return rightChoicesQuestions;
    }

    public void setRightChoicesQuestions(List<QuestionInfo> rightChoicesQuestions) {
        this.rightChoicesQuestions = rightChoicesQuestions;
    }

    public List<QuestionInfo> getWrongChoicesQuestions() {
        return wrongChoicesQuestions;
    }

    public void setWrongChoicesQuestions(List<QuestionInfo> wrongChoicesQuestions) {
        this.wrongChoicesQuestions = wrongChoicesQuestions;
    }

    public int getRightTrueorfalse() {
        return rightTrueorfalse;
    }

    public void setRightTrueorfalse(int rightTrueorfalse) {
        this.rightTrueorfalse = rightTrueorfalse;
    }

    public int getWrongTrueorfalse() {
        return wrongTrueorfalse;
    }

    public void setWrongTrueorfalse(int wrongTrueorfalse) {
        this.wrongTrueorfalse = wrongTrueorfalse;
    }

    public List<QuestionInfo> getRightTrueorfalseQuestions() {
        return rightTrueorfalseQuestions;
    }

    public void setRightTrueorfalseQuestions(List<QuestionInfo> rightTrueorfalseQuestions) {
        this.rightTrueorfalseQuestions = rightTrueorfalseQuestions;
    }

    public List<QuestionInfo> getWrongTrueorfalseQuestions() {
        return wrongTrueorfalseQuestions;
    }

    public void setWrongTrueorfalseQuestions(List<QuestionInfo> wrongTrueorfalseQuestions) {
        this.wrongTrueorfalseQuestions = wrongTrueorfalseQuestions;
    }

    public int getRightMuliple() {
        return rightMuliple;
    }

    public void setRightMuliple(int rightMuliple) {
        this.rightMuliple = rightMuliple;
    }

    public int getWrongMuliple() {
        return wrongMuliple;
    }

    public void setWrongMuliple(int wrongMuliple) {
        this.wrongMuliple = wrongMuliple;
    }

    public List<QuestionInfo> getRightMulipleQuestions() {
        return rightMulipleQuestions;
    }

    public void setRightMulipleQuestions(List<QuestionInfo> rightMulipleQuestions) {
        this.rightMulipleQuestions = rightMulipleQuestions;
    }

    public List<QuestionInfo> getWrongMulipleQuestions() {
        return wrongMulipleQuestions;
    }

    public void setWrongMulipleQuestions(List<QuestionInfo> wrongMulipleQuestions) {
        this.wrongMulipleQuestions = wrongMulipleQuestions;
    }



    public List<QuestionInfo> getChoices() {
        return choices;
    }

    public void setChoices(List<QuestionInfo> choices) {
        this.choices = choices;
    }

    public List<QuestionInfo> getTrueorfalse() {
        return trueorfalse;
    }

    public void setTrueorfalse(List<QuestionInfo> trueorfalse) {
        this.trueorfalse = trueorfalse;
    }

    public List<QuestionInfo> getMuliple() {
        return muliple;
    }

    public void setMuliple(List<QuestionInfo> muliple) {
        this.muliple = muliple;
    }

    public Vector getAllQuestions_Str() {
        return allQuestions_Str;
    }

    public void setAllQuestions_Str(Vector allQuestions_Str) {
        this.allQuestions_Str = allQuestions_Str;
    }

    public List<QuestionInfo> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(List<QuestionInfo> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    //随机生成试卷
    public Vector createPaper(){
        return null;
    }
}
