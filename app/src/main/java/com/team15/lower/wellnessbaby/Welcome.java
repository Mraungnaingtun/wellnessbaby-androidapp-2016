package com.team15.lower.wellnessbaby;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.team15.lower.wellnessbaby.Close.PrefManager;
import com.team15.lower.wellnessbaby.Login_package.Registernew;

import java.io.File;

public class Welcome extends AppCompatActivity {

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.welcome_letter);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        linearLayout.setAnimation(animation);

        prefManager = new PrefManager(this);
        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation1) {
                    final AlertDialog.Builder build = new AlertDialog.Builder(Welcome.this);
                    build.setTitle("မဂၤလာပါ");
                    build.setMessage("\n" +
                            "\"Wellness Baby\" application မွ ၾကိဳဆိုပါတယ္။\n" +
                            "ဒီ app ကေတာ့ လူၾကီးမင္းတို့၏ ကေလးငယ္မ်ားကို\n" +
                            "က်န္းမာဖြံျဖိဳးေသာ ကေလးငယ္မ်ားျဖစ္ေအာင္\n" +
                            "ရည္ညြန္းပီးေတာ့ ထုတ္ထားတာ ျဖစ္ပါတယ္။\n" +
                            "ဒီ app ကို စတင္သံုးစြဲေတာ့မယ္ ဆိုရင္ ကေလးရဲ့\n" +
                            "ကိုယ္ေရးအခ်က္အလက္ေတြကို ျဖည့္ေပးရမွာ ျဖစ္ပါတယ္။\n" +
                            "ဒီ app ကို သံုးစြဲတဲ့အတြက္ ေက်းဇူးတင္ပါတယ္။");


                    build.setCancelable(false);

                    build.setPositiveButton("ျဖည့္မည္", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                            File myDir = new File(Environment.getExternalStorageDirectory() + "/wellness");

                            if (myDir.isDirectory()) {
                                String[] children = myDir.list();
                                for (int i = 0; i < children.length; i++) {
                                    new File(myDir, children[i]).delete();
                                }
                            }

                           /* File file = new File(Environment.getExternalStorageDirectory() + "/wellness/");
                            file.delete();*/

                            launchRegister();
                        }
                    });
                    build.show();
                }

                @Override
                public void onAnimationRepeat(Animation animation2) {
                }
            });
        } else {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation1) {
                    launchHomeScreen();
                }


                @Override
                public void onAnimationRepeat(Animation animation2) {
                }
            });
            //builder=new AlertDialog.Builder(this);


        }
    }

    private void launchHomeScreen() {
        startActivity(new Intent(Welcome.this, MainActivity.class));
        Welcome.this.finish();

    }

    private void launchRegister() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(Welcome.this, Registernew.class));
        Welcome.this.finish();
    }

    public void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }
}
