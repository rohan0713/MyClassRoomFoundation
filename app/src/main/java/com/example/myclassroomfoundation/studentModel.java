package com.example.myclassroomfoundation;

public class studentModel {

    private String name;
    private String roll_no;
    private String answer;

    public studentModel(String name, String roll_no, String answer) {
        this.name = name;
        this.roll_no = roll_no;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
