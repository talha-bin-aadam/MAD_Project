package com.example.rar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class bookNow extends AppCompatActivity {

    SQLiteHelper db;
    int bookedSeats;
    int availSeats;
    EditText seatsInput;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);

        db = new SQLiteHelper(this);
        data = getIntent().getExtras();
        seatsInput = findViewById(R.id.bookedSeats);
    }

    public void go(View view){
        bookedSeats = Integer.valueOf(seatsInput.getText().toString());
        availSeats = db.t3getSeats(data.getInt("rideID", 0));

        if(bookedSeats > availSeats)
            Toast.makeText(this, "seats not available", Toast.LENGTH_LONG).show();
        else{
            SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("rideID", data.getInt("rideID", 0));
            editor.putInt("booked seats", bookedSeats);
            editor.apply();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frameLayout, new rideConfirm());
            transaction.commit();


        }
    }
}
