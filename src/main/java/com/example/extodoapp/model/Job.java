package com.example.extodoapp.model;

import java.time.LocalTime;

public class Job {
    private int id;
    private String description;
    private LocalTime time;
    private boolean status;

    public Job() {
    }

    public Job(int id, String description, LocalTime time, boolean status) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.status = status;
    }

    public Job(String description, LocalTime time) {
        this.description = description;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
