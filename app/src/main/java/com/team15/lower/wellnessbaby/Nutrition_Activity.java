package com.team15.lower.wellnessbaby;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.team15.lower.wellnessbaby.Adapter.NutritionAdapter;
import com.team15.lower.wellnessbaby.Fragmetn.Feeds_fragment;
import com.team15.lower.wellnessbaby.Fragmetn.Food_List;

import java.util.ArrayList;
import java.util.List;

public class Nutrition_Activity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    NutritionAdapter nutritionAdapter;
    //BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nutrition);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if(getIntent().getExtras() != null){
            Toast.makeText(this, "EXTRA DATA :"+getIntent().getExtras().getInt("EXTRA_SESSION_ID"),Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Adding fragments to ViewPager
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Feeds_fragment(), "ကေလးအာဟာရေကြ်းေမြးေရး");//Title
        adapter.addFragment(new Food_List(), "အာဟာရ ႏွင့္ သိထားသင့့္ေသာအခ်က္မ်ား");//Title
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
