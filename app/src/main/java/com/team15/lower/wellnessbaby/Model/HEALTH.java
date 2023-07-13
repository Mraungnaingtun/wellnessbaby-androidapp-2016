package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 27. 10. 2016.
 */
public class HEALTH {


    private int health_id;
    private String health_name;
    private String health_description;


    public HEALTH(){}


    public HEALTH(int id,String name,String description)
    {
        this.health_id=id;
        this.health_name=name;
        this.health_description=description;
    }

    public void setHealth_id(int id){this.health_id=id;}
    public void setHealth_name(String name){this.health_name=name;}
    public void setHealth_description(String description){this.health_description=description;}


    public int getHealth_id(){return health_id;}
    public String getHealth_name(){return  health_name;}
    public String getHealth_description(){return health_description;}
}
