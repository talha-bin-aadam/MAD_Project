package com.example.rar;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class rideConfirm extends Fragment {

    SQLiteHelper db;
    TextView fromLoc;
    TextView toLoc;
    TextView date;
    TextView seats;
    TextView fair;
    TextView totalFair;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_confirm, container, false);

        fromLoc = view.findViewById(R.id.fromCity);
        toLoc = view.findViewById(R.id.toCity);
        date = view.findViewById(R.id.date2);
        seats = view.findViewById(R.id.seat);
        fair = view.findViewById(R.id.fair);
        totalFair = view.findViewById(R.id.totalFair);

        db = new SQLiteHelper(view.getContext());

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);

        Cursor res = db.t2getTravelInfo(sharedPreferences.getLong("userID", 0));

        res.moveToFirst();
        fromLoc.setText(res.getString(0));
        toLoc.setText(res.getString(1));
        date.setText(res.getString(2));

        final int bookedSeats = sharedPreferences.getInt("booked seats", 0);
        final float singleFair = db.t3getFair(sharedPreferences.getInt("rideID", 0));
        seats.setText(String.valueOf(bookedSeats));
        fair.setText(String.valueOf(singleFair));
        totalFair.setText(String.valueOf(singleFair*bookedSeats));

        final Button button = view.findViewById(R.id.confirmButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ticket Booked", Toast.LENGTH_LONG).show();
                db.t3updateSeats(sharedPreferences.getInt("rideID", 0), bookedSeats);

                db.t4ticketInfoInsertData(sharedPreferences.getLong("userID", 0),
                        sharedPreferences.getInt("rideID", 0),
                        bookedSeats, (singleFair*bookedSeats));
            }
        });

        return view;
    }

}
