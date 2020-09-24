package com.example.cakecommand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ActivityThird extends AppCompatActivity{

    TextView txtUser;
    TextView txtIngredientsChosen;
    TextView txtIngredients;
    EditText strIngredient;
    Button btnPlus;
    Button btnDisconnect;
    Button btnRegister;
    Button btnCommands;
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int i=1;
        String appIngredients ="FLOUR"+"\n\n"+"Wheat flour"+"\n\n"+"Almond powder"+"\n\n"+"Corns crash"+"\n\n"+"Mixte nuts powder"+"\n\n"
                +"Other"+"\n\n"+"none"+"\n\n\n"+"SWEETENERS"+"\n\n"+"Refined sugar"+"\n\n"+"Brown sugar"+"\n\n"+"Honey"+"\n\n"+"Corn syrup"+"\n\n"
                +"Other"+"\n\n"+"none"+"\n\n\n"+"FATS"+"\n\n"+"Regular butter"+"\n\n"+"Nuts butter"+"\n\n"+"Coconut oil"+"\n\n"+"Other"+"\n\n"+"none"
                +"\n\n\n"+"LIQUIDS"+"\n\n"+"Regular milk"+"\n\n"+"Nuts milk"+"\n\n"+"Regular Cream"+"\n\n"+"Puts Cream"+"\n\n"+"Other"+"\n\n"+"none"
                +"\n\n\n"+"STUFFING"+"\n\n"+"Berries"+"\n\n"+"Choclate"+"\n\n"+"Cream cheese"+"\n\n"+"Other"+"\n\n"+"none"
                +"\n\n\n"+"OTHERS"+"\n\n"+"Eggs"+"\n\n"+"Baking powder"+"\n\n"+"Flavoring"+"\n\n"+"Other";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        final String User=this.getIntent().getExtras().getString("message");
        txtUser=findViewById(R.id.txtUser);
        txtIngredients=findViewById(R.id.txtIngredients);
        txtIngredientsChosen=findViewById(R.id.txtIngredientsChosen);
        strIngredient=findViewById(R.id.strIngredient);
        btnPlus=findViewById(R.id.btnPlus);
        btnDisconnect=findViewById(R.id.btnDisconnect);
        btnRegister=findViewById(R.id.btnRegister);
        btnCommands=findViewById(R.id.btnCommand);
        txtUser.setText(User);
        txtIngredients.append(appIngredients);
        btnCommands.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ActivityThird.this, ActivityFourth.class);
                intent.putExtra("message",User);
                startActivityForResult(intent, 1934);
            }
        });
        btnDisconnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ActivityThird.this, MainActivity.class);
                startActivityForResult(intent, 1634);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                databaseManager=new DatabaseManager(ActivityThird.this);
                databaseManager.insertCommands(User,txtIngredientsChosen.getText().toString(),0,ActivityThird.this);
                txtIngredientsChosen.setText("");

            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(strIngredient.getText().toString().equals("")) {
                    Toast.makeText(ActivityThird.this, "You haven't chosen any ingredient", Toast.LENGTH_LONG).show();
                }
                if(strIngredient.getText().toString().equals("other")) {
                    Toast.makeText(ActivityThird.this, "Write your chosen other ingredient", Toast.LENGTH_LONG).show();
                }
                else if(strIngredient.getText().toString().equals("flavoring")) {
                    Toast.makeText(ActivityThird.this, "Writet your chosen flavoring", Toast.LENGTH_LONG).show();
                }
                else if(strIngredient.getText().toString().equals("none")) {
                    Toast.makeText(ActivityThird.this, "You haven't chosen any ingredient ", Toast.LENGTH_LONG).show();
                }
                else if(strIngredient.getText().toString().equals("flour")||strIngredient.getText().toString().equals("sweeteners")||strIngredient.getText().toString().equals("fats")||
                        strIngredient.getText().toString().equals("liquids")||strIngredient.getText().toString().equals("stuffing")|strIngredient.getText().toString().equals("others")) {
                    Toast.makeText(ActivityThird.this, "Choose and write an ingredient not a category name", Toast.LENGTH_LONG).show();
                }
                else if(i==1) {txtIngredientsChosen.append(strIngredient.getText().toString()+"\n\n");}
                strIngredient.setText("");
            }
        });

    }
}
