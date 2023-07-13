package com.team15.lower.wellnessbaby.Adapter;

/**
 * Created by nwt on 10/5/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.team15.lower.wellnessbaby.Advice_Item_Read;
import com.team15.lower.wellnessbaby.Model.Adivice;
import com.team15.lower.wellnessbaby.R;

import java.util.List;

public class AdiviceAdapter extends RecyclerView.Adapter<AdiviceAdapter.MyViewHolder> {

    private List<Adivice> adivicelist;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title, des;
        public RelativeLayout rllayout;
        public Context context;

        public MyViewHolder(View view, Context _context) {
            super(view);

            context = _context;
            rllayout = (RelativeLayout) view.findViewById(R.id.rllayout);
            title = (TextView) view.findViewById(R.id.title);
            des = (TextView) view.findViewById(R.id.genre);
            rllayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String title = adivicelist.get(getAdapterPosition()).getAdivice_title();
            /*Toast.makeText(context, "SELECTED : "+title, Toast.LENGTH_SHORT).show();*/
            String des=adivicelist.get(getAdapterPosition()).getGetAdivice_description();
            Intent intent = new Intent(context,Advice_Item_Read.class);
            intent.putExtra("title",title);
            intent.putExtra("description",des);
            context.startActivity(intent);
        }
    }

    public AdiviceAdapter(List<Adivice> moviesList) {
        this.adivicelist = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adivice_row, parent, false);

        return new MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Adivice movie = adivicelist.get(position);

        holder.title.setText(movie.getAdivice_title());
        Log.i("ABC ",movie.getAdivice_title());

       // holder.des.setText(movie.getGetAdivice_description());


    }

    @Override
    public int getItemCount() {
        return adivicelist.size();
    }


}