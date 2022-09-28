package com.example.myclassroomfoundation;

public class meetingModel {

    String class_id;
    String subject_id;
    String link;

    public meetingModel(String class_id, String subject_id, String link) {
        this.class_id = class_id;
        this.subject_id = subject_id;
        this.link = link;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
