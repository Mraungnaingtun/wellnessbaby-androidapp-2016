package com.team15.lower.wellnessbaby.Fragmetn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team15.lower.wellnessbaby.Adapter.HEALTHAdapter;
import com.team15.lower.wellnessbaby.DBhelper.SQLHelper;
import com.team15.lower.wellnessbaby.Model.HEALTH;
import com.team15.lower.wellnessbaby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AyeNyeinThu on 27. 10. 2016.
 */
public class OneFragment extends Fragment {

    private List<HEALTH> diseaseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HEALTHAdapter mAdapter;
    HEALTH disease;
    String[] disease_array;
    Context context;


    SQLHelper sqlHelper;
    SQLiteDatabase db;

    public OneFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlHelper = new SQLHelper(getContext(), "healthpart");
        db = sqlHelper.getDb();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        context = container.getContext();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new HEALTHAdapter(setData());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return view;
    }


   /* public void setDiseaseList(){

        disease_array=getDisease_array();
        for (int i = 0; i < disease_array.length; i++) {
            HEALTH h = getAllData();

            HEALTH heal = new HEALTH(i, h.getHealth_name(), h.getHealth_description());
            diseaseList.add(heal);
        }

    }*/


    public List<HEALTH> setData() {

        List<HEALTH> diseaselist = new ArrayList<>();
        String query = "select * from health";
        Cursor cursor = db.rawQuery(query, null);
        Log.i("Cursor Size : ", cursor.getCount() + "");
        if (cursor.moveToFirst()) {
            do {
                HEALTH health = new HEALTH();
                health.setHealth_description(String.valueOf(cursor.getString(0)));
                health.setHealth_name(cursor.getString(1));
                health.setHealth_description(cursor.getString(2));
                diseaselist.add(health);
            } while (cursor.moveToNext());
        }
        return diseaselist;
    }




   /* private String[] getDisease_array(){


        String query = "select * from health";
        Cursor cursor = db.rawQuery(query, null);
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
*/
}
