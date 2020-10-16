package com.example.nearbyplaces.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NearbyPlaces.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "places";
    //Table columns
    private static final String VENUE_NAME = "venue_name";
    private static final String PLACE_NAME = "place_name";
    private static final String PLACE_ADDRESS = "place_address";
    private static final String PLACE_CITY = "place_city";
    private static final String PLACE_DISTANCE = "place_distance";
    private static final String PLACE_CROSS = "place_cross";
    private static final String PLACE_ID = "place_id";


    private static DataBaseHelper instance = null;
    private SQLiteDatabase db;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // singleton pattern
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
                PLACE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLACE_NAME + " TEXT, " +
                VENUE_NAME + " TEXT, " +
                PLACE_ADDRESS + " TEXT, " +
                PLACE_CITY + " TEXT, " +
                PLACE_CROSS + " TEXT, " +
                PLACE_DISTANCE + " INTEGER " +
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


    public void addPlace(DataBaseModel model) {
        db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(VENUE_NAME, model.getVenueName());
            values.put(PLACE_NAME, model.getPlaceName());
            values.put(PLACE_ADDRESS, model.getCompleteAddress());
            values.put(PLACE_CITY, model.getCity());
            values.put(PLACE_CROSS, model.getCrossStreet());
            values.put(PLACE_DISTANCE, model.getDistance());


            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }


    public List<DataBaseModel> getAllPosts() {
        List<DataBaseModel> models = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    DataBaseModel model = new DataBaseModel(
                            cursor.getString(cursor.getColumnIndex(VENUE_NAME)),
                            cursor.getString(cursor.getColumnIndex(PLACE_NAME)),
                            cursor.getString(cursor.getColumnIndex(PLACE_ADDRESS)),
                            cursor.getString(cursor.getColumnIndex(PLACE_CROSS)),
                            cursor.getString(cursor.getColumnIndex(PLACE_CITY)),
                            cursor.getInt(cursor.getColumnIndex(PLACE_DISTANCE))
                    );
                    models.add(model);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return models;
    }

    public void deleteAllPosts() {
        db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_NAME, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }


}
