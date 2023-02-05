package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    Button button3,button4,button6,button7,button9,button10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }


        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, H_Insert.class);
                startActivity(intent);
            }


        });
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, H_list.class);
                startActivity(intent);
            }


        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentLoadNewActivity=new Intent (Home.this, Confirm.class);
                startActivity(intentLoadNewActivity);



            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentLoadNewActivity=new Intent (Home.this, ViewBooking.class);
                startActivity(intentLoadNewActivity);



            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentLoadNewActivity=new Intent (Home.this, CompassActivity.class);
                startActivity(intentLoadNewActivity);



            }
        });
    }
}