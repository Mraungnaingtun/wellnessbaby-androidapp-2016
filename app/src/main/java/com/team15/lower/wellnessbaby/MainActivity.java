package com.team15.lower.wellnessbaby;

import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.team15.lower.wellnessbaby.Model.Baby;
import com.team15.lower.wellnessbaby.DBhelper.DBBabyhelper;
import com.team15.lower.wellnessbaby.DBhelper.SQLHelper;
import com.team15.lower.wellnessbaby.Login_package.Registernew;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    String DB_PATH = "/data/data/com.team15.lower.wellnessbaby/databases";
    String DB_NAME = "healthpart";
    LinearLayout ll;

    Button health_btn;
    Button nutrition_btn;
    Button timeline_btn;
    Button important_btn;
    Button entertainment_btn;

    DBBabyhelper dbBabyhelper;
    byte[] image;
    int check = 0;

    SQLHelper sqlHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        image = getIntent().getByteArrayExtra("image");
        ImageView setting;

        sqlHelper = new SQLHelper(getApplicationContext(), "healthpart");
        db = sqlHelper.getDb();


        health_btn = (Button) findViewById(R.id.health_btn);
        nutrition_btn = (Button) findViewById(R.id.sec_Btn);
        timeline_btn = (Button) findViewById(R.id.right_btn);
        important_btn = (Button) findViewById(R.id.thrid_Btn);
        entertainment_btn = (Button) findViewById(R.id.four_Btn);
        setting=(ImageView) findViewById(R.id.setting);

        health_btn.setOnClickListener(this);
        nutrition_btn.setOnClickListener(this);
        timeline_btn.setOnClickListener(this);
        important_btn.setOnClickListener(this);
        entertainment_btn.setOnClickListener(this);


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,About.class));
            }
        });

        dbBabyhelper = new DBBabyhelper(this);

//        Log.i("DATABASE EXIST : ", "" + checkDataBase());
//        if (!checkDataBase()) {
//            if (CreateDir_NotExists(DB_PATH))
//                copyDataBase();
//
//        }
        this.check = 1;
        //Toast.makeText(MainActivity.this, "Press again to exit!!!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                check = 0;
            }
        }, 1500);

    }

    public boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    //create adir
    public static boolean CreateDir_NotExists(String path) {
        boolean ret = true;

        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("CREATE DIR : ", "Problem creating folder");
                ret = false;
            }
        }
        return ret;
    }


    // copying database
    private void copyDataBase() {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        Log.i("Database", "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = MainActivity.this.getAssets().open(DB_NAME);
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("Database", "New database has been copied to device!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.health_btn:

                Intent intent = new Intent(MainActivity.this, Health_Activity.class);
                startActivity(intent);
                break;

            case R.id.sec_Btn:
                Intent intent_two = new Intent(MainActivity.this, Nutrition_Activity.class);
                startActivity(intent_two);
                break;

            case R.id.right_btn:
                Baby baby = dbBabyhelper.getBaby("" + 1);
                if (baby.getName() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.alert_text_timeline)
                            .setMessage(R.string.alert_text_timeline_des);
                    builder.setPositiveButton(R.string.yeah, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            File file = new File(Environment.getExternalStorageDirectory() + "/wellness/");
                            file.delete();
                            Intent intent = new Intent(MainActivity.this, Registernew.class);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                } else {
                    Intent intent_thrid = new Intent(MainActivity.this, TimeLine.class);
                    startActivity(intent_thrid);
                }
                break;


            case R.id.thrid_Btn:
                Intent intent_one = new Intent(MainActivity.this, Knowledge.class);
                startActivity(intent_one);
                break;

            case R.id.four_Btn:
                Intent intent_four = new Intent(MainActivity.this, Ententainment_Activity.class);
                startActivity(intent_four);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder build=new AlertDialog.Builder(this);
        build.setTitle("အၾကံျပဳခ်က္");
        build.setMessage("app မွထြက္ရန္ေသရွာပါသလား");
        build.setPositiveButton("ဟုတ္ကဲ့", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        })
                .setNegativeButton("မထြက္ေသးပါ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        build.show();

    }
}

