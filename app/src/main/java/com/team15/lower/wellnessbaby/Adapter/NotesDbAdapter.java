package com.team15.lower.wellnessbaby.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by AyeNyeinThu on 26. 10. 2016.
 */
public class NotesDbAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notepaddatabase.db";
    private static final String TABLE = "notepad";


    public static final String KEY_ID = "noteid";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_DATE = "date";
    public static final String KEY_BODY = "body";

    public NotesDbAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_BABY = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + KEY_TITLE + " TEXT, "
                + KEY_SUBJECT + " TEXT, "
                + KEY_DATE + " TEXT, "
                + KEY_BODY + " TEXT, )";

        db.execSQL(CREATE_TABLE_BABY);
        Log.i("NOTESDBADAPTER", "database is successfully created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);

        // Create tables again
        onCreate(db);
    }


}
