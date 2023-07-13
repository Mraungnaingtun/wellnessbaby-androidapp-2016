package com.team15.lower.wellnessbaby.Child_Right;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.team15.lower.wellnessbaby.Adapter.CustomSwipeAdapter;
import com.team15.lower.wellnessbaby.R;

public class ChildRight extends AppCompatActivity {


    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    ImageButton next;
    ImageButton previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_right);

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
