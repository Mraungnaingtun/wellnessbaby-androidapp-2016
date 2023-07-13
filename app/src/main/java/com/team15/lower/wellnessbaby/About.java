package com.team15.lower.wellnessbaby;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity {


    TextView facebook;
    TextView dayornight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        TextView textView = (TextView) findViewById(R.id.about);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder3 = new AlertDialog.Builder(About.this);
                // Get the layout inflater
                LayoutInflater inflater = About.this.getLayoutInflater();
                final View dialogview = inflater.inflate(R.layout.about_custom_alert, null);

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder3.setView(dialogview)
                        // Add action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();

                            }
                        });
                builder3.show();
            }
        });
/**
 * 8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
 */
        facebook=(TextView)findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://www.facebook.com/wellnessbabyapp/";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_contant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_share) {

            // con.startActivity(intent);
            Log.i("Send SMS", "");
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);

            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address"  , new String ("09422522286"));
            // smsIntent.putExtra("address",phNo);
            smsIntent.putExtra("sms_body"  , "Test ");

            try {
                startActivity(smsIntent);
                // finish();
                Log.i("Finished sending SMS...", "");
            }
            catch (android.content.ActivityNotFoundException ex)
            {

            }
        }else  if(id== R.id.share){

            String[] TO = {"ll.dora2468@gmail.com"};
            String[] CC = {"wellnessbaby2016@gmail.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);

            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello Ye Htet Linn");
            //must write something that u want to mess
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello My Dear!");
            try {
                startActivity(Intent.createChooser(emailIntent, "Share News-------"));
            } catch (android.content.ActivityNotFoundException ex)
            {

            }
        }
        return super.onOptionsItemSelected(item);
    }

}
