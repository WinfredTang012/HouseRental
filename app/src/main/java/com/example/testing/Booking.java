package com.example.testing;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Booking extends AppCompatActivity {



    public EditText fullName, applicantNumber, applicantMatric, applicantEmail, applicantBornDate, applicantMoveIn;

    public ImageView imageView;
    public Button btOpen, btBook;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    //creating a variable for database
    FirebaseDatabase firebaseDatabase;


    //Firebase Database
    DatabaseReference reference;



    //create variable for class
    BookingInfo bookingInfo;


//    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
//            "[a-zA-Z0-9_+&*-]+)*@" +
//            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//            "A-Z]{2,7}$";
//
//    Pattern pat = Pattern.compile(emailRegex);


//    FirebaseDatabase database = FirebaseDatabase.getInstance("https://testfirebase-98765-default-rtdb.asia-southeast1.firebasedatabase.app/");
//    DatabaseReference reference = database.getReference("BOOKINGS");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);




        //Assign Variable
        //mStorage = FirebaseStorage.getInstance().getReference();
        imageView = findViewById(R.id.image_view);
        btOpen = findViewById(R.id.bt_open);
        //mProgress = new ProgressDialog(this);
        //Variable for EditText
        fullName = findViewById(R.id.etFullName);
        applicantNumber = findViewById(R.id.etApplicantPhone);
        applicantMatric = findViewById(R.id.etMatricId);
        applicantEmail = findViewById(R.id.etApplicantEmail);
        applicantBornDate = findViewById(R.id.etApplicantBorn);
        applicantMoveIn = findViewById(R.id.etMoveIn);

        //instance of our firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //get reference for database
        reference = firebaseDatabase.getReference("BookingInfo").push();

        //initializing our object class variable
        bookingInfo = new BookingInfo();

        //adding on click listener for our button
        btBook = findViewById(R.id.btn_BookProperty);


        //Request For Camera Permission
        if (ContextCompat.checkSelfPermission(Booking.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Booking.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        btBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity=new Intent (Booking.this, Onlinepayment.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }

    private void addDatatoFirebase(String fullname, String phonenumber, String matricid, String email, String birthdate, String moveindate) {
        //set data in our object class
        bookingInfo.setEtFullName(fullname);
        bookingInfo.setEtApplicantPhone(phonenumber);
        bookingInfo.setEtApplicantEmail(email);
        bookingInfo.setEtApplicantBorn(birthdate);
        bookingInfo.setEtMoveIn(moveindate);

        //ADD VALUE EVENT LISTNER METHOD CALLED with databse reference
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //inside the method of on data change
                //object to database
                //databse reference send data to firebase
                reference.setValue(bookingInfo);

                //after adding this data we are showing toast message.
                Toast.makeText(Booking.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //if the data is not added or it is canceled
                //display the failure toast message
                Toast.makeText(Booking.this, "Fail to add data" + error, Toast.LENGTH_SHORT).show();

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            //Get Captured Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //Get Captured Image to ImageView
            imageView.setImageBitmap(captureImage);








        }
    }
}