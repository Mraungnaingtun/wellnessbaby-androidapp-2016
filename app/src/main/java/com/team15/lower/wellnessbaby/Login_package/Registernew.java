package com.team15.lower.wellnessbaby.Login_package;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.team15.lower.wellnessbaby.Close.PrefManager;
import com.team15.lower.wellnessbaby.DBhelper.DBBabyhelper;
import com.team15.lower.wellnessbaby.MainActivity;
import com.team15.lower.wellnessbaby.Model.Baby;
import com.team15.lower.wellnessbaby.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by Administrator on 10/14/2016.
 */
public class Registernew extends AppCompatActivity {

    private PrefManager prefManager;
    EditText baby_name = null;
    EditText baby_weight_pound;
    EditText baby_weight_aungsa;

    Spinner day_spinner;
    Spinner month_spinner;
    Spinner year_spinner;
    ImageButton cancel;


    String msg;
    ImageView male;
    ImageView female;
    ImageButton register;
    ImageView baby_photo;
    Bitmap photo = null;
    CheckBox name_present;
    DBBabyhelper dbBabyhelper;
    BitmapDrawable bitmapData;
    String height = "";
    String note = "ddasdf";


    String gender = null;
    String birthdate;
    String baby_weight;
    private static boolean malefirst = true;
    private static boolean femalefirst = true;
    public static final int REQUEST_CAPTURE = 1;
    private static int IMAGE_GALLERY_REQUEST = 20;
    List<Baby> babyList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        dbBabyhelper = new DBBabyhelper(this);
        baby_name = (EditText) findViewById(R.id.baby_name);
        baby_weight_pound = (EditText) findViewById(R.id.baby_weight);
        baby_weight_aungsa = (EditText) findViewById(R.id.weight_aungsa);
        day_spinner = (Spinner) findViewById(R.id.date);
        month_spinner = (Spinner) findViewById(R.id.month);
        year_spinner = (Spinner) findViewById(R.id.year);
        cancel = (ImageButton) findViewById(R.id.cancel_button);
        name_present = (CheckBox) findViewById(R.id.no_name);
        /**
         * cancel button for registering data of baby
         * but user can be inputted data of baby
         */
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        /**
         * adding data to spinner for day
         */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.day, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        day_spinner.setAdapter(adapter);
        /**
         * adding data to spinner for month
         */
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.month, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        month_spinner.setAdapter(adapter1);
        /**
         *adding data to spinner for year
         */
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        year_spinner.setAdapter(adapter2);
        register = (ImageButton) findViewById(R.id.register);


        /**
         * choice  for male
         */
        male = (ImageButton) findViewById(R.id.boy);

        male.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedMale(true);
            }
        });
        /**
         * choice for female
         */
        female = (ImageButton) findViewById(R.id.girl);
        female.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectedFemale(true);
            }
        });
        /**
         * inputting photo data by using gallary or Camera
         */
        baby_photo = (ImageView) findViewById(R.id.input_babyimage);
        baby_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Registernew.this);
                builder.setTitle("Choose your like")
                        .setItems(R.array.chosing_for_photo, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] strarr = getResources().getStringArray(R.array.chosing_for_photo);
                                msg = strarr[which];
                                switch (msg) {
                                    case "Choose Photo":
                                        selectPhoto(v);
                                        break;
                                    case "Take Photo":
                                        launchCamera(v);
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        /**
         * finally registering baby data to TimeLine
         * save data to timeline by Baby class
         * this is save btn click event
         * our main work for registering baby data is here ,we can work for inputting data of the users
         */

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index_day = day_spinner.getSelectedItemPosition();
                int index_month = month_spinner.getSelectedItemPosition();
                int index_year = year_spinner.getSelectedItemPosition();


                Log.i("PROCESS:", "ADDED");

                /**
                 *taking indexes for baby birth date
                 */
                String[] day_array = getResources().getStringArray(R.array.day);
                String[] month_array = getResources().getStringArray(R.array.month);
                String[] year_array = getResources().getStringArray(R.array.year);
                birthdate = day_array[index_day] + "/" + month_array[index_month] + "/" + year_array[index_year];

                if(baby_weight_pound.getText().length() != 0 && baby_weight_aungsa.getText().length() != 0)
                baby_weight = baby_weight_pound.getText().toString()+" lb"+"\t\t"+ baby_weight_aungsa.getText().toString()+" as";

                /**
                 * now you can take User's selected data with indexes
                 */
                if (malefirst == false) {
                    gender = "ေယာက္်ားေလး";
                } else if(femalefirst== false)
                    gender = "မိန္းကေလး";


/**
 ************************************************************************************************************************************************************************************
  */
                if (baby_name.getText().length() == 0 && index_day == 0 || index_month == 0 || index_year == 0 && baby_weight_pound.getText().length() == 0 && baby_weight_aungsa.getText().length() == 0 && gender == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Registernew.this);
                    builder.setTitle("----------အၾကံေပးခ်က္----------");
                    builder.setMessage("သင့္ကေလးရဲ႕ အခ်က္အလက္ကိုေသခ်ာစြာျဖည့္စြပ္ေပးပါ");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();



                /*else if (index_day == 0 || index_month == 0 || index_year == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Registernew.this);
                    builder.setTitle("----------အၾကံေပးခ်က္----------");
                    builder.setMessage("\n" +
                            "သင့္ကေလးေမြးေန႔ကိုေသခ်ာစြာသတ္မွတ္ေပးပါခင္ဗ်ား");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    builder.show();
                    // register.setClickable(false);
                } else if (baby_weight_pound.getText().length() == 0 && baby_weight_aungsa.getText().length() == 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Registernew.this);
                    builder.setTitle("----------အၾကံေပးခ်က္----------");
                    builder.setMessage("သင့္ကေလးရဲ႕အေလးခ်ိန္ကိုသတ္မွတ္ေပးရန္လိုအပ္ပါသည္");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();*/
             /*   } else if (gender == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Registernew.this);
                    builder.setTitle("----------အၾကံေပးခ်က္----------");
                    builder.setMessage("ကေလးကေယာက္်ားကေလးလား မိန္းကေလးလားခင္ဗ်ာ");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();*/

                } else if (photo == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Registernew.this);
                    builder.setTitle("----------အၾကံေပးခ်က္----------");
                    builder.setMessage("\n" +
                            "ကေလးရဲ႕ပုံေလးကိုသတ္မွတ္ေပးပါ");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                } else {
                    Baby babychay = new Baby(0, baby_name.getText().toString(),birthdate,baby_weight, gender, height);
                    dbBabyhelper.addChild(babychay);
                    saveImageToExternalStorage(photo);
                    launchHomeScreen();
                }
                //   Bitmap empty = Bitmap.createBitmap(photo.getWidth(), photo.getHeight(), photo.getConfig());
        }
        });
    }

    /**
     *end of onCreate method
     * starting function to choose image from gallary
     */

    /**
     * public  static byte[] getBitmapAsByteArray(Bitmap bitmap){
     * <p>
     * ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
     * bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputstream);
     * return outputstream.toByteArray();
     * }
     */
    private void launchHomeScreen() {
        finish();
        startActivity(new Intent(Registernew.this, MainActivity.class));
    }


    public void selectedMale(boolean yesorno) {

        if (yesorno == true && malefirst == true) {

            Drawable drawable = getResources().getDrawable(R.drawable.man_select);
            male.setImageDrawable(drawable);
            malefirst = false;
            femalefirst = true;
            Drawable drawable1 = getResources().getDrawable(R.drawable.women);
            female.setImageDrawable(drawable1);

        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.man);
            male.setImageDrawable(drawable);
            malefirst = true;
        }
    }

    public void selectedFemale(boolean yesorno) {

        if (yesorno == true && femalefirst == true) {
            Drawable drawable = getResources().getDrawable(R.drawable.women_select);
            female.setImageDrawable(drawable);
            femalefirst = false;
            malefirst = true;
            Drawable drawable2 = getResources().getDrawable(R.drawable.man);
            male.setImageDrawable(drawable2);
        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.women);
            female.setImageDrawable(drawable);
            femalefirst = true;
        }
    }


    public void selectPhoto(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        String pictureDirectoryPath = pictureDirectory.getPath();

        //finally ,get a URL representation
        Uri data = Uri.parse(pictureDirectoryPath);

        //set the data and type .Get all image types
        photoPickerIntent.setDataAndType(data, "image/*");

        //we will invoke this activity ,and get something back from it.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                Uri imageUri = data.getData();
                InputStream inpuStream;

                try {
                    inpuStream = getContentResolver().openInputStream(imageUri);
                    photo = BitmapFactory.decodeStream(inpuStream);
                    baby_photo.setImageBitmap(photo);

                    // saveImageToExternalStorage(photo);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
            //*****************************************************************************************************************************
        }
        if (requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            photo = (Bitmap) extras.get("data");
            baby_photo.setImageBitmap(photo);
            // scaleImage(baby_photo);
            //saveImageToExternalStorage(photo);
        }
    }

    public boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CAPTURE);
    }

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
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private int dpToPx(int dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);

    }

    private void saveImageToExternalStorage(Bitmap finalBitmap) {

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(Environment.getExternalStorageDirectory() + "/wellness");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

    }
}
