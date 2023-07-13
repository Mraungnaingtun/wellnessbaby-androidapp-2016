package com.team15.lower.wellnessbaby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Read_Diseases_Description extends AppCompatActivity {


    TextView tt;
    TextView dd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__diseases__description);


        Bundle bbb=getIntent().getExtras();
        String tit=bbb.getString("disease_title");
        String dis=bbb.getString("disease_description");

        tt=(TextView)findViewById(R.id.disease_title);
        dd=(TextView)findViewById(R.id.disease_description_for_each);


        tt.setText(tit);
        dd.setText(dis);
    }
}
