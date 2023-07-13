package com.team15.lower.wellnessbaby.Fragmetn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.team15.lower.wellnessbaby.Adapter.SecondBookAdapter;
import com.team15.lower.wellnessbaby.R;



public class Second_book extends Fragment {


    ViewPager viewPager;
    SecondBookAdapter adapter;
    ImageButton next;
    ImageButton previous;

    public Second_book() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_second_book, container, false);

        viewPager=(ViewPager)v.findViewById(R.id.view_pager_second);
        adapter = new SecondBookAdapter(getActivity());
        viewPager.setAdapter(adapter);
        return  v;
    }

}
