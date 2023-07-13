package com.team15.lower.wellnessbaby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

public class Ententainment_Activity extends AppCompatActivity implements View.OnClickListener {

    Button music_btn;
    Button paint_btn;
    Button book_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ententainment);

       /* ViewFlipper viewFlipper= (ViewFlipper) findViewById(R.id.view_flipper);
        Animation in= AnimationUtils.loadAnimation(this,R.anim.slide_left);
        Animation out= AnimationUtils.loadAnimation(this,R.anim.slide_right);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setAutoStart(true);*/


        KenBurnsView kenBurnsView=(KenBurnsView)findViewById(R.id.image);

        kenBurnsView.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {


            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }
        });


        music_btn=(Button)findViewById(R.id.music_btn);
        //music_btn.setBackgroundColor(Color.TRANSPARENT);
        paint_btn=(Button)findViewById(R.id.paint_btn);
        book_btn=(Button)findViewById(R.id.book_btn);


        music_btn.setOnClickListener(this);
        paint_btn.setOnClickListener(this);
        book_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {



       switch (v.getId()){
           case R.id.music_btn:
               startActivity(new Intent(Ententainment_Activity.this,Play_Music.class));
               break;

           case R.id.book_btn:
               startActivity(new Intent(Ententainment_Activity.this,Book_Activity.class));
               break;

           case R.id.paint_btn:
               startActivity(new Intent(Ententainment_Activity.this,Paint.class));
       }
    }
}
