package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 20. 10. 2016.
 */
public class Baby {

    private int id;
    private String name;
    private String birth_date;
    private String weight;
    private String gender;
    private String height;
    private String note;

    /**
     * default constructor
     */
    public Baby(){}

    /**
     *real working constructor with given data
     */
    public Baby(int id, String name, String birth_date, String weight, String gender, String height)
    {
        this.id=id;
        this.name=name;
        this.birth_date=birth_date;
        this.weight=weight;
        this.gender=gender;
        this.height=height;
    }
    public void setId(int id){
        this.id=id;
    }

    public  void setName(String name){
        this.name=name;
    }

    public void setBirth_date(String birth_date){
        this.birth_date=birth_date;
    }
    public void setWeight(String weight){
        this.weight=weight;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public  void setHeight(String height){this.height=height;}
    /**
     *  public void setBaby_photo(byte[] image1){
     this.baby_photo=image1;
     }
     *
     */


    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public String getBirth_date(){
        return birth_date;
    }
    public String getWeight() {
        return weight;
    }
    public String getGender(){
        return gender;
    }
    public String getHeight(){return height;}
   /** public byte[] getBaby_photo(){
     *   return baby_photo;
   * }*/
}
