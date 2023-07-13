package com.team15.lower.wellnessbaby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Book_Activity extends AppCompatActivity implements View.OnClickListener{

    ImageButton story_one;
    ImageButton story_two;
    ImageButton story_three;
    ImageButton story_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);

        story_one=(ImageButton)findViewById(R.id.story_one);
        story_two=(ImageButton)findViewById(R.id.story_two);
        story_three=(ImageButton)findViewById(R.id.story_three);
        story_four=(ImageButton)findViewById(R.id.story_four);


        story_one.setOnClickListener(this);
        story_two.setOnClickListener(this);
        story_three.setOnClickListener(this);
        story_four.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id)
        {
            case R.id.story_one:
                Intent intent=new Intent(Book_Activity.this,Onread_Activity.class);
                intent.putExtra("Butt",1);
                startActivity(intent);
                break;

            case R.id.story_two:
               Intent intent1=new Intent(Book_Activity.this,Onread_Activity.class);
                intent1.putExtra("Butt",2);
                startActivity(intent1);
                break;

            case  R.id.story_three:
                AlertDialog.Builder builder12=new AlertDialog.Builder(this);
                builder12.setTitle("Problem");
                builder12.setMessage("Comming Soon!");
                break;

            case R.id.story_four:
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setTitle("Problem");
                builder1.setMessage("Comming Soon!");
                break;
        }
    }
}
