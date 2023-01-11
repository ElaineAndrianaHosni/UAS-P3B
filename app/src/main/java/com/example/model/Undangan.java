package com.example.model;

public class Undangan {
    String id;
    String title;
    String started_datetime;
    String end_datetime;
    String description;
    String organizer;
    boolean attending;
    public Undangan(String id,String title,String started_datetime,String end_datetime,String description,String organizer,boolean attending){
        this.id = id;
        this.title = title;
        this.started_datetime = started_datetime;
        this.end_datetime = end_datetime;
        this.description = description;
        this.organizer = organizer;
        this.attending = attending;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStarted_datetime() {
        return started_datetime;
    }

    public String getEnd_datetime() {
        return end_datetime;
    }

    public String getDescription() {
        return description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public boolean isAttending() {
        return attending;
    }
}
