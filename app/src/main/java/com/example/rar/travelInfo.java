package com.example.rar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class travelInfo extends AppCompatActivity {

    String currentLocInput;
    String destInput;

    TextView dateInput;

    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);

        Spinner spn_currentLoc = findViewById(R.id.currentLoc), spn_dest = findViewById(R.id.dest);

        final String cityCurrentLoc[] = {"Current Location",
                "Lahore",
                "Islamabad",
                "Multan",
                "Faisalabad",
                "Karachi",
                "Khanewal",
                "Jhang",
                "Peshawar"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, cityCurrentLoc);
        spn_currentLoc.setAdapter(adapter1);
        spn_currentLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                currentLocInput = cityCurrentLoc[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final String cityDest[] = {"Destination",
                "Lahore",
                "Multan",
                "Faisalabad",
                "Karachi",
                "Khanewal",
                "Jhang",
                "Peshawar"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, cityDest);
        spn_dest.setAdapter(adapter2);
        spn_dest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                destInput = cityDest[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dateInput = findViewById(R.id.date);
        db = new SQLiteHelper(this);
    }

    public void datePicker(View view){

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener;

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month++;
                dateInput.setText(year + "/" + month + "/" + day);
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(travelInfo.this, dateSetListener, year, month, day);
        dialog.show();

    }

    public void gotoRidesData(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);

        db.t2travelInfoInsertData(sharedPreferences.getLong("userID", 0),
                currentLocInput, destInput, dateInput.getText().toString());

        Intent intent = new Intent(this, ridesData.class);
        startActivity(intent);
    }

}
