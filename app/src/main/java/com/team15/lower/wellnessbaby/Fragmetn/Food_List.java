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

import com.team15.lower.wellnessbaby.Adapter.FoodListAdapter;
import com.team15.lower.wellnessbaby.DBhelper.SQLHelper;
import com.team15.lower.wellnessbaby.Model.FoodList;
import com.team15.lower.wellnessbaby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment with a Google +1 button.
 */
public class Food_List extends Fragment {


    private List<FoodList> foodListArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FoodListAdapter mAdapter;
    FoodList foodList;
    String[] nutrition_array;
    Context context;

    SQLHelper sqlHelper;
    SQLiteDatabase db;

    public Food_List() {
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
        View view = inflater.inflate(R.layout.fragment_food__list, container, false);

        //Find the +1 button

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_list);
        context = container.getContext();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new FoodListAdapter(setNutrition());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    public List<FoodList> setNutrition() {

        List<FoodList> food = new ArrayList<>();
        String query = "select * from foodlist";
        Cursor cursor = db.rawQuery(query, null);
        Log.i("Cursor Size : ", cursor.getCount()+"");
        if (cursor.moveToFirst()) {
            do {
                FoodList foodList = new FoodList();
                foodList.setFood_id(Integer.valueOf(cursor.getString(0)));
                foodList.setFoodgroup_name(cursor.getString(1));
                foodList.setFood_description(cursor.getString(2));

               food.add(foodList);

            } while (cursor.moveToNext());
        }
        return food;
    }

}
