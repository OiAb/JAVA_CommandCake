package com.example.cakecommand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySecond extends AppCompatActivity {

    EditText strNom;
    EditText strPassword;
    EditText strCheckPassword;
    EditText strEmail;
    Button btnSignUp;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        strNom = findViewById(R.id.strNom);
        strPassword = findViewById(R.id.strPassword);
        strCheckPassword=findViewById(R.id.strCheckPassword);
        strEmail=findViewById(R.id.strEmail);
        btnSignUp = findViewById(R.id.btnSignup);
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                databaseManager=new DatabaseManager(ActivitySecond.this);
                if(!strPassword.getText().toString().equals(strCheckPassword.getText().toString()))
                    Toast.makeText(ActivitySecond.this, "The Checked Password doesn't match the Password ", Toast.LENGTH_LONG).show();
                if(databaseManager.insertUsers(strNom.getText().toString(),strPassword.getText().toString(), strCheckPassword.getText().toString(), strEmail.getText().toString(), ActivitySecond.this)) {
                    Intent intent = new Intent(ActivitySecond.this, MainActivity.class);
                    intent.putExtra("message",strNom.getText().toString());
                    startActivityForResult(intent, 1034);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1234){}
    }
}
