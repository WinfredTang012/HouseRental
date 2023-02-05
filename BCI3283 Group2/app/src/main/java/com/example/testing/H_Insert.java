package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class H_Insert extends AppCompatActivity {
    private EditText locationEdt, descriptionEdt, facilitiesEdt, offerEdt;
    private Button insert;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    H_Object h_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hinsert);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://testfirebase-98765-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference("HOUSE");
        // initializing our edittext and button
        locationEdt = (EditText) findViewById(R.id.location);
        descriptionEdt = (EditText) findViewById(R.id.description);
        facilitiesEdt = (EditText) findViewById(R.id.facilities);
        offerEdt = (EditText) findViewById(R.id.offer);
        insert = (Button) findViewById(R.id.insert);



        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("HOUSE").push();

        // initializing our object
        // class variable.
        h_object = new H_Object();

        insert = findViewById(R.id.insert);

        // adding on click listener for our button.
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = locationEdt.getText().toString();
                String description = descriptionEdt.getText().toString();
                String facilities = facilitiesEdt.getText().toString();
                String offer = offerEdt.getText().toString();


                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(location) && TextUtils.isEmpty(description) && TextUtils.isEmpty(facilities) && TextUtils.isEmpty(offer)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(H_Insert.this, "Please filled in data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(location, description, facilities, offer);
                }
            }
        });
    }

    private void addDatatoFirebase(String location, String description, String facilities, String offer) {
        // below 3 lines of code is used to set
        // data in our object class.
        h_object.setLocation(location);
        h_object.setDescription(description);
        h_object.setFacilities(facilities);
        h_object.setOffer(offer);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(h_object);
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                assert firebaseUser != null;
                String userId = firebaseUser.getUid();




                // after adding this data we are showing toast message.
                Toast.makeText(H_Insert.this, "data added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(H_Insert.this, Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(H_Insert.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}