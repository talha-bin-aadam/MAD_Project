package com.example.rar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class signUp extends AppCompatActivity {

    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new SQLiteHelper(this);

        db.t3rideInfoInsertData();
    }

    public void gotoTravelInfo(View view){
        EditText nameInput = findViewById(R.id.name);
        EditText emailInput = findViewById(R.id.email);
        EditText passInput = findViewById(R.id.pass);

        long userID = db.t1userInfoInsertData(nameInput.getText().toString(),
                emailInput.getText().toString(),
                passInput.getText().toString());

        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong("userID", userID);
        editor.apply();

        Intent intent = new Intent(this, travelInfo.class);
        startActivity(intent);
    }
}
