package com.example.dominpc.ludogest.View;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dominpc.ludogest.Core.Apuesta;
import com.example.dominpc.ludogest.Core.SqlIO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dominpc on 12/12/2017.
 */

public class ApuestasApp extends Application {
    private SqlIO db;
    private List<Apuesta> apuestas;

    @Override
    public void onCreate(){
        this.apuestas = new ArrayList<>();
        this.db = new SqlIO(this);
    }


    public SQLiteDatabase getDB()
    {
        return this.db.getWritableDatabase();
    }

    //Listado de series leyendo la bd y devolviendo objetos de tipo serie.
    public List<Apuesta> getListaApuestas(){
        this.leerBDApuesta();
        return this.apuestas;
    }

    //Lectura de la bd para la tabla series mediante un cursor.
    private void leerBDApuesta(){
        SQLiteDatabase db = this.db.getReadableDatabase();
        this.apuestas.clear();

        Cursor cursor = db.rawQuery("SELECT * FROM apuesta",null);

        if ( cursor.moveToFirst() ) {
            do {
                Apuesta apuesta = new Apuesta(cursor.getInt( 0 ), cursor.getString( 1 ));
                this.apuestas.add( apuesta );
            } while( cursor.moveToNext() );

            cursor.close();
        }
        return;
    }


    public void insertarApuesta(String evento, String pronostico, double importe, double cuota, double ganancia, int resultado){
        SQLiteDatabase db = this.getDB();
        try{
            db.beginTransaction();
            db.execSQL("INSERT INTO apuesta(evento, pronostico, importe, cuota, ganancia, resultado) VALUES(?, ?, ?, ?, ?, ?)",new String[]{evento, pronostico, importe, cuota, ganancia, resultado});
            db.setTransactionSuccessful();
            this.leerBDApuesta();
        }finally {
            db.endTransaction();
        }
        return;
    }


    public void eliminarApuesta(int id){
        this.leerBDApuesta();
        SQLiteDatabase db = this.getDB();

        try {
            db.beginTransaction();
            db.execSQL("DELETE FROM apuesta WHERE id=?", new Integer[]{id});
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
        this.leerBDApuesta();
        return;
    }

    public void modificarSerie(int id, String texto){
        this.leerBDApuesta();
        SQLiteDatabase db = this.getDB();

        try{
            db.beginTransaction();
            db.execSQL("UPDATE Serie SET nombre ='" + texto + "' WHERE id=?", new Integer[]{id});
            db.setTransactionSuccessful();

        }finally {
            db.endTransaction();
        }
        this.leerBDApuesta();
        return;
    }


    //Método que cambia a 0 si el capitulo no se ha visto o a 1 en caso contrario.
    public void resultado(final int idApuesta, int change){
        SQLiteDatabase db = this.db.getReadableDatabase();
        try{
            db.beginTransaction();
            db.execSQL("UPDATE apuesta SET resultado ='" + change + "' WHERE id= '"+ idApuesta+ "'");
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }
    }

    //Devuelve un objeto de tipo capitulo según un id.
    public Capitulo getCapituloById(final int idCapitulo){
        SQLiteDatabase db = this.db.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM capitulo WHERE id='" + idCapitulo + "'",null);
        cursor.moveToFirst();
        Capitulo capitulo = new Capitulo(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3),cursor.getInt(4), cursor.getInt(5));

        return capitulo;
    }

}
