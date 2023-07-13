package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 29. 10. 2016.
 */
public class Article  {

    private  int article_id;
    private String article_name;
    private String article_description;

    public Article(){}


    public Article(int id,String name,String description){
        this.article_id=id;
        this.article_name=name;
        this.article_description=description;
    }

    public void setArticle_id(int id){
        this.article_id=id;
    }

    public void setArticle_name(String name){
        this.article_name=name;
    }
    public void setArticle_description(String description){
        this.article_description=description;
    }


    public int getArticle_id(){ return article_id;}
    public  String getArticle_name(){return article_name;}
    public String getArticle_description(){return article_description;}

}
