package com.shreyxnsh.vtop.ui.feedbacks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shreyxnsh.vtop.R;

import java.util.HashMap;
import java.util.Objects;

public class FeedbackActivity extends AppCompatActivity {

    EditText edtname,edtnumber, edtemail,edtmessage;
    MaterialButton btnsend;
    Spinner spinner;
    DatabaseReference databaseReference;
    private Toolbar toolbar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        toolbar = findViewById(R.id.appbarFeed);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Feedback");
        toolbar.setTitleTextAppearance(this, R.style.poppins_bold);


        edtname=findViewById(R.id.feedname);
        edtnumber =findViewById(R.id.feednumber);
        edtemail=findViewById(R.id.feedemail);
        edtmessage=findViewById(R.id.feedmessage);
        btnsend=findViewById(R.id.feedbtnsend);
        spinner=findViewById(R.id.feedspinner);

        databaseReference= FirebaseDatabase.getInstance().getReference("Feedback");

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });



    }

    private void checkValidation() {
        String name=edtname.getText().toString();
        String number=edtnumber.getText().toString();
        String email=edtemail.getText().toString();
        String message=edtmessage.getText().toString();
        String batch=spinner.getSelectedItem().toString();

        if (name.isEmpty()){
            edtname.setError("Empty");
            edtname.requestFocus();
        }else if(email.isEmpty()){
            edtemail.setError("Empty");
            edtemail.requestFocus();
        }else if(number.isEmpty()){
            edtnumber.setError("Empty");
            edtnumber.requestFocus();
        }else if(message.isEmpty()){
            edtmessage.setError("Empty");
            edtmessage.requestFocus();
        }else if (batch.equals("Select Department")){
            Toast.makeText(this, "Please select a department", Toast.LENGTH_SHORT).show();
        }else{
            getFeedback();
        }

    }

    private void getFeedback() {

        String name=edtname.getText().toString();
        String number=edtnumber.getText().toString();
        String email=edtemail.getText().toString();
        String message=edtmessage.getText().toString();
        String batch=spinner.getSelectedItem().toString();

        FeedbackData feedbackData=new FeedbackData(name,number,email,message,batch);

        databaseReference.push().setValue(feedbackData);
        Toast.makeText(this, "Your FeedBack is sent! ", Toast.LENGTH_SHORT).show();
    }
}