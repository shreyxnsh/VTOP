package com.shreyxnsh.vtop.ui.feedbacks;

public class FeedbackData {
    String name,number, email, message, batch;

    public FeedbackData(String name, String number, String email, String message, String batch) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.message = message;
        this.batch = batch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
