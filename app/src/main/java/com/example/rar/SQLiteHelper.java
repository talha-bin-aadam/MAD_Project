package com.example.rar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.mbms.StreamingServiceInfo;

public class SQLiteHelper  extends SQLiteOpenHelper {

    public static final String database = "travel.db";

    public static final String t1userInfo = "userInfo";
    public static final String t1c1id = "id";
    public static final String t1c2name = "name";
    public static final String t1c3email = "email";
    public static final String t1c4pass = "password";

    public static final String t2travelInfo = "travelInfo";
    public static final String t2c1userId = "userID";
    public static final String t2c2currentLoc = "currentLocation";
    public static final String t2c3dest = "destination";
    public static final String t2c4date = "date";

    public static final String t3rideInfo = "rideInfo";
    public static final String t3c1id = "id";
    public static final String t3c2depLoc = "departureLocation";
    public static final String t3c3dest = "destination";
    public static final String t3c4seats = "availableSeats";
    public static final String t3c5depTime = "departureTime";
    public static final String t3c6arrivalTime = "arrivalTime";
    public static final String t3c7date = "date";
    public static final String t3c8fair = "fair";

    public static final String t4ticketInfo = "ticketInfo";
    public static final String t4c1id = "id";
    public static final String t4c2userID = "userID";
    public static final String t4c3rideID = "rideID";
    public static final String t4c4seats = "noOfSeats";
    public static final String t4c5fair = "totalFair";

    public SQLiteHelper(Context context) {

        super(context, database, null, 1);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + t1userInfo + "("
                + t1c1id + " integer primary key autoincrement,"
                + t1c2name + " text not null,"
                + t1c3email + " text,"
                + t1c4pass + " text not null)");

        db.execSQL("create table " + t2travelInfo + "("
                + t2c1userId + " integer primary key,"
                + t2c2currentLoc + " text not null,"
                + t2c3dest + " text not null,"
                + t2c4date + " text not null,"
                + "foreign key(" + t2c1userId + ") "
                + "references " + t1userInfo + "(" + t1c1id + ")"
                + "on update cascade on delete cascade)");

        db.execSQL("create table " + t3rideInfo + "("
                + t3c1id + " integer primary key autoincrement,"
                + t3c2depLoc + " text not null,"
                + t3c3dest + " text not null,"
                + t3c4seats + " integer not null,"
                + t3c5depTime + " text not null,"
                + t3c6arrivalTime + " text not null,"
                + t3c7date + " text not null,"
                + t3c8fair + " real not null)");

        db.execSQL("create table " + t4ticketInfo + "("
                + t4c1id + " integer primary key autoincrement,"
                + t4c2userID + " integer not null,"
                + t4c3rideID + " integer not null,"
                + t4c4seats + " integer not null,"
                + t4c5fair + " real not null,"
                + "foreign key(" + t4c2userID + ") "
                + "references " + t1userInfo + "(" + t1c1id + ") "
                + "on update cascade on delete cascade,"
                + "foreign key(" + t4c3rideID + ") "
                + "references " + t3rideInfo + "(" + t3c1id + ") "
                + "on update cascade on delete cascade)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long t1userInfoInsertData(String name, String email, String pass){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(t1c2name, name);
        contentValues.put(t1c3email, email);
        contentValues.put(t1c4pass, pass);

        return db.insert(t1userInfo, null, contentValues);
    }

    public void t2travelInfoInsertData(long userID, String currentLoc, String dest, String date){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(t2c1userId, userID);
        contentValues.put(t2c2currentLoc, currentLoc);
        contentValues.put(t2c3dest, dest);
        contentValues.put(t2c4date, date);

        db.insert(t2travelInfo, null, contentValues);
    }

    public void t3rideInfoInsertData(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(t3c2depLoc, "Lahore");
        contentValues.put(t3c3dest, "Islamabad");
        contentValues.put(t3c4seats, 25);
        contentValues.put(t3c5depTime, "10:45AM");
        contentValues.put(t3c6arrivalTime, "04:00PM");
        contentValues.put(t3c7date, "2019/6/26");
        contentValues.put(t3c8fair, 1500);
        db.insert(t3rideInfo, null, contentValues);

        contentValues.put(t3c2depLoc, "Lahore");
        contentValues.put(t3c3dest, "Faisalabad");
        contentValues.put(t3c4seats, 9);
        contentValues.put(t3c5depTime, "07:00AM");
        contentValues.put(t3c6arrivalTime, "10:00AM");
        contentValues.put(t3c7date, "2019/6/27");
        contentValues.put(t3c8fair, 600);
        db.insert(t3rideInfo, null, contentValues);

        contentValues.put(t3c2depLoc, "Karachi");
        contentValues.put(t3c3dest, "Lahore");
        contentValues.put(t3c4seats, 20);
        contentValues.put(t3c5depTime, "10:00PM");
        contentValues.put(t3c6arrivalTime, "11:00AM");
        contentValues.put(t3c7date, "2019/6/28");
        contentValues.put(t3c8fair, 3500);
        db.insert(t3rideInfo, null, contentValues);

        contentValues.put(t3c2depLoc, "Khanewal");
        contentValues.put(t3c3dest, "Multan");
        contentValues.put(t3c4seats, 27);
        contentValues.put(t3c5depTime, "03:30PM");
        contentValues.put(t3c6arrivalTime, "04:45PM");
        contentValues.put(t3c7date, "2019/6/29");
        contentValues.put(t3c8fair, 3500);
        db.insert(t3rideInfo, null, contentValues);

        contentValues.put(t3c2depLoc, "Karachi");
        contentValues.put(t3c3dest, "Lahore");
        contentValues.put(t3c4seats, 8);
        contentValues.put(t3c5depTime, "09:00AM");
        contentValues.put(t3c6arrivalTime, "10:30PM");
        contentValues.put(t3c7date, "2019/6/28");
        contentValues.put(t3c8fair, 3700.99);
        db.insert(t3rideInfo, null, contentValues);

        contentValues.put(t3c2depLoc, "Islamabad");
        contentValues.put(t3c3dest, "Jhang");
        contentValues.put(t3c4seats, 33);
        contentValues.put(t3c5depTime, "12:00AM");
        contentValues.put(t3c6arrivalTime, "05:00PM");
        contentValues.put(t3c7date, "2019/6/29");
        contentValues.put(t3c8fair, 1700);
        db.insert(t3rideInfo, null, contentValues);

        contentValues.put(t3c2depLoc, "Karachi");
        contentValues.put(t3c3dest, "Lahore");
        contentValues.put(t3c4seats, 16);
        contentValues.put(t3c5depTime, "05:00PM");
        contentValues.put(t3c6arrivalTime, "08:30AM");
        contentValues.put(t3c7date, "2019/6/28");
        contentValues.put(t3c8fair, 3450);
        db.insert(t3rideInfo, null, contentValues);

    }

    public void t4ticketInfoInsertData(long userID, int rideID, int seats, float fair){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(t4c2userID, userID);
        contentValues.put(t4c3rideID, rideID);
        contentValues.put(t4c4seats, seats);
        contentValues.put(t4c5fair, fair);

        db.insert(t4ticketInfo, null, contentValues);
    }

/*
    public int t1getUserID(String name, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select " + t1c1id + " from " + t1userInfo + " where " + t1c2name + " = ? and " + t1c3email + " = ? and " + t1c4pass + " = ?", new String[]{name, email, pass});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
*/
    public Cursor t2getTravelInfo(long userID){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select " + t2c2currentLoc + ", " + t2c3dest + ", " + t2c4date + " from " + t2travelInfo + " where " + t2c1userId + " = ?", new String[]{String.valueOf(userID)});
    }


    public Cursor t3getRidesInfo(String currentLoc, String dest, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select " + t3c1id + ", " + t3c5depTime + ", " + t3c6arrivalTime + ", " + t3c4seats + ", " + t3c8fair + " from " + t3rideInfo + " where " + t3c2depLoc + " = ? and " + t3c3dest + " = ? and " + t3c7date + " = ?", new String[]{currentLoc, dest, date});
    }

    public int t3getSeats(int rideID){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select " + t3c4seats + " from " + t3rideInfo + " where " + t3c1id + " = ?", new String[]{String.valueOf(rideID)});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public float t3getFair(int rideID){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select " + t3c8fair + " from " + t3rideInfo + " where " + t3c1id + " = ?", new String[]{String.valueOf(rideID)});
        cursor.moveToFirst();
        return cursor.getFloat(0);
    }
    public void t3updateSeats(int rideID, int seats){

        SQLiteDatabase db = this.getWritableDatabase();
        int preSeats = t3getSeats(rideID);

        ContentValues contentValues = new ContentValues();
        contentValues.put(t3c4seats, preSeats-seats);

        db.update(t3rideInfo, contentValues, t3c1id + " = ?", new String[] {String.valueOf(rideID)});
    }

}
