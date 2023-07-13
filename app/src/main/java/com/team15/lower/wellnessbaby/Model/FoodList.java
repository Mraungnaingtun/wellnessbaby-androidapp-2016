package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 31. 10. 2016.
 */
public class FoodList {

    private int food_id;
    private String foodgroup_name;
    private String food_description;


    public FoodList(){}

    public FoodList(int id,String name,String description)
    {
        this.food_id=id;
        this.foodgroup_name=name;
        this.food_description=description;
    }

    public String getFood_description() {
        return food_description;
    }

    public int getFood_id() {
        return food_id;
    }

    public String getFoodgroup_name() {
        return foodgroup_name;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public void setFoodgroup_name(String foodgroup_name) {
        this.foodgroup_name = foodgroup_name;
    }
}
