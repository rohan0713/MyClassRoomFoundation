package com.example.myclassroomfoundation.Teacher;

public class assignmentModel {

    private String subject_id;
    private String subject_name;
    private String class_name;
    private String section_name;

    public assignmentModel(String sub_id, String subject_name, String class_name, String section_name) {
        this.subject_id = sub_id;
        this.subject_name = subject_name;
        this.class_name = class_name;
        this.section_name = section_name;
    }

    public assignmentModel(String subject_id, String subject_name) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public String getSub_id() {
        return subject_id;
    }

    public void setSub_id(String sub_id) {
        this.subject_id = sub_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }
}
