package com.team15.lower.wellnessbaby.DBhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.team15.lower.wellnessbaby.Model.HEALTH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AyeNyeinThu on 27. 10. 2016.
 */
public class DBdiseasehelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "healthpart";

    // Contacts table name
    private static final String TABLE_HEALTH = "health";

    // Contacts Table Columns names
    private static final String KEY_ID="health_id";
    static final String KEY_NAME = "health_name";
    private static final String KEY_DESCRIPTON = "health_description";


    public DBdiseasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_HEALTH + "("
                + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT NOT NULL," + KEY_DESCRIPTON + " TEXT NOT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEALTH);

        // Create tables again
        onCreate(db);
    }

    public List<HEALTH> getAllContact() {
        List<HEALTH> healthList = new ArrayList<HEALTH>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_HEALTH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", ""+cursor.getString(1));
                HEALTH contact = new HEALTH();
                contact.setHealth_id(Integer.parseInt(cursor.getString(0)));
                contact.setHealth_name(cursor.getString(1));
                contact.setHealth_description(cursor.getString(2));

                // Adding contact to list
                healthList.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return healthList;
    }


    public HEALTH getdisease(String id){
        HEALTH disease=new HEALTH();

        String selectQuery = "SELECT * FROM "+ TABLE_HEALTH + " WHERE "+ KEY_ID + " = " + id;
        Log.i("Query",selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.i("Cursor",cursor.getCount()+"a");
        if (cursor.moveToFirst()) {
            do {
                // disease.setHel(Integer.parseInt(cursor.getString(0)));
                disease.setHealth_name(cursor.getString(1));
                disease.setHealth_description(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        return  disease;
    }



    public String[] getAllDiseaseName(){


        String selectQuery="SELECT * FROM "+ TABLE_HEALTH;
        Log.i("Query",selectQuery);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        Log.i("Cursor",cursor.getCount()+"");
        String[] array=new String[cursor.getCount()];

        int index=0;
        if(cursor.moveToFirst()){

            do{
                array[index] = cursor.getString(0) + " - " + cursor.getString(1);
                index++;
            }while (cursor.moveToNext());
        }
        Log.i("returning array", ""+array.length);
        cursor.close();
        return  array;
    }


}
