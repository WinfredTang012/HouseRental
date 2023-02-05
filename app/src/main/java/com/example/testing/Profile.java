package com.example.testing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
    private DatabaseReference rootDatabaseref;

    Button button5, button7,button8,button9;
    private TextView pname, pemail;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    private EditText oname,ophone,oaddress;
    private Button button1;
    public Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        pname = findViewById(R.id.textView);
        pemail = findViewById(R.id.textView2);
        button5 =(Button) findViewById(R.id.button5);
        button7 =(Button) findViewById(R.id.button7);
        button9 =(Button) findViewById(R.id.button3);
        button8 =(Button) findViewById(R.id.button8);
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("USERS");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser USERS = FirebaseAuth.getInstance().getCurrentUser();
        String userID = USERS.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("USERS");
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()){

                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userId = firebaseUser.getUid();

                    String vname = datasnapshot.child(userId).child("name").getValue().toString();
                    String vemail = datasnapshot.child(userId).child("email").getValue().toString();

                    pname.setText(vname);
                    pemail.setText(vemail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        button5.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v){
                Intent intentLoadNewActivity=new Intent (Profile.this, ViewProfile.class);
                startActivity(intentLoadNewActivity);
            }
        });



        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.privacypolicygenerator.info/live.php?token=AYPoT4jFr1UU8Ef6ctoyG32FVcKQoSvI"));
                startActivity(browserIntent);
            }
        });


        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Profile.this, EditProfile.class);
                startActivity(intent);


            }



        });
    }


}