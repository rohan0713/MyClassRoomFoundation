package com.example.myclassroomfoundation;

public class answerModel {

    String contact_id;
    String name;
    String roll_no;
    String question_id;
    String answer;
    String answer_doc;
    String answer_marks;
    String answer_date;

    public answerModel(String contact_id, String name, String roll_no, String question_id, String answer, String answer_doc, String answer_marks, String answer_date) {
        this.contact_id = contact_id;
        this.name = name;
        this.roll_no = roll_no;
        this.question_id = question_id;
        this.answer = answer;
        this.answer_doc = answer_doc;
        this.answer_marks = answer_marks;
        this.answer_date = answer_date;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer_doc() {
        return answer_doc;
    }

    public void setAnswer_doc(String answer_doc) {
        this.answer_doc = answer_doc;
    }

    public String getAnswer_marks() {
        return answer_marks;
    }

    public void setAnswer_marks(String answer_marks) {
        this.answer_marks = answer_marks;
    }

    public String getAnswer_date() {
        return answer_date;
    }

    public void setAnswer_date(String answer_date) {
        this.answer_date = answer_date;
    }
}
