package com.example.myclassroomfoundation;

public class classModel {

    private int image;
    private String section;
    private String classes;

    public classModel(int image, String section, String classes) {
        this.image = image;
        this.section = section;
        this.classes = classes;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
