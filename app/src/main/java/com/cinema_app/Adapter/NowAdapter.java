package com.cinema_app.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cinema_app.R;
import com.cinema_app.models.movies;


import java.util.List;


public class NowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<movies> list ;
    private Context context;
    public NowAdapter(Context context, List<movies> List1) {
        this.context = context;
        this.list = List1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        Holder newsHolder = (Holder) holder;
        movies movies = list.get(position);

        newsHolder.title.setText(movies.getName());



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
        TextView title ;
        public Holder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.name);


        }

    }
}






