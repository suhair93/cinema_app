package com.cinema_app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cinema_app.models.SeatExample;

import by.anatoldeveloper.hallscheme.hall.HallScheme;
import by.anatoldeveloper.hallscheme.hall.Seat;
import by.anatoldeveloper.hallscheme.hall.SeatListener;
import by.anatoldeveloper.hallscheme.view.ZoomableImageView;

public class buyTick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_home);

    }
}
