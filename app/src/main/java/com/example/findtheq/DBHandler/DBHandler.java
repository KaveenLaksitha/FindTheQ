package com.example.findtheq.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "localdb";
    private static final String TABLE_NAME = "user";

    private static final String ID_COL = "id";
    private static final String EMAIL_COL = "email";
    private static final String VEHICLE_TYPE_COL = "vehicleType";
    private static final String JOINED_STATION_ID_COL = "joinedStationID";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }

        @Override
    public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EMAIL_COL + " TEXT,"
                    + VEHICLE_TYPE_COL + " TEXT,"
                    + JOINED_STATION_ID_COL + " TEXT)";

            db.execSQL(query);
    }

    public void addNewUser(String email, String vehicleType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EMAIL_COL, email);
        values.put(VEHICLE_TYPE_COL, vehicleType);
        values.put(JOINED_STATION_ID_COL, "");

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<DbModel> readUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<DbModel> data = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                data.add(new DbModel(cursor.getString(1),
                        cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public void updateUserDetails(String email, String vehicleType, String stationID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EMAIL_COL, email);
        values.put(VEHICLE_TYPE_COL, vehicleType);
        values.put(JOINED_STATION_ID_COL, stationID);

        db.update(TABLE_NAME, values, "email=?", new String[]{email});
        db.close();
    }

    public Cursor  readeStationID(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE email LIKE " + "'" + email + "'";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }if (cursor.getCount() == 0){
            System.out.println("Error while reading joined stationID");
        }
        return cursor;

    }

    public void dropTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
