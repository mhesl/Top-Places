package com.example.nearbyplaces.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NearbyPlaces.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "places";
    //Table columns
    private static final String VENUE_NAME = "venue_name";
    private static final String PLACE_NAME = "place_name";
    private static final String PLACE_ID = "place_name";
    private static DataBaseHelper instance;
    private SQLiteDatabase db;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" +
                PLACE_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                PLACE_NAME + " TEXT," + // Define a foreign key
                VENUE_NAME + " TEXT" +
                ")";
        db.execSQL(CREATE_PLACES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

}
