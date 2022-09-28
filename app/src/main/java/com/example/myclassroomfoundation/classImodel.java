package com.example.myclassroomfoundation;

import java.util.List;

public class classImodel {

    String class_id;
    String class_name;
    List<sectionModel> section;

    public List<sectionModel> getsection() {
        return section;
    }

    public classImodel(String class_id, String class_name, List<sectionModel> section) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.section = section;
    }

    public void setsection(List<sectionModel> section) {
        this.section = section;
    }

    public classImodel(String class_id, String class_name) {
        this.class_id = class_id;
        this.class_name = class_name;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
