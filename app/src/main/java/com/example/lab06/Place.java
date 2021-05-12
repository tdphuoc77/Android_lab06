package com.example.lab06;

public class Place {
    int id;
    String place;

    public Place(int id, String place) {
        this.id = id;
        this.place = place;
    }

    public Place(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", place='" + place + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
