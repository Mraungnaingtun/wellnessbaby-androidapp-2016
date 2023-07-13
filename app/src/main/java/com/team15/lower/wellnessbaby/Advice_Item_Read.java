package com.team15.lower.wellnessbaby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Advice_Item_Read extends AppCompatActivity {


    TextView title;
    TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice__item__read);
        Bundle bb=getIntent().getExtras();
        String tit=bb.getString("title");
        String desc=bb.getString("description");

        title=(TextView)findViewById(R.id.advice_each_title);
        description=(TextView)findViewById(R.id.advice_description);

        title.setText(tit);
        description.setText(desc);
    }
}
