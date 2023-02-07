package com.shreyxnsh.vtop.ui.home;

public class EventData {
    int image;
    String name, time, location, clubname;

    public EventData(int image, String name, String time, String location, String clubname) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.location = location;
        this.clubname = clubname;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getClubname() {
        return clubname;
    }
}

