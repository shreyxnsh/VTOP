package com.shreyxnsh.vtop.ui.clubs;

public class ClubData {
    private String clubname, facultyname, studentname, image, key;

    public ClubData() {
    }

    public ClubData(String clubname, String facultyname, String studentname, String image, String key) {
        this.clubname = clubname;
        this.facultyname = facultyname;
        this.studentname = studentname;
        this.image = image;
        this.key = key;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
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
