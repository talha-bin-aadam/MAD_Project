package com.example.rar;

import android.content.Context;
/*
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
*/
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.itemViewHolder>{

    private Context context;
    private List<listItem> itemList;

    public itemAdapter(Context context, List<listItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row, null);
        itemViewHolder holder = new itemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, final int position) {
        final listItem item = itemList.get(position);

        holder.depTime.setText(item.getDeptTime());
        holder.arrivalTime.setText(item.getArrivalTime());
        holder.seats.setText(String.valueOf(item.getAvailSeats()));
        holder.fair.setText(String.valueOf(item.getFair()));
//        Toast.makeText(ridesData.class, String.valueOf(item.getId()), Toast.LENGTH_LONG).show();
//        Log.d("msg", String.valueOf(item.getId()));


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, String.valueOf(item.getId()), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, bookNow.class);
                intent.putExtra("rideID", item.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class itemViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView depTime;
        TextView arrivalTime;
        TextView seats;
        TextView fair;
        Button btn;

        public itemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            depTime = itemView.findViewById(R.id.dep_time);
            arrivalTime = itemView.findViewById(R.id.arrival_time);
            seats = itemView.findViewById(R.id.seats);
            fair = itemView.findViewById(R.id.fair);
            btn = itemView.findViewById(R.id.book);

        }
    }


}
