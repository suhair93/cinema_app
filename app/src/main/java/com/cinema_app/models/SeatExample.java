package com.cinema_app.models;

import android.graphics.Color;

import com.cinema_app.R;

import by.anatoldeveloper.hallscheme.hall.HallScheme;
import by.anatoldeveloper.hallscheme.hall.Seat;

public class SeatExample implements Seat {

    public int id;
    public int color = R.color.colorAccent;
    public String marker;
    public String selectedSeatMarker;
    public HallScheme.SeatStatus status;

    @Override
    public int id() {
        return id;
    }

    @Override
    public int color() {
        return color;
    }

    @Override
    public String marker() {
        return marker;
    }

    @Override
    public String selectedSeat() {
        return selectedSeatMarker;
    }

    @Override
    public HallScheme.SeatStatus status() {
        return status;
    }

    @Override
    public void setStatus(HallScheme.SeatStatus status) {
        this.status = status;
    }

}