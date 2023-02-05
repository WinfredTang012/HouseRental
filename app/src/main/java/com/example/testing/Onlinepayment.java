package com.example.testing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class Onlinepayment extends AppCompatActivity {

    Button pb,mb,cimb,islam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinepayment);

        pb =(Button) findViewById(R.id.btn_publicbank);
        mb =(Button) findViewById(R.id.btn_maybank);
        cimb =(Button) findViewById(R.id.btn_cimbbank);
        islam =(Button) findViewById(R.id.btn_bankislam);

        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pbebank.com/"));
                startActivity(browserIntent);
            }
        });
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.maybank2u.com.my/home/m2u/common/login.do/"));
                startActivity(browserIntent);
            }
        });

        cimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cimbclicks.com.my/"));
                startActivity(browserIntent);
            }
        });

        islam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bankislam.biz/"));
                startActivity(browserIntent);
            }
        });
    }

}