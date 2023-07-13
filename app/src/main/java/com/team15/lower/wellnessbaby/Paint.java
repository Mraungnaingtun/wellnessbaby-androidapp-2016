package com.team15.lower.wellnessbaby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.team15.lower.wellnessbaby.Model.TouchView;

public class Paint extends AppCompatActivity {

    Button clear,erase;
    TouchView touchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        clear = (Button) findViewById(R.id.clear);
       // erase = (Button) findViewById(R.id.erase);
        touchView = (TouchView) findViewById(R.id.todraw);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchView.eraseAll();
            }
        });

       // erase.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
                touchView.eraseAll();
           // }
        //});
    }
}
