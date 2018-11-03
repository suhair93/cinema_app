package com.cinema_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cinema_app.Login;
import com.cinema_app.R;
import com.cinema_app.customer.buyTick;
import com.cinema_app.models.Keys;
import com.cinema_app.models.movies;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class SeatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> list ;
    private Context context;
    public SeatAdapter(Context context, List<Integer> List1) {
        this.context = context;
        this.list = List1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_seat, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

         SharedPreferences prefs =  context.getSharedPreferences(Keys.KEY_ID, MODE_PRIVATE);
        final String  email_customer = prefs.getString(Keys.KEY_CUSTOMER,"");
        Holder holder1 = (Holder) holder;




    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);

    }
    public class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);


        }

    }
}






