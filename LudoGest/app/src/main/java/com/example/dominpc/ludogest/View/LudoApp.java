package com.example.dominpc.ludogest.View;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dominpc.ludogest.Core.Apuesta;
import com.example.dominpc.ludogest.Core.SqlIO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arinc on 14/12/2017.
 */


public class LudoApp extends Application{

private SqlIO db;
private List<Apuesta> apuestas;


@Override
public void onCreate(){
   this.apuestas= new ArrayList<>() ;
   this.db= new SqlIO(this);


}

public SQLiteDatabase getDB(){
    return this.db.getWritableDatabase();
}
public List<Apuesta> getListaApuestas(){
    this.leerBDApuestas();
    return apuestas;


}
private void leerBDApuestas(){
    SQLiteDatabase db=this.db.getReadableDatabase();
    this.apuestas.clear();
    Cursor cursor=db.rawQuery("SELECT * FROM apuesta ",null);

    if(cursor.moveToFirst()){
        do{
            Apuesta apu =new Apuesta(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getDouble(5),cursor.getInt(6));
            this.apuestas.add(apu);
        }while(cursor.moveToNext());
        cursor.close();
    }


}

public void insertApuesta(){
SQLiteDatabase db = this.getDB();
    try{
     db.beginTransaction();
      db.execSQL("INSERT INTO apuesta(evento,pronostico,importe,cuota,ganancia,resultado)VALUES(?,?,?,?,?,?)");

    }finally{

    }
}

}
