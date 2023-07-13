package com.team15.lower.wellnessbaby;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

public class Play_Music extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer m, m1, m2, m3, m4, pm;
    int a = 0,b=0;
    KenBurnsView kenBurnsView;
    ImageButton pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__music);
        final Button play = (Button) findViewById(R.id.first_song);
        final Button splay = (Button) findViewById(R.id.second_song);
        final Button tplay = (Button) findViewById(R.id.third_song);
        final Button fplay = (Button) findViewById(R.id.four_song);
        final Button lplay = (Button) findViewById(R.id.five_song);
        pause = (ImageButton) findViewById(R.id.play_button);


        play.setOnClickListener(this);
        splay.setOnClickListener(this);
        tplay.setOnClickListener(this);
        fplay.setOnClickListener(this);
        lplay.setOnClickListener(this);
        pause.setOnClickListener(this);


        KenBurnsView kenBurnsView=(KenBurnsView)findViewById(R.id.imageView2);

        kenBurnsView.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {


            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }
        });

        m = new MediaPlayer();
        m = MediaPlayer.create(this, R.raw.m1);
        m1 = new MediaPlayer();
        m1 = MediaPlayer.create(this, R.raw.m2);
        m2 = new MediaPlayer();
        m2 = MediaPlayer.create(this, R.raw.m3);
        m3 = new MediaPlayer();
        m3 = MediaPlayer.create(this, R.raw.m4);
        m4 = new MediaPlayer();
        m4 = MediaPlayer.create(this, R.raw.m5);
        pm = new MediaPlayer();


    }

    @Override
    public void onClick(View v) {

        int d = v.getId();
        switch (d) {
            case R.id.first_song:
                if (a == 0) {

                    m = MediaPlayer.create(Play_Music.this, R.raw.m1);
                    pause.setImageResource(R.drawable.pb);
                    m.start();
                    pm = m;
                    a = 1;

                } else {
                    if (pm.isPlaying()) {
                        pause.setImageResource(R.drawable.pa);
                        pm.pause();
                        m = MediaPlayer.create(Play_Music.this, R.raw.m1);
                        pause.setImageResource(R.drawable.pb);
                        m.start();
                        pm = m;
                        a = 1;
                    }
                }
                break;
            case R.id.second_song:
                if (a == 0) {

                    m1 = MediaPlayer.create(Play_Music.this, R.raw.m2);
                    pause.setImageResource(R.drawable.pb);
                    m1.start();
                    pm = m1;

                } else {
                    if (pm.isPlaying()) {
                        pause.setImageResource(R.drawable.pa);
                        pm.pause();
                        m1 = MediaPlayer.create(Play_Music.this, R.raw.m2);
                        pause.setImageResource(R.drawable.pb);
                        m1.start();
                        pm = m1;
                        a = 1;
                    }
                }
                break;
            case R.id.third_song:
                if (a == 0) {

                    m2 = MediaPlayer.create(Play_Music.this, R.raw.m3);
                    pause.setImageResource(R.drawable.pb);
                    m2.start();
                    pm = m2;
                    a = 1;
                } else {
                    if (pm.isPlaying()) {
                        pause.setImageResource(R.drawable.pa);
                        pm.pause();
                        m2 = MediaPlayer.create(Play_Music.this, R.raw.m3);
                        pause.setImageResource(R.drawable.pb);
                        m2.start();
                        pm = m2;
                        a = 1;
                    }
                }
                break;
            case R.id.four_song:
                if (a == 0) {

                    m3 = MediaPlayer.create(Play_Music.this, R.raw.m4);
                    pause.setImageResource(R.drawable.pb);
                    m3.start();
                    pm = m3;
                    a = 1;
                } else {
                    if (pm.isPlaying()) {
                        pause.setImageResource(R.drawable.pa);
                        pm.pause();
                        m3 = MediaPlayer.create(Play_Music.this, R.raw.m4);
                        pause.setImageResource(R.drawable.pb);
                        m3.start();
                        pm = m3;
                        a = 1;
                    }
                }
                break;
            case R.id.five_song:
                if (a == 0) {

                    m4 = MediaPlayer.create(Play_Music.this, R.raw.m5);
                    pause.setImageResource(R.drawable.pb);
                    m4.start();
                    pm = m4;
                    a = 1;
                } else {
                    if (pm.isPlaying()) {
                        pause.setImageResource(R.drawable.pa);
                        pm.pause();
                        m4 = MediaPlayer.create(Play_Music.this, R.raw.m5);
                        pause.setImageResource(R.drawable.pb);
                        m4.start();
                        pm = m4;
                        a = 1;
                    }
                }
                break;
            case R.id.play_button:
                if (a==0&&b==0){
                    pause.setImageResource(R.drawable.pb);
                    m1.start();
                    pm=m1;
                    a=1;
                    b=1;
                }
                else if (pm.isPlaying()) {
                    pause.setImageResource(R.drawable.pa);
                    pm.pause();
                    a = 0;

                } else {
                    pause.setImageResource(R.drawable.pb);
                    pm.start();
                    a = 1;
                }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.home) {
            pm.stop();
            this.finish();
        }

            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        pm.stop();
        super.onBackPressed();
    }
}

