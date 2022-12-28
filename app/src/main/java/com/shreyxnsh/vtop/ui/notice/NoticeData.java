package com.shreyxnsh.vtop.ui.notice;

public class NoticeData {
    String Title, image, date, time, key;

    public NoticeData() {
    }

    public NoticeData(String name, String designation, String email, String cabin, String title, String downloadUrl) {
    }

    public NoticeData(String title, String image, String date, String time, String key) {
        Title = title;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
