package com.example.testing;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewBooking extends AppCompatActivity {



    SharedPreferences sp;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    //Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);
        TextView fullname, phonenumber, matricid, email, birthdate, moveindate;
        fullname = findViewById(R.id.etFullName);
        phonenumber = findViewById(R.id.etApplicantPhone);
        matricid = findViewById(R.id.etMatricId);
        email = findViewById(R.id.etApplicantEmail);
        birthdate = findViewById(R.id.etApplicantBorn);
        moveindate = findViewById(R.id.etMoveIn);

        Button closeBook;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();



        closeBook = (Button) findViewById(R.id.backToMain);
        closeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ViewBooking.this, Home.class);
                startActivity(intent);


            }



        });




    }
}