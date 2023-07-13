package com.team15.lower.wellnessbaby;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.team15.lower.wellnessbaby.Fragmetn.First_Story;
import com.team15.lower.wellnessbaby.Fragmetn.Second_book;


public class Onread_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onread_);

        Bundle bb = getIntent().getExtras();
        int tit=bb.getInt("Butt");

        displayFragment(tit);
    }

    private void displayFragment(int position) {

        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = new First_Story();
                break;
            case 2:
                fragment = new Second_book();

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.view_pager, fragment);
            fragmentTransaction.commit();
        }
    }
}