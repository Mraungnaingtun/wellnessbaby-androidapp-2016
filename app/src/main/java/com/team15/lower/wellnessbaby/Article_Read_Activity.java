package com.team15.lower.wellnessbaby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Article_Read_Activity extends AppCompatActivity {

    TextView title;
    TextView description;
    RelativeLayout back;
    int b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article__read_);

        Bundle bbbb=getIntent().getExtras();
        String tit=bbbb.getString("title");
        String desc=bbbb.getString("description");


        title=(TextView)findViewById(R.id.article_each_title);
        description=(TextView)findViewById(R.id.article_each_description);

        title.setText(tit);
        description.setText(desc);
    }



}
