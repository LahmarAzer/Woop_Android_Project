package com.esprit.lastwooptravelproject;

public class Friend {
    private int id;
    private String name;
    private String location;
    private String bio;
    private boolean status;
    public Friend(int id, String name, String location, String bio, boolean status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.bio = bio;
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // You might want to override toString, equals, and hashCode methods as well.
}
