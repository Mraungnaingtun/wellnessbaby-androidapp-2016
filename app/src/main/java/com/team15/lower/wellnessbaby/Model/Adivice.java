package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 27. 10. 2016.
 */
public class Adivice {

    private  int adivice_id;
    private String  adivice_title, getAdivice_description;

    public Adivice() {
    }

    public Adivice(int id,String title){
        this.adivice_id=id;
        this.adivice_title=title;
    }

    public Adivice(int id, String title, String des) {
        this.adivice_id = id;
        this.adivice_title = title;
        this.getAdivice_description = des;
    }



    public int getAdivice_id() {
        return adivice_id;
    }

    public String getAdivice_title() {
        return adivice_title;
    }

    public String getGetAdivice_description() {
        return getAdivice_description;
    }

    public void setAdivice_id(int id) {
        this.adivice_id = id;
    }

    public void setAdivice_title(String title) {
        this.adivice_title=title;
    }

    public void setGetAdivice_description(String description) {
        this.getAdivice_description = description;
    }
}