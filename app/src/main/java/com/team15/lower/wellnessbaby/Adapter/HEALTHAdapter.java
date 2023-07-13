package com.team15.lower.wellnessbaby.Adapter;

/**
 * Created by nwt on 10/5/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team15.lower.wellnessbaby.Model.HEALTH;
import com.team15.lower.wellnessbaby.R;
import com.team15.lower.wellnessbaby.Read_Diseases_Description;

import java.util.List;

public class HEALTHAdapter extends RecyclerView.Adapter<HEALTHAdapter.MyViewHolder> {

    private List<HEALTH> diseaseList;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title, year, genre;
        public RelativeLayout rllayout;
        public Context context;

        public MyViewHolder(View view, Context _context) {
            super(view);
            context = _context;

            rllayout = (RelativeLayout) view.findViewById(R.id.rllayout);

            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);

            rllayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String title = diseaseList.get(getAdapterPosition()).getHealth_name();
            //Toast.makeText(context, "SELECTED : "+title, Toast.LENGTH_SHORT).show();
            String gen=diseaseList.get(getAdapterPosition()).getHealth_description();

            Intent intent=new Intent(context, Read_Diseases_Description.class);

            intent.putExtra("disease_title",title);
            intent.putExtra("disease_description",gen);
            context.startActivity(intent);


        }
    }

    public HEALTHAdapter(List<HEALTH> moviesList) {
        this.diseaseList = moviesList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        HEALTH disea = diseaseList.get(position);

        holder.title.setText(disea.getHealth_name());
        holder.genre.setText(disea.getHealth_description());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diseaselist_row, parent, false);

        return new MyViewHolder(itemView, parent.getContext());
    }



    @Override
    public int getItemCount() {
        return diseaseList.size();
    }
}