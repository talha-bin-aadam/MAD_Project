package com.example.rar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ridesData extends AppCompatActivity {

    SQLiteHelper db;

    TextView fromLoc;
    TextView toLoc;
    TextView date;

    RecyclerView recyclerView;

    List<listItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides_data);

        fromLoc = findViewById(R.id.fromCity);
        toLoc = findViewById(R.id.toCity);
        date = findViewById(R.id.date2);

        db = new SQLiteHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
//        Toast.makeText(ridesData.this, String.valueOf(sharedPreferences.getLong("userID", 0)), Toast.LENGTH_LONG).show();
        Cursor res = db.t2getTravelInfo(sharedPreferences.getLong("userID", 0));

        res.moveToFirst();
        fromLoc.setText(res.getString(0));
        toLoc.setText(res.getString(1));
        date.setText(res.getString(2));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new SQLiteHelper(this);
        res = db.t3getRidesInfo(fromLoc.getText().toString(),
                toLoc.getText().toString(), date.getText().toString());

        if(res.getCount() == 0) {
            Toast.makeText(this, "No rides available", Toast.LENGTH_LONG).show();
        }
        else {
            itemList = new ArrayList<>();

            while (res.moveToNext()) {
                itemList.add(
                        new listItem(
                                res.getInt(0),
                                res.getString(1),
                                res.getString(2),
                                res.getInt(3),
                                res.getFloat(4)));
            }

            itemAdapter adapter = new itemAdapter(this, itemList);

            recyclerView.setAdapter(adapter);
        }

    }
}
