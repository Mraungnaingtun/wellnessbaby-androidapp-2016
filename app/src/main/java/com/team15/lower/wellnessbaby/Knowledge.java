package com.team15.lower.wellnessbaby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.team15.lower.wellnessbaby.Child_Right.ChildRight;

public class Knowledge extends AppCompatActivity {

    Button child_right;
    Button advice_btn;
    Button article;
    LinearLayout ll;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        //fdsadfsdaf

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        textView = (TextView) findViewById(R.id.marquee);
        textView.setSelected(true);
        advice_btn=(Button)findViewById(R.id.advice);

        ll = (LinearLayout) findViewById(R.id.record_page);
        // Animation fade_in=AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        // ll.setAnimation(fade_in);

        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setAutoStart(true);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translat);

        child_right = (Button) findViewById(R.id.child_right);
        child_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Knowledge.this, ChildRight.class));
            }
        });

        advice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Knowledge.this,Advice_baby.class));
            }
        });

        article=(Button)findViewById(R.id.articles_id);
        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Knowledge.this,Articles_Activity.class));
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.finish();
    }
}
