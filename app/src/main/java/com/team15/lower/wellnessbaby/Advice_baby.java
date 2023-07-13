package com.team15.lower.wellnessbaby;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.team15.lower.wellnessbaby.Adapter.AdiviceAdapter;
import com.team15.lower.wellnessbaby.Model.Adivice;
import com.team15.lower.wellnessbaby.DBhelper.SQLHelper;

import java.util.ArrayList;
import java.util.List;


public class Advice_baby extends AppCompatActivity {

    private RecyclerView recyclerView;
    AdiviceAdapter adiviceAdapter;
    private List<Adivice> adiviceList=new ArrayList<>();
    SQLHelper sqlHelper;
    SQLiteDatabase sqLiteDatabase;
    String[] advice_array;
    Adivice adivice;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_baby);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sqlHelper =new SQLHelper(getApplicationContext(), "healthpart");
        sqLiteDatabase=sqlHelper.getDb();
        adiviceList=setData();
        adiviceAdapter = new AdiviceAdapter(adiviceList);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adiviceAdapter);
        relativeLayout=(RelativeLayout)findViewById(R.id.recycler_advice);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


       // setAdviceName();


        if (getIntent().getExtras() != null) {
            Toast.makeText(this, "EXTRA DATA :" + getIntent().getExtras().getInt("EXTRA_SESSION_ID"), Toast.LENGTH_SHORT).show();
        }
    }

    public List<Adivice> setData(){


        List<Adivice> adiviceList=new ArrayList<>();
        String query="select * from adivice";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        Log.i("Cursor Size : ", cursor.getCount() + "");
        if (cursor.moveToFirst()) {
            do {
                Adivice adivice=new Adivice();
                adivice.setAdivice_id(Integer.parseInt(cursor.getString(0)));
                adivice.setAdivice_title(cursor.getString(1));
                adivice.setGetAdivice_description(cursor.getString(2));
                adiviceList.add(adivice);
                Log.i("Name",cursor.getString(1));

            } while (cursor.moveToNext());
        }
        return adiviceList;
    }

    private String[] getAllAdviceName(){

        String query = "select * from adivice";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[] array=new String[cursor.getCount()];
        int index=0;
        if(cursor.moveToFirst()){
            do{
                array[index] = cursor.getString(0) + " - " + cursor.getString(1);
                index++;
            }while (cursor.moveToNext());
        }

        Log.i("returning array", ""+array.length);
        cursor.close();
        return  array;
    }

}