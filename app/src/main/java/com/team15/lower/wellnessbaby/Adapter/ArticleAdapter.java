package com.team15.lower.wellnessbaby.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team15.lower.wellnessbaby.Article_Read_Activity;
import com.team15.lower.wellnessbaby.Model.Article;
import com.team15.lower.wellnessbaby.R;

import java.util.List;

/**
 * Created by AyeNyeinThu on 29. 10. 2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private List<Article> articleList;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title, des;
        public RelativeLayout relativeLayout;
        public Context context;

        public MyViewHolder(View view, Context _context) {
            super(view);

            context = _context;
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativelayout_article);
            title = (TextView) view.findViewById(R.id.article_title);
            des = (TextView) view.findViewById(R.id.article_description);
            relativeLayout.setOnClickListener(MyViewHolder.this);

        }


        @Override
        public void onClick(View v) {
            String title = articleList.get(getAdapterPosition()).getArticle_name();
            /*Toast.makeText(context, "SELECTED : "+title, Toast.LENGTH_SHORT).show();*/
            String des=articleList.get(getAdapterPosition()).getArticle_description();

            Intent intent = new Intent(context,Article_Read_Activity.class);
            intent.putExtra("title",title);
            intent.putExtra("description",des);
            context.startActivity(intent);
        }
    }

    public ArticleAdapter(List<Article> adv) {
        this.articleList = adv;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_row, parent, false);
        return new MyViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Article article = articleList.get(position);

        holder.title.setText(article.getArticle_name());
        holder.des.setText(article.getArticle_description());

        Log.i("ABC ",article.getArticle_description());
        // holder.des.setText(movie.getGetAdivice_description());

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


}
