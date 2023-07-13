package com.team15.lower.wellnessbaby;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.team15.lower.wellnessbaby.Adapter.ArticleAdapter;
import com.team15.lower.wellnessbaby.DBhelper.SQLHelper;
import com.team15.lower.wellnessbaby.Model.Article;

import java.util.ArrayList;
import java.util.List;

public class Articles_Activity extends AppCompatActivity {


    List<Article> articleList;
    SQLHelper sqlHelper1;
    ArticleAdapter articleAdapter;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        articleList=new ArrayList<>();
        sqlHelper1 =new SQLHelper(getApplicationContext(), "healthpart");
        sqLiteDatabase=sqlHelper1.getDb();
        articleList=setData();

        articleAdapter = new ArticleAdapter(articleList);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_article);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(articleAdapter);

    }
    public List<Article> setData(){

        List<Article> articleList=new ArrayList<>();
        String query="select * from article";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        Log.i("Cursor Size : ", cursor.getCount() + "");
        if (cursor.moveToFirst()) {
            do {
                Article article=new Article();
                article.setArticle_id(Integer.parseInt(cursor.getString(0)));
                article.setArticle_name(cursor.getString(1));
                article.setArticle_description(cursor.getString(2));
                articleList.add(article);
                Log.i("Name",cursor.getString(1));

            } while (cursor.moveToNext());
        }
        return articleList;
    }

    private String[] getAllAdviceName(){

        String query = "select * from article";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[] array=new String[cursor.getCount()];
        int index=0;
        if(cursor.moveToFirst()){
            do{
                array[index] = cursor.getString(0) + " - " + cursor.getString(1);
                index++;
            }while (cursor.moveToNext());
        }

        Log.i("returning array", ""+array.length);
        cursor.close();
        return  array;
    }
}
