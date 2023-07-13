package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 26. 10. 2016.
 */
public class Note  {

    private int id;
    private String title;
    private String date;
    private String body;

    /**
     * default constructor
     */

    public Note(){}


    /**
     * real working constructor
     */
    public Note(int i,String t,String d,String b){
        this.id=i;
        this.title=t;
        this.date=d;
        this.body=b;

    }


    public void set_note_Id(int id1){
        this.id=id1;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public  void setDate(String d){
        this.date=d;
    }
    public void  setBody(String about){
        this.body=about;
    }


    public int getId(){return id;}
    public String getTitle(){return  title;}
    public String getDate(){return date;}
    public String getBody(){return body;}

}
