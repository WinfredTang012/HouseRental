package com.example.testing;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {

    SharedPreferences sp;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        TextView name, id, phone, email, password,address;
        id = findViewById(R.id.textView3);
        name = findViewById(R.id.textView4);
        phone = findViewById(R.id.textView5);
        email = findViewById(R.id.textView6);
        password = findViewById(R.id.textView11);
        address = findViewById(R.id.textView8);
  


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("USERS");
        reference.addValueEventListener(new ValueEventListener(){


            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()){

                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userId = firebaseUser.getUid();

                    String vname = datasnapshot.child(userId).child("name").getValue().toString();
                    String vphone = datasnapshot.child(userId).child("phone").getValue().toString();
                    String vemail = datasnapshot.child(userId).child("email").getValue().toString();
                    String vpassword = datasnapshot.child(userId).child("password").getValue().toString();
                    String vid = datasnapshot.child(userId).child("id").getValue().toString();
                    String vaddress = datasnapshot.child(userId).child("address").getValue().toString();


                    name.setText(vname);
                    phone.setText(vphone);
                    email.setText(vemail);
                    password.setText(vpassword);
                    id.setText(vid);
                    address.setText(vaddress);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }
}




