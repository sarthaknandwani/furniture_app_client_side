package com.example.furniture_app_2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furniture_app_2.Model.Upload;
import com.example.furniture_app_2.R;
import com.example.furniture_app_2.description_activity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private List<Upload> uploads;

    public RecyclerViewAdapter(Context mContext, List<Upload> uploads) {
        this.mContext = mContext;
        this.uploads = uploads;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.card_view_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Upload upload = uploads.get(position);
        holder.product_name.setText(upload.getName());
        holder.company_name.setText(upload.getManufacturer());
        holder.rating_bar.setRating(upload.getRatings());
        holder.cost.setText(upload.getPrice());
        Log.d(TAG, "onBindViewHolder: upload.getUrl() = " + upload.getUrl());
        Glide.with(mContext).load(upload.getUrl()).into(holder.imd_book_thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, description_activity.class);
                intent.putExtra("name", upload.getName());
                intent.putExtra("manufacturer", upload.getManufacturer());
                intent.putExtra("ratings", upload.getRatings());
                intent.putExtra("cost", upload.getPrice());
                intent.putExtra("url", upload.getUrl());
                intent.putExtra("description", upload.getDescription());
                intent.putExtra("url_3d", upload.getUrl_3d());
                intent.putExtra("product_id", upload.getProduct_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_name, company_name, cost;
        ImageView imd_book_thumbnail;
        CardView cardView;
        RatingBar rating_bar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_name);
            company_name = itemView.findViewById(R.id.company_name);
            cost = itemView.findViewById(R.id.cost_0);
            rating_bar = itemView.findViewById(R.id.rating_bar);
            imd_book_thumbnail = itemView.findViewById(R.id.image_0);
            cardView = itemView.findViewById(R.id.cardView_id);
        }
    }
}
