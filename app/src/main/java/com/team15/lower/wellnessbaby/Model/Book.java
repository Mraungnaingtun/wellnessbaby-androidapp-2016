package com.team15.lower.wellnessbaby.Model;

/**
 * Created by AyeNyeinThu on 28. 10. 2016.
 */
public class Book {

    private int book_id;
    private String book_name;
    private int image;

    public Book() {
    }

    public Book(int book_id,String name,int image) {
        this.book_id=book_id;
        this.book_name = name;
        this.image=image;
    }
    public String getName() {
        return book_name;
    }
    public void setName(String name) {
        this.book_name = name;
    }
    public int getBook_id() {
        return book_id;
    }
    public void setBook_id(int id) {this.book_id=id;}
    public int  getImage(){return image;}
    public void setImage(int image){this.image=image;}

}
