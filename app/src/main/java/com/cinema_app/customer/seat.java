package com.cinema_app.customer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cinema_app.Adapter.SeatAdapter;
import com.cinema_app.R;
import com.cinema_app.models.SeatExample;

import java.util.ArrayList;
import java.util.List;

import by.anatoldeveloper.hallscheme.hall.HallScheme;
import by.anatoldeveloper.hallscheme.hall.Seat;
import by.anatoldeveloper.hallscheme.hall.SeatListener;
import by.anatoldeveloper.hallscheme.view.ZoomableImageView;

public class seat extends AppCompatActivity {
RecyclerView recyclerView;
List<Integer> list = new ArrayList<>();
    SeatAdapter seatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_tickets2);

        recyclerView = findViewById(R.id.recycler);
        seatAdapter = new SeatAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(seatAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),10));

        for (int i =0 ; i<= 50 ; i++){
            list.add(i);
        }


//        ZoomableImageView imageView = (ZoomableImageView)findViewById(R.id.zoomable_image);
//        Seat seats[][] = new Seat[10][10];
//        HallScheme scheme = new HallScheme(imageView, basicScheme(), this);
//
//        scheme.setSeatListener(new SeatListener() {
//
//            @Override
//            public void selectSeat(int id) {
//                Toast.makeText(getBaseContext(), "select seat " + id, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void unSelectSeat(int id) {
//                Toast.makeText(getBaseContext(), "unSelect seat " + id, Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//     //  scheme.setSceneBackgroundColor(R.color.colorAccent);
//        scheme.setBackgroundColor(Color.TRANSPARENT);
//
//      //  scheme.setMarkerColor(Color.RED);
//      //  scheme.setChosenSeatTextColor(Color.RED);
//    }
//    public Seat[][] basicScheme() {
//        Seat seats[][] = new Seat[10][10];
//        for (int i = 0; i < 10; i++)
//            for(int j = 0; j < 10; j++) {
//                SeatExample seat = new SeatExample();
//                seat.id = i * 10 + (j+1);
//                seat.selectedSeatMarker = String.valueOf(j+1);
//                seat.status = HallScheme.SeatStatus.FREE;
//                seats[i][j] = seat;
//            }
    //    return seats;
    }
}
