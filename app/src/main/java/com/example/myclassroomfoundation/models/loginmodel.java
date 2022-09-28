package com.example.myclassroomfoundation.models;

public class loginmodel {

    boolean status;
    String message;
    String id;
    String role;

    public loginmodel(boolean status, String message, String id, String role) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.role = role;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
