package com.cinema_app.models;

import java.util.ArrayList;

public class Reservation {

    public String details;
    public  String id;
    public  String price;
    public String place;
    public String timeShow;
    public  String length;
    public  ArrayList<Seat> seats;
    public  String email_customer;

    public Reservation() {
    }

    public void setEmail_customer(String email_customer) {
        this.email_customer = email_customer;
    }

    public String getEmail_customer() {
        return email_customer;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTimeShow() {
        return timeShow;
    }

    public void setTimeShow(String timeShow) {
        this.timeShow = timeShow;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}
