package com.cinema_app.customer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cinema_app.Adapter.SeatAdapter;
import com.cinema_app.R;
import com.cinema_app.models.Seat;
import com.cinema_app.models.movies;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class buyTick extends AppCompatActivity {
          String id = "";
          TextView about,place,timeShow ,length,price;
          ImageView img;
          Button next;
          ProgressDialog dialog;
    FirebaseDatabase database;
    DatabaseReference ref;
    RecyclerView recyclerView;
    List<Seat> list = new ArrayList<>();
    SeatAdapter seatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_home);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        about = findViewById(R.id.about);
        place = findViewById(R.id.lab);
        timeShow = findViewById(R.id.showTime);
        length = findViewById(R.id.length);
        img = findViewById(R.id.img);
        next = findViewById(R.id.next);
        about = findViewById(R.id.about);

        price = findViewById(R.id.price);

        dialog = new ProgressDialog(buyTick.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id", "");
        }


        recyclerView = findViewById(R.id.recycler);
        seatAdapter = new SeatAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(seatAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),10));

        for (int i =0 ; i<= 59 ; i++){
            list.add(new Seat(i,false+""));
        }


        Query fireQuery = ref.child("movie").orderByChild("id").equalTo(id);
        fireQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // ازا الحساب غير موجوديظهر مسج
                if (dataSnapshot.getValue() == null) {
                    Toast.makeText(getBaseContext(), "Not found", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    // ازا الحساب موجوديقوم بتخزين الحساب المدخل
                } else {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        movies movies = snapshot.getValue(movies.class);
                        about.setText(movies.getDetails());
                        place.setText(movies.getScreen());
                        timeShow.setText(movies.getTime());
                        length.setText(movies.getDuration());
                        Glide.with(getBaseContext()).load(movies.getImg()).into(img);


                    }


                    dialog.dismiss();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.dismiss();
                Toast.makeText(getBaseContext(), "no connected internet", Toast.LENGTH_SHORT).show();
            }

        });
     next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(buyTick.this,visacard.class);
             i.putExtra("details",about.getText().toString());
             i.putExtra("place",place.getText().toString());
             i.putExtra("timeShow",timeShow.getText().toString());
             i.putExtra("length",length.getText().toString());
             i.putExtra("id",id);
             i.putParcelableArrayListExtra("seat", (ArrayList<? extends Parcelable>) list);
             startActivity(i);
         }
     });



    }
}
