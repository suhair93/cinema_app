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
import com.cinema_app.models.Seat;
import com.cinema_app.models.movies;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class SeatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Seat> list ;
    private Context context;
    private boolean status = false;
    public SeatAdapter(Context context, List<Seat> List1) {
        this.context = context;
        this.list = List1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_seat, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

       final Seat seat = list.get(position);
        final Holder holder1 = (Holder) holder;
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == false){
                    holder1.imageView.setBackgroundResource(R.drawable.select);
                    status = true;
                    seat.setStatus(true+"");

                }else {
                    holder1.imageView.setBackgroundResource(R.drawable.seat);
                    status = false;
                    seat.setStatus(false+"");
                }


            }
        });



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
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
           imageView = itemView.findViewById(R.id.img);

        }

    }
}






