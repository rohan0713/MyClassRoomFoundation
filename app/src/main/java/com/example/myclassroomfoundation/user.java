package com.example.myclassroomfoundation;

public class user {

    boolean status;
    String message;
    String id;
    String role;
    String email;
    String name;
    String date_of_birth;
    String phone_no;
    String address;
    String city;
    String state;
    String country;
    String pincode;
    String section;
    String class_name;

    public user(boolean status, String message, String id, String role, String email, String name, String date_of_birth, String phone_no, String address, String city, String state, String country, String pincode, String section, String classs) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.role = role;
        this.email = email;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.phone_no = phone_no;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.section = section;
        this.class_name = classs;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getClasss() {
        return class_name;
    }

    public void setClasss(String classs) {
        this.class_name = classs;
    }
}
