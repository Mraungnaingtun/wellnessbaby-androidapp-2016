package com.team15.lower.wellnessbaby.Adapter;

/**
 * Created by AyeNyeinThu on 3. 11. 2016.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team15.lower.wellnessbaby.R;

/**
 * Created by AyeNyeinThu on 1. 11. 2016.
 */
public class SecondBookAdapter  extends PagerAdapter {

    private int[] image_resource = {R.drawable.thar1,R.drawable.thar2,R.drawable.thar3,R.drawable.thar4,R.drawable.thar5,R.drawable.thar6,
            R.drawable.thar7,R.drawable.thar8,R.drawable.thar9};

    TextView count_page;
    ImageView imageView;
    private Context ctx;
    private LayoutInflater layoutInflater;


    public SecondBookAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.first_swipe, container, false);
        int pos=position+1;
        imageView = (ImageView) item_view.findViewById(R.id.slide_image_first);
        TextView count=(TextView) item_view.findViewById(R.id.count_first);
        count.setText("<<<<< စာမ်က္နာ:  "+ pos +" >>>>>>");
        imageView.setImageResource(image_resource[position]);
        container.addView(item_view);
        return item_view;
    }

    public void next() {
        imageView.setImageResource(image_resource[getItemPosition(imageView) + 1]);
    }

    public void previous() {
        imageView.setImageResource(image_resource[getItemPosition(imageView) + 1]);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}

