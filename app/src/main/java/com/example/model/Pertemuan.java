package com.example.model;

public class Pertemuan {
    String id;
    String title;
    String started_datetime;
    String end_datetime;
    public Pertemuan(String id,String title,String started_datetime,String end_datetime){
        this.id = id;
        this.title = title;
        this.started_datetime = started_datetime;
        this.end_datetime = end_datetime;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStarted_datetime(String started_datetime) {
        this.started_datetime = started_datetime;
    }

    public void setEnd_datetime(String end_datetime) {
        this.end_datetime = end_datetime;
    }
}
