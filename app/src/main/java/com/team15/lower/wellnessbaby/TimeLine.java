package com.team15.lower.wellnessbaby;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.team15.lower.wellnessbaby.DBhelper.DBBabyhelper;
import com.team15.lower.wellnessbaby.Login_package.Registernew;
import com.team15.lower.wellnessbaby.Model.Baby;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class TimeLine extends AppCompatActivity {


    List<Baby> babyList;
    DBBabyhelper dbBabyhelper;
    BitmapDrawable bitmapData;
    private int updateid = 1;
    String[] baby_array;
    String[] baby_array1;
    EditText baby_name;
    EditText birthday;
    EditText gender;
    EditText weight;
    EditText height;
    TextView notepad;
    ImageView user_image;
    FloatingActionButton fab;
    Toolbar toolbar;
    EditText age_letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        /**
         * creating db object to work with database --------------------
         * so nane dbBabyhelper
         */

        dbBabyhelper = new DBBabyhelper(TimeLine.this);
        /**
         * join data to my form
         * now let find id and connect
         */
        baby_name = (EditText) findViewById(R.id.achay_name);
        birthday = (EditText) findViewById(R.id.birthday_edittext);
        gender = (EditText) findViewById(R.id.gender_text);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        user_image = (ImageView) findViewById(R.id.user_image);
        age_letter=(EditText)findViewById(R.id.age_letter);


        /**
         * inputData from table---------------------------------------------------------------------
         */
        inputData();
        /**
         * default inputting data or first child to user interface ---------------------------------
         */

       fab = (FloatingActionButton) findViewById(R.id.fab_one);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                setCursor();
                Log.i("PROCESS : ", "UPDATE");

                String name = baby_name.getText().toString();
                String birthdate = birthday.getText().toString();
                String gen = gender.getText().toString();
                String wei = weight.getText().toString();
                String hei = height.getText().toString();
                Baby newBaby = new Baby(updateid, name, birthdate, gen, wei, hei);
                dbBabyhelper.updateBaby(newBaby);
                removeCursor();
                fab.hide();
            }
        });

    }
    /**------------------------------------------------------------------------------------------------------------------------------------
     * ending on create method -------------------------------------------------------------------------------------------------------------------------------------------------------
     * getting baby data form database for each baby-----------------------------------------------------------------------------------------
     */


    /**
     * for taking user input image to timeline -----------------------------    --------------
     *
     * @param i
     */
    private void loadImage(int i) {
        File file = new File(Environment.getExternalStorageDirectory() + "/wellness/");
        File imageList[] = file.listFiles();

        Log.e("Image: " + i + ": path", imageList[i].getAbsolutePath());
        Bitmap b = BitmapFactory.decodeFile(imageList[i].getAbsolutePath());
        user_image.setImageBitmap(b);
        //scaleImage(user_image);
    }

    /**
     * default inputting method--------    ---------   -------
     */
    public void inputData() {
        Baby baby_obj;
        // babyList = dbBabyhelper.getAllBaby();
        Log.i("Got all baby", "true");
        baby_obj = dbBabyhelper.getBaby("" + 1);
        loadImage(0);
        baby_name.setText(baby_obj.getName());
        birthday.setText(baby_obj.getBirth_date());
        gender.setText(baby_obj.getGender());
        weight.setText(String.valueOf(baby_obj.getWeight()));
        height.setText(baby_obj.getHeight());
        removeCursor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id==R.id.home)
        {
           finish();
        }else if (id == R.id.menu_new) {
            finish();
            startActivity(new Intent(TimeLine.this, Registernew.class));

        } else if (id == R.id.choose) {

            /**
             *only see for all babies----------------------------------------------------------------------------------------------------
             */

            baby_array = dbBabyhelper.getAllBabyName();
            AlertDialog.Builder builder = new AlertDialog.Builder(TimeLine.this);
            builder.setTitle("ကေလးမ်ား");
            builder.setItems(baby_array, new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {


                    updateid = Integer.parseInt(baby_array[which].split("-")[0].trim());

                    loadImage(updateid - 1);
                    Baby baby = dbBabyhelper.getBaby("" + updateid);
                    baby_name.setText(baby.getName());
                    birthday.setText(baby.getBirth_date());
                    gender.setText(baby.getGender());
                    weight.setText(String.valueOf(baby.getWeight()));
                    height.setText(baby.getHeight());
                    //updated data
                    removeCursor();
                }
            });
            builder.show();
            /**
             * really take data to update custom----------------------------------------------------------------------------------------
             */

        } else if (id == R.id.update) {

            baby_array1 = dbBabyhelper.getAllBabyName();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(TimeLine.this);
            builder1.setTitle("ထပ္ျပင္မည့္ကေလးကိုေရႊးပါ");
            builder1.setItems(baby_array1, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    updateid = Integer.parseInt(baby_array1[which].split("-")[0].trim());

                    loadImage(updateid - 1);

                    Baby baby1 = dbBabyhelper.getBaby("" + updateid);

                    baby_name.setText(baby1.getName());
                    birthday.setText(baby1.getBirth_date());
                    gender.setText(baby1.getGender());
                    weight.setText(String.valueOf(baby1.getWeight()));
                    height.setText(baby1.getHeight());
                    //updated data
                    setCursor();
                    fab.show();
                }
            });
            builder1.show();


        } else {
           this.finish();
        }


        return super.onOptionsItemSelected(item);
    }


    /**
     * if you want to load all babies --************************************
     */

    public void loadAllBaby() {

        String strALlString = "";
        babyList = dbBabyhelper.getAllBaby();

        for (Baby baby : babyList) {
            String strBaby = baby.getId() + ",";
            strBaby += baby.getName() + ",";
            strBaby += baby.getBirth_date() + ",";
            strBaby += baby.getWeight() + ",";
            strBaby += baby.getGender();
            strALlString += strBaby + "\n\n";
        }
        // textView.setText(strALlString);
    }


/*    *//**
     * scaling inputting image from sdcard
     *
     * @param
     * @throws NoSuchElementException
     *//*
    private void scaleImage(ImageView view) throws NoSuchElementException {
        // Get bitmap from the the ImageView.
        Bitmap bitmap = null;

        try {
            Drawable drawing = view.getDrawable();
            bitmap = ((BitmapDrawable) drawing).getBitmap();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("No drawable on given view");
        } catch (ClassCastException e) {
            // Check bitmap is Ion drawable
            // bitmap = Ion.with(view).getBitmap();
        }

        // Get current dimensions AND the desired bounding box
        int width = 0;

        try {
            width = bitmap.getWidth();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Can't find bitmap on given view/drawable");
        }

        int height = bitmap.getHeight();
        int bounding = dpToPx(250);
        Log.i("Test", "original width = " + Integer.toString(width));
        Log.i("Test", "original height = " + Integer.toString(height));
        Log.i("Test", "bounding = " + Integer.toString(bounding));

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Log.i("Test", "xScale = " + Float.toString(xScale));
        Log.i("Test", "yScale = " + Float.toString(yScale));
        Log.i("Test", "scale = " + Float.toString(scale));

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        Log.i("Test", "scaled width = " + Integer.toString(width));
        Log.i("Test", "scaled height = " + Integer.toString(height));

        // Apply the scaled bitmap
        bitmapData = result;
        String imgBitmap = "android.graphics.drawable.BitmapDrawable@41f7e610";
        Log.i("Bitmalp Drawable : ", result + "");
        view.setImageDrawable(result);

        // Now change ImageView's dimensions to match the scaled image
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);

        Log.i("Test", "done");
    }*/

    private int dpToPx(int dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);

    }


    private void removeCursor() {
        baby_name.setFocusable(false);
        birthday.setFocusable(false);
        gender.setFocusable(false);
        weight.setFocusable(false);
        height.setFocusable(false);
        age_letter.setFocusable(false);
    }

    private void setCursor() {
        baby_name.setFocusableInTouchMode(true);
        baby_name.requestFocus();
        birthday.setFocusableInTouchMode(true);
        gender.setFocusableInTouchMode(true);
        weight.setFocusableInTouchMode(true);
        height.setFocusableInTouchMode(true);
    }

    @Override
    public void onBackPressed() {

        //Intent i=new Intent("android:intent.action.MAIN");
        //  startActivity(i);
        super.finish();
    }

    private void calculator(){

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date date1=simpleDateFormat.parse(String.valueOf(date));
        }catch (ParseException pe)
        {

        }
    }
}
