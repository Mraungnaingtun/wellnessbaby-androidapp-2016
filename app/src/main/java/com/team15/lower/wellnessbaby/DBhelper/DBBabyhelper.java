package com.team15.lower.wellnessbaby.DBhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.team15.lower.wellnessbaby.Model.Baby;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AyeNyeinThu on 20. 10. 2016.
 */
public class DBBabyhelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "baby";
    private static final String TABLE_NAME = "babyinfo";

    private static final String baby_id = "baby_id";
    private static final String baby_name = "baby_name";
    private static final String baby_birthday = "baby_birthday";
    private static final String baby_weight = "baby_weight";
    private static final String baby_gender = "baby_gender";
    private static final String baby_height = "baby_height";

    public DBBabyhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BABY = "CREATE TABLE " + TABLE_NAME + "("
                + baby_id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + baby_name + " TEXT, "
                + baby_birthday + " TEXT, "
                + baby_weight + " TEXT, "
                + baby_gender + " TEXT, "
                + baby_height + " TEXT )";

        db.execSQL(CREATE_TABLE_BABY);
        Log.i("DbHelper", "database is successfully created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /**
     * adding child for
     *
     * @param baby
     */
    public void addChild(Baby baby) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(baby_name, baby.getName());
        values.put(baby_birthday, baby.getBirth_date());
        values.put(baby_weight, baby.getWeight());
        values.put(baby_gender, baby.getGender());
        values.put(baby_height, baby.getHeight());
        //insert
        db.insert(TABLE_NAME, null, values);
    }

    public int updateBaby(Baby baby) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(baby_name, baby.getName());
        values.put(baby_birthday, baby.getBirth_date());
        values.put(baby_weight, baby.getGender());
        values.put(baby_gender, baby.getWeight());
        values.put(baby_height, baby.getHeight());

        String[] selectionArgs = {String.valueOf((baby.getId()))};
        Log.i("UPDATE : ", "" + baby.getId());
        return db.update(TABLE_NAME, values, baby_id + "= ?", selectionArgs);
    }

    /**
     * getting baby data from database
     */
    public Baby getBaby(String id) {
        Baby chitbaby = new Baby();

        //Select Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + baby_id + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through rows and adding to model
        if (cursor.moveToFirst()) {
            do {
                // chitbaby.setId(Integer.parseInt(cursor.getString(0)));
                chitbaby.setName(cursor.getString(1));
                chitbaby.setBirth_date(cursor.getString(2));
                chitbaby.setWeight(cursor.getString(3));
                chitbaby.setGender(cursor.getString(4));
                chitbaby.setHeight(cursor.getString(5));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return chitbaby;
    }

    public List<Baby> getAllBaby() {
        List<Baby> babyList = new ArrayList<Baby>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", "" + cursor.getString(1));
                //create baby object
                Baby b = new Baby();
                b.setId(Integer.parseInt(cursor.getString(0)));
                b.setName(cursor.getString(1));
                b.setBirth_date(cursor.getString(2));
                b.setWeight(cursor.getString(3));
                b.setGender(cursor.getString(4));
                b.setHeight(cursor.getString(5));
                //Adding all baby
                babyList.add(b);
            } while (cursor.moveToNext());
        }

        //return
        cursor.close();
        return babyList;
    }

    public String[] getAllBabyName() {

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String[] array = new String[cursor.getCount()];
        int index = 0;
        if (cursor.moveToFirst()) {
            do {
                array[index] = cursor.getString(0) + " - " + cursor.getString(1);
                index++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return array;
    }

}
