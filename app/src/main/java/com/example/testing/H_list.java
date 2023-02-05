package com.example.testing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class H_list extends AppCompatActivity {

    private StorageReference h1StorageReference;
    private StorageReference h2StorageReference;
    private StorageReference h3StorageReference;
    private StorageReference h4StorageReference;
    TextView l1,d1,f1,o1;
    TextView l2,d2,f2,o2;
    TextView l3,d3,f3,o3;
    TextView l4,d4,f4,o4;
    ImageView pic1,pic2,pic3,pic4;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlist);

        h1StorageReference = FirebaseStorage.getInstance().getReference().child("houselist/h1.jpg");
        h2StorageReference = FirebaseStorage.getInstance().getReference().child("houselist/h2.jpg");
        h3StorageReference = FirebaseStorage.getInstance().getReference().child("houselist/h3.jpg");
        h4StorageReference = FirebaseStorage.getInstance().getReference().child("houselist/h4.jpg");

        pic1= findViewById(R.id.pich1);
        pic2= findViewById(R.id.pich2);
        pic3= findViewById(R.id.pich3);
        pic4= findViewById(R.id.pich4);
        l1 = findViewById(R.id.h1_location);
        l2 = findViewById(R.id.h2_location);
        l3 =  findViewById(R.id.h3_location);
        l4 =  findViewById(R.id.h4_location);

        d1 =  findViewById(R.id.h1_description);
        d2 =  findViewById(R.id.h2_description);
        d3 =  findViewById(R.id.h3_description);
        d4 = findViewById(R.id.h4_description);

        o1 = findViewById(R.id.h1_offer);
        o2 =  findViewById(R.id.h2_offer);
        o3 =  findViewById(R.id.h3_offer);
        o4 = findViewById(R.id.h4_offer);

        f1 =  findViewById(R.id.h1_facilities);
        f2 = findViewById(R.id.h2_facilities);
        f3 =  findViewById(R.id.h3_facilities);
        f4 = findViewById(R.id.h4_facilities);
        pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(H_list.this, Booking.class);
                startActivity(intent);
            }
        });
        pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(H_list.this, Booking.class);
                startActivity(intent);
            }
        });
        pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(H_list.this, Booking.class);
                startActivity(intent);
            }
        });
        pic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(H_list.this, Booking.class);
                startActivity(intent);
            }
        });

        try {
            final File localFile = File.createTempFile("h1.jpg", "jpeg");
            h1StorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView) findViewById(R.id.pich1)).setImageBitmap(bitmap);

                        } }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        }catch (IOException e){
            e.printStackTrace();
        }


        try {
            final File localFile = File.createTempFile("h2.jpg", "jpeg");
            h2StorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView)findViewById(R.id.pich2)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        }catch (IOException e){
            e.printStackTrace();
        }


        try {
            final File localFile = File.createTempFile("h3.jpg", "jpeg");
            h3StorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView)findViewById(R.id.pich3)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        }catch (IOException e){
            e.printStackTrace();
        }


        try {
            final File localFile = File.createTempFile("h4.jpg", "jpeg");
            h4StorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView)findViewById(R.id.pich4)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        }catch (IOException e){
            e.printStackTrace();
        }

        reff = FirebaseDatabase.getInstance().getReference("H1");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String lo1= snapshot.child("Location").getValue().toString();
                String de1=snapshot.child("Description").getValue().toString();
                String fa1= snapshot.child("Facilities").getValue().toString();
                String of1=snapshot.child("Price").getValue().toString();

                l1.setText(lo1);
                d1.setText(de1);
                f1.setText(fa1);
                o1.setText(of1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reff = FirebaseDatabase.getInstance().getReference("H2");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String lo2= snapshot.child("Location").getValue().toString();
                String de2=snapshot.child("Description").getValue().toString();
                String fa2= snapshot.child("Facilities").getValue().toString();
                String of2=snapshot.child("Price").getValue().toString();

                l2.setText(lo2);
                d2.setText(de2);
                f2.setText(fa2);
                o2.setText(of2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reff = FirebaseDatabase.getInstance().getReference("H3");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String lo3= snapshot.child("Location").getValue().toString();
                String de3=snapshot.child("Description").getValue().toString();
                String fa3= snapshot.child("Facilities").getValue().toString();
                String of3=snapshot.child("Price").getValue().toString();

                l3.setText(lo3);
                d3.setText(de3);
                f3.setText(fa3);
                o3.setText(of3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reff = FirebaseDatabase.getInstance().getReference("H4");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String lo4= snapshot.child("Location").getValue().toString();
                String de4=snapshot.child("Description").getValue().toString();
                String fa4= snapshot.child("Facilities").getValue().toString();
                String of4=snapshot.child("Price").getValue().toString();

                l4.setText(lo4);
                d4.setText(de4);
                f4.setText(fa4);
                o4.setText(of4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
