package com.example.lab06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bai2";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "place";

    private static final String KEY_ID = "id";
    private static final String KEY_PLACE = "place";

    public DatabaseHandler2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_bai2_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT)", TABLE_NAME, KEY_ID, KEY_PLACE);
         db.execSQL(create_bai2_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop_bai2_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_bai2_table);
        onCreate(db);
    }
    public void adPlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PLACE, place.getPlace());



        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Place getPlace(int placeID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(placeID) },null, null, null);
        if(cursor != null)
            ((Cursor) cursor).moveToFirst();
        Place place= new Place(cursor.getInt(0), cursor.getString(1));

        return place;
    }
    public ArrayList<Place> getAllPlace() {
        ArrayList<Place> list = new ArrayList<Place>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Place place = new Place(cursor.getInt(0), cursor.getString(1));
            list.add(place);
            cursor.moveToNext();
        }
        return list;
    }

    public void updatePlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PLACE, place.getPlace());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(place.getId()) });
        db.close();
    }
    public void deletePlace(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ? ", new String[] { String.valueOf(id) });
        db.close();
    }
}
