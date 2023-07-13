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

import com.team15.lower.wellnessbaby.Model.Nutrition;
import com.team15.lower.wellnessbaby.R;
import com.team15.lower.wellnessbaby.Read_Diseases_Description;

import java.util.List;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.MyViewHolder> {

    private List<Nutrition> nutritionList;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title, year, genre;
        public RelativeLayout rllayout;
        public Context context;

        public MyViewHolder(View view, Context _context) {
            super(view);
            context = _context;

            rllayout = (RelativeLayout) view.findViewById(R.id.rllayout_nutrition);

            title = (TextView) view.findViewById(R.id.title_nutrition);
            genre = (TextView) view.findViewById(R.id.genre_nutrition);

            rllayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String title = nutritionList.get(getAdapterPosition()).getNutrition_agegroup();
            //Toast.makeText(context, "SELECTED : "+title, Toast.LENGTH_SHORT).show();
            String gen=nutritionList.get(getAdapterPosition()).getNutrition_letter();
            Intent intent=new Intent(context, Read_Diseases_Description.class);
            intent.putExtra("disease_title",title);
            intent.putExtra("disease_description",gen);
            context.startActivity(intent);
        }
    }

    public NutritionAdapter(List<Nutrition> moviesList) {
        this.nutritionList = moviesList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Nutrition disea = nutritionList.get(position);

        holder.title.setText(disea.getNutrition_agegroup());
        holder.genre.setText(disea.getNutrition_letter());
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nutrition_row, parent, false);
        return new MyViewHolder(itemView, parent.getContext());
    }
    @Override
    public int getItemCount() {
        return nutritionList.size();
    }
}