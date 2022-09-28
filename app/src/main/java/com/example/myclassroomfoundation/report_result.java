package com.example.myclassroomfoundation;

public class report_result {

    String question;
    String answer;
    String full_mark;
    String obtained_mark;

    public report_result(String question, String answer, String full_mark, String obtained_mark) {
        this.question = question;
        this.answer = answer;
        this.full_mark = full_mark;
        this.obtained_mark = obtained_mark;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFull_mark() {
        return full_mark;
    }

    public void setFull_mark(String full_mark) {
        this.full_mark = full_mark;
    }

    public String getObtained_mark() {
        return obtained_mark;
    }

    public void setObtained_mark(String obtained_mark) {
        this.obtained_mark = obtained_mark;
    }
}
