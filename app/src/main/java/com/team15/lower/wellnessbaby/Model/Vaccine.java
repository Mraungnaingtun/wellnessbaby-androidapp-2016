package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 30. 10. 2016.
 */
public class Vaccine {



    private int vaccine_id;
    private String vaccine_age;
    private String vaccine_name;
    private String disease_name;
    private String vaccine_total_count;


    public Vaccine(){}

    public Vaccine(int id,String age,String name,String disease,String total){
        this.vaccine_id=id;
        this.vaccine_age=age;
        this.vaccine_name=name;
        this.disease_name=disease;
        this.vaccine_total_count=total;
    }

    public int getVaccine_id() {
        return vaccine_id;
    }

    public String getVaccine_age() {
        return vaccine_age;
    }

    public String getVaccine_name() {
        return vaccine_name;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public String getVaccine_total_count() {
        return vaccine_total_count;
    }

    public void setVaccine_id(int vaccine_id) {
        this.vaccine_id = vaccine_id;
    }

    public void setVaccine_age(String vaccine_age) {
        this.vaccine_age = vaccine_age;
    }

    public void setVaccine_name(String vaccine_name) {
        this.vaccine_name = vaccine_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public void setVaccine_total_count(String vaccine_total_count) {
        this.vaccine_total_count = vaccine_total_count;
    }
}
