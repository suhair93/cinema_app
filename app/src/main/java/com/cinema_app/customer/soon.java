package com.cinema_app.customer;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cinema_app.Adapter.NowAdapter;
import com.cinema_app.Adapter.SoonAdapter;
import com.cinema_app.R;
import com.cinema_app.models.movies;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class soon extends Fragment {
    FirebaseDatabase database;
    DatabaseReference ref;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_soon, container, false);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }



        // LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        //  mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        // GetNotification(Token);

      final   List<movies> lList = new ArrayList<movies>();


        final SoonAdapter nAdapter = new SoonAdapter(getContext(), lList);
        recyclerView.setAdapter(nAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();


        ref.child("movie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    movies movies = snapshot.getValue(movies.class);
                    if (movies.getType().equals("2"))

                        lList.add(movies);
                    nAdapter.notifyDataSetChanged();}


                Collections.reverse(lList);
                // box.hideAll();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

}
