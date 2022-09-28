package com.example.myclassroomfoundation;

public class questionModel {

    private String id;
    private String question_id;
    private String question;
    private String question_doc;
    private String question_marks;
    private String question_date;

    public questionModel(String id, String question_id, String question, String question_doc, String question_marks, String question_date) {
        this.id = id;
        this.question_id = question_id;
        this.question = question;
        this.question_doc = question_doc;
        this.question_marks = question_marks;
        this.question_date = question_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_doc() {
        return question_doc;
    }

    public void setQuestion_doc(String question_doc) {
        this.question_doc = question_doc;
    }

    public String getQuestion_marks() {
        return question_marks;
    }

    public void setQuestion_marks(String question_marks) {
        this.question_marks = question_marks;
    }

    public String getQuestion_date() {
        return question_date;
    }

    public void setQuestion_date(String question_date) {
        this.question_date = question_date;
    }
}
