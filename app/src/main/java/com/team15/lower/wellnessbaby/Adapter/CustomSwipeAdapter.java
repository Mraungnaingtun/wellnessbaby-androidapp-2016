package com.team15.lower.wellnessbaby.Adapter;

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
 * Created by AyeNyeinThu on 21. 10. 2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {

    private int[] image_resource = {R.drawable.childright1, R.drawable.childright2, R.drawable.childright3, R.drawable.childright4,
            R.drawable.childright5, R.drawable.childright6, R.drawable.childright7, R.drawable.childright8, R.drawable.childright9,
            R.drawable.childright10, R.drawable.childright11, R.drawable.childright12, R.drawable.childright13, R.drawable.childright14,
            R.drawable.childright15, R.drawable.childright16, R.drawable.childright17, R.drawable.childright18, R.drawable.childright19,
            R.drawable.childright20, R.drawable.childright21,
            R.drawable.childright22, R.drawable.childright23, R.drawable.childright24,
            R.drawable.childright25, R.drawable.childright26, R.drawable.childright27, R.drawable.childright28, R.drawable.childright29,
            R.drawable.childright30, R.drawable.childright31, R.drawable.childright32, R.drawable.childright33, R.drawable.childright34,
            R.drawable.childright35, R.drawable.childright36, R.drawable.childright37, R.drawable.childright38, R.drawable.childright39,
            R.drawable.childright40};

    TextView count_page;
    ImageView imageView;
    private Context ctx;
    private LayoutInflater layoutInflater;


    public CustomSwipeAdapter(Context ctx) {
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
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);

        imageView = (ImageView) item_view.findViewById(R.id.slide_image);
        TextView count=(TextView) item_view.findViewById(R.id.count);
        int p=position+1;
        count.setText("<<<<< စာမ်က္နာ:  "+ p +" >>>>>>");
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
