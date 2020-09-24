package com.example.cakecommand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText strNom;
    EditText strPassword;
    Button btnSignin;
    Button btnSignUp;
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strNom = findViewById(R.id.strNom);
        strPassword = findViewById(R.id.strPassword);
        btnSignin = findViewById(R.id.btnSignin);
        btnSignUp = findViewById(R.id.btnSignup);
        btnSignin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                databaseManager=new DatabaseManager(MainActivity.this);
               if(databaseManager.AuthUsers(strNom.getText().toString(),strPassword.getText().toString(),MainActivity.this)) {
                    Intent intent = new Intent(MainActivity.this, ActivityThird.class);
                    intent.putExtra("message",strNom.getText().toString());
                    startActivityForResult(intent, 1134);
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ActivitySecond.class);
                startActivityForResult(intent, 1234);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1234){}
    }
}
