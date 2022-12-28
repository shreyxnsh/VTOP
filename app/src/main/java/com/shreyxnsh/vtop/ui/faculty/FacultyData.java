package com.shreyxnsh.vtop.ui.faculty;

public class FacultyData {
    private String name, designation, email, cabin, image, key;


    public FacultyData() {
    }

    public FacultyData(String name, String designation, String email, String cabin, String image, String key) {
        this.name = name;
        this.designation = designation;
        this.email = email;
        this.cabin = cabin;
        this.image = image;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}