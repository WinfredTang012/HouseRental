package com.example.testing;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;

import java.util.UUID;


public class EditProfile extends AppCompatActivity {

    private ImageView profilePic;
    private TextView oid;
    private EditText oname,ophone,oaddress;
    private Button button1;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile);

        oid = findViewById(R.id.textView7);
        oname = findViewById(R.id.editTextTextPersonName2);
        ophone = findViewById(R.id.editTextTextPersonName3);
        oaddress = findViewById(R.id.editTextTextPersonName4);
        profilePic = findViewById(R.id.imageView);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        button1= findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        reference = FirebaseDatabase.getInstance().getReference("USERS");
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()){

                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userId = firebaseUser.getUid();

                    String vid = datasnapshot.child(userId).child("id").getValue().toString();

                    oid.setText(vid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        // update the user
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nname = oname.getText().toString();
                String naddress = oaddress.getText().toString();
                String nphone = ophone.getText().toString();

                updateUser(nname, naddress, nphone);


            }
        });





///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        profilePic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                choosePicture();
            }
        });
    }


    public void choosePicture() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    public void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();

        StorageReference users = storageReference.child("USERS" + randomKey);

        users.putFile(imageUri)

                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Image uploaded", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed To Upload", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new com.google.firebase.storage.OnProgressListener<TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull TaskSnapshot taskSnapshot) {


                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());

                        pd.setMessage("Percentage : " + (int) progressPercent + "%");
                    }
                });


    }

    public void updateUser(String nname, String naddress, String nphone) {

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userId = firebaseUser.getUid();

        // updating the user via child nodes
        if (!TextUtils.isEmpty(nname))
            reference.child(userId).child("name").setValue(nname);

        if (!TextUtils.isEmpty(naddress))
            reference.child(userId).child("address").setValue(naddress);

        if (!TextUtils.isEmpty(nphone))
            reference.child(userId).child("phone").setValue(nphone);

        Toast.makeText(EditProfile.this, "Updated Successful", Toast.LENGTH_LONG).show();

    }

}







