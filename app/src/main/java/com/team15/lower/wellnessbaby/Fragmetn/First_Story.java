package com.team15.lower.wellnessbaby.Fragmetn;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.team15.lower.wellnessbaby.Adapter.FirstBookAdapter;
import com.team15.lower.wellnessbaby.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)

public class First_Story extends Fragment {

    ViewPager viewPager;
    FirstBookAdapter adapter;
    ImageButton next;
    ImageButton previous;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
      View view=inflater.inflate(R.layout.fragment_first__story, container, false);

        viewPager=(ViewPager)view.findViewById(R.id.view_pager_first);
        adapter = new FirstBookAdapter(getActivity());
        viewPager.setAdapter(adapter);
        return  view;

    }


}

