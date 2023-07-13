package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 31. 10. 2016.
 */
public class Nutrition {

    private int nutrition_id;
    private String nutrition_agegroup;
    private String nutrition_letter;

    public  Nutrition(){}

    public Nutrition(int id,String agegroup,String letter){
        this.nutrition_id=id;
        this.nutrition_agegroup=agegroup;
        this.nutrition_letter=letter;
    }

    public int getNutrition_id() {
        return nutrition_id;
    }

    public String getNutrition_agegroup() {
        return nutrition_agegroup;
    }

    public String getNutrition_letter() {
        return nutrition_letter;
    }

    public void setNutrition_id(int nutrition_id) {
        this.nutrition_id = nutrition_id;
    }

    public void setNutrition_agegroup(String nutrition_agegroup) {
        this.nutrition_agegroup = nutrition_agegroup;
    }

    public void setNutrition_letter(String nutrition_letter) {
        this.nutrition_letter = nutrition_letter;
    }
}
