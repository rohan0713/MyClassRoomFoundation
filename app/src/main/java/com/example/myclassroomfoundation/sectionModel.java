package com.example.myclassroomfoundation;

public class sectionModel {

    String section_id;
    String section_name;

    public sectionModel(String section_id, String section_name) {
        this.section_id = section_id;
        this.section_name = section_name;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }
}
