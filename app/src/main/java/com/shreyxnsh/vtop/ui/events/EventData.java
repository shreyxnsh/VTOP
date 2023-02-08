package com.shreyxnsh.vtop.ui.events;

public class EventData {

    private String clubname, datetime, eventname, image, venue, key;

    public EventData() {
    }

    public EventData(String clubname, String datetime, String eventname, String image, String venue, String key) {
        this.clubname = clubname;
        this.datetime = datetime;
        this.eventname = eventname;
        this.image = image;
        this.venue = venue;
        this.key = key;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
