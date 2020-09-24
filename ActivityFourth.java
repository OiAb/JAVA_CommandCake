package com.example.cakecommand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityFourth extends AppCompatActivity {

    String User;

    TextView txtRegisteredComm;
    TextView txtConfirmedComm;
    EditText strNumCommand;
    TextView txtnoun;
    Button btnConfirm;
    Button btnDis;
    DatabaseManager databaseManager;
    List<CommandData> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseManager=new DatabaseManager(ActivityFourth.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        User=this.getIntent().getExtras().getString("message");
        txtRegisteredComm=findViewById(R.id.txtRegisteredComm);
        txtConfirmedComm=findViewById(R.id.txtConfirmedComm);
        strNumCommand=findViewById(R.id.strNumCommand);
        btnConfirm=findViewById(R.id.btnConfirm);
        txtnoun=findViewById(R.id.txtnoun);
        btnDis=findViewById(R.id.btnDis);
        txtnoun.setText(User);
        list=databaseManager.getResigteredCommands(User);
        for(CommandData str:list)txtRegisteredComm.append(str+""+"-> Not confirmed command "+"\n\n");
        list=databaseManager.getConfirmedCommands(User);
        for(CommandData str:list)txtConfirmedComm.append(str+"-> Confirmed command");
        btnDis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ActivityFourth.this, MainActivity.class);
                startActivityForResult(intent, 1134);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int i=Integer.parseInt(strNumCommand.getText().toString());
                Log.i("DATABASE","tbedel invoked"+i);
                if(databaseManager.idExist(i,User)) {
                    list = databaseManager.getIngredientsAndMakeConfirm(i,User);
                    for (CommandData str : list)
                        txtConfirmedComm.append(str + "-> Confirmed command");
                    Toast.makeText(ActivityFourth.this, "Your command has been confirmed", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(ActivityFourth.this, "the id doesen't match any command ", Toast.LENGTH_LONG).show();
                list=databaseManager.getResigteredCommands(User);
                txtRegisteredComm.setText("");
                for(CommandData str:list)txtRegisteredComm.append(str+"-> Not confirmed command"+"\n\n");
            }
        });
    }
}
