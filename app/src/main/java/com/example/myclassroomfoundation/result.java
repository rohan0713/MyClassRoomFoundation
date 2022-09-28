package com.example.myclassroomfoundation;

public class result {

    boolean status;
    String message;
    String url;

    public String getUrl() {
        return url;
    }

    public result(boolean status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public result(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
