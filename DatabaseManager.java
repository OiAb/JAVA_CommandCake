package com.example.cakecommand;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="CakeCommand.DB";
    private static final int DATABASE_VERSION=4;
    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stringSQL="create table T_Commands("
                +"id integer primary key autoincrement,"
                +"user text not null,"
                +"ingredients varchar(100"+") not null,"
                +"isConfirmed integer not null)";
        String strSQL = "create table T_Users("
                +"name text primary key,"
                + "password text not null,"
                +"email varchar(20"+") not null)";
        sqLiteDatabase.execSQL(strSQL);
        sqLiteDatabase.execSQL(stringSQL);
        Log.i("DATABASE","onCreate invoked");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String strSQL="drop table T_Users";
        String stringSQL="drop table T_Commands";
        sqLiteDatabase.execSQL(strSQL);
        //sqLiteDatabase.execSQL(stringSQL);
        this.onCreate(sqLiteDatabase);
        Log.i("DATABASE","onUpgrade invoked");
    }
    public boolean insertUsers(String name, String password, String CheckPassword, String email,Context context){
        String stringSQL="select * from T_Users";
        Cursor cursor =this.getReadableDatabase().rawQuery(stringSQL, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            if(name.equals(cursor.getString(0))||email.equals(cursor.getString(2))) {
                cursor.close();
                Toast.makeText(context, "An account already created with the same information", Toast.LENGTH_LONG).show();
                return false;
            }
            cursor.moveToNext();
        }
        cursor.close();
        if(email.equals("")&&name.equals("")&&password.equals("")){
            Toast.makeText(context, "Write your information", Toast.LENGTH_LONG).show();
            return false;
        }
        if(email.equals("")||name.equals("")||password.equals("")){
            Toast.makeText(context, "Complete your information", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!CheckPassword.equals(password)){
            Toast.makeText(context, "The Checked Password doesn't match the Password", Toast.LENGTH_LONG).show();
            return false;
        }
        name=name.replace("'","''");
        password=password.replace("'","''");
        CheckPassword=CheckPassword.replace("'","''");
        String strSQL="insert into T_Users (name , password, email) values('"
                +name+"', '"+password+"', '"+email+"')";
        this.getWritableDatabase().execSQL(strSQL);
        Toast.makeText(context, "Your account has been created", Toast.LENGTH_LONG).show();
        Log.i("DATABASE","insertUsers invoked");
        return true;
    }
    public boolean AuthUsers(String nameAuth, String passwordAuth,Context context){
        Log.i("DATABASE","AuthUsers invoked");
        if(nameAuth.equals("")&&passwordAuth.equals("")){
            Toast.makeText(context, "Write your name and password", Toast.LENGTH_LONG).show();
            return false;
        }
        if(nameAuth.equals("")){
            Toast.makeText(context, "Write your name", Toast.LENGTH_LONG).show();
            return false;
        }
        if(passwordAuth.equals("")){
            Toast.makeText(context, "Write your password", Toast.LENGTH_LONG).show();
            return false;
        }
        String strSQL="select * from T_Users";
        Cursor cursor =this.getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            if(nameAuth.equals(cursor.getString(0))&&passwordAuth.equals(cursor.getString(1))) {
                cursor.close();
                return true;
            }
            cursor.moveToNext();
        }
        cursor.close();
        Toast.makeText(context, "These information don't match any account !", Toast.LENGTH_LONG).show();
        return false;
    }
    public boolean insertCommands(String user, String ingredients, int isConfirmed,Context context){
        if(ingredients.equals("")){
            Toast.makeText(context, "Write the ingredients", Toast.LENGTH_LONG).show();
            return false;
        }
        String stringSQL="select * from T_Commands";
        Cursor cursor =this.getReadableDatabase().rawQuery(stringSQL, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            if(user.equals(cursor.getString(1))&&ingredients.equals(cursor.getString(2))) {
                cursor.close();
                Toast.makeText(context, "A command already registered with the same information", Toast.LENGTH_LONG).show();
                return false;
            }
            cursor.moveToNext();
        }
        cursor.close();
        user=user.replace("'","''");
        ingredients=ingredients.replace("'","''");
        String strSQL="insert into T_Commands (user , ingredients, isConfirmed) values('"
                +user+"', '"+ingredients+"', "+isConfirmed+")";
        this.getWritableDatabase().execSQL(strSQL);
        Log.i("DATABASE","insertCommands invoked");
        Toast.makeText(context, "Your command has been registered", Toast.LENGTH_LONG).show();
        return true;
    }
    public List<CommandData> getIngredientsAndMakeConfirm(int id,String user){
        Log.i("DATABASE","tbedel invoked");
        String strSQL="select * from T_Commands";
        String stringSQL="update T_Commands set isConfirmed=1 where id="+id;
        this.getWritableDatabase().execSQL(stringSQL);
        List<CommandData> ingredients=new ArrayList<>();
        Cursor cursor =this.getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            if(id==cursor.getInt(0)&&cursor.getString(1).equals(user)) {
                Log.i("DATABASE","tbedel invoked");
                CommandData ing=new CommandData(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                ingredients.add(ing) ;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return ingredients;
    }
    public List<CommandData> getResigteredCommands(String user){
        String strSQL="select * from T_Commands";
        List<CommandData> commands= new ArrayList<>();
        Cursor cursor =this.getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            if(cursor.getInt(3)==0&&cursor.getString(1).equals(user)){CommandData score=new CommandData(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3));
            commands.add(score);}
            cursor.moveToNext();
        }
        cursor.close();

        return commands;
    }
    public List<CommandData> getConfirmedCommands(String user){
        String strSQL="select * from T_Commands";
        List<CommandData> commands= new ArrayList<>();
        Cursor cursor =this.getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            if(cursor.getInt(3)==1&&cursor.getString(1).equals(user)){CommandData score=new CommandData(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                commands.add(score);}
            cursor.moveToNext();
        }
        cursor.close();

        return commands;
    }
    public boolean idExist(int id,String user){
        String strSQL="select * from T_Commands";
        Cursor cursor =this.getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            if(cursor.getInt(0)==id&&cursor.getString(1).equals(user)) return true;
            cursor.moveToNext();
        }
        cursor.close();
        return false;
    }
}
