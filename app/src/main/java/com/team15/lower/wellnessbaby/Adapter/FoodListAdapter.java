package com.team15.lower.wellnessbaby.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team15.lower.wellnessbaby.Model.FoodList;
import com.team15.lower.wellnessbaby.R;
import com.team15.lower.wellnessbaby.Read_Diseases_Description;

import java.util.List;

/**
 * Created by AyeNyeinThu on 31. 10. 2016.
 */
public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyViewHolder> {

    private List<FoodList> foodListList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title, year, genre;
        public RelativeLayout rllayout;
        public Context context;

        public MyViewHolder(View view, Context _context) {
            super(view);
            context = _context;

            rllayout = (RelativeLayout) view.findViewById(R.id.rllayout_food);

            title = (TextView) view.findViewById(R.id.title_food);
            genre = (TextView) view.findViewById(R.id.genre_food);
            rllayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String title = foodListList.get(getAdapterPosition()).getFoodgroup_name();
            //Toast.makeText(context, "SELECTED : "+title, Toast.LENGTH_SHORT).show();
            String gen=foodListList.get(getAdapterPosition()).getFood_description();
            Intent intent=new Intent(context, Read_Diseases_Description.class);
            intent.putExtra("disease_title",title);
            intent.putExtra("disease_description",gen);
            context.startActivity(intent);
        }
    }

    public FoodListAdapter(List<FoodList> moviesList) {
        this.foodListList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_row, parent, false);

        return new MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FoodList disea = foodListList.get(position);

        holder.title.setText(disea.getFoodgroup_name());
        holder.genre.setText(disea.getFood_description());
    }

    @Override
    public int getItemCount() {
        return foodListList.size();
    }
}
