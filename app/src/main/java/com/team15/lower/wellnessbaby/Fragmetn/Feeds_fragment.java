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

import com.team15.lower.wellnessbaby.Adapter.NutritionAdapter;
import com.team15.lower.wellnessbaby.DBhelper.SQLHelper;
import com.team15.lower.wellnessbaby.Model.Nutrition;
import com.team15.lower.wellnessbaby.R;

import java.util.ArrayList;
import java.util.List;

public class Feeds_fragment extends Fragment {


    private List<Nutrition> nutritionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NutritionAdapter mAdapter;
    Nutrition nutrition;
    String[] nutrition_array;
    Context context;

    SQLHelper sqlHelper;
    SQLiteDatabase db;

    public Feeds_fragment() {

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

        View view = inflater.inflate(R.layout.fragment_feeds_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_nutrition);
        context = container.getContext();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new NutritionAdapter(setNutrition());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    public List<Nutrition> setNutrition() {

        List<Nutrition> nutritions = new ArrayList<>();
        String query = "select * from nutrition";
        Cursor cursor = db.rawQuery(query, null);
        Log.i("Cursor Size : ", cursor.getCount()+"");
        if (cursor.moveToFirst()) {

            do {
                Nutrition nutrition = new Nutrition();
                nutrition.setNutrition_id(Integer.valueOf(cursor.getString(0)));
                nutrition.setNutrition_agegroup(cursor.getString(1));
                nutrition.setNutrition_letter(cursor.getString(2));
                nutritions.add(nutrition);
            } while (cursor.moveToNext());
        }
        return nutritions;
    }

}
