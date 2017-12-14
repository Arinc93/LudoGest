package com.example.dominpc.ludogest.Core;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by arinc on 14/12/2017.
 */


/*
    private String evento;
    private String pronostico;
    private double importe;
    private double cuota;
    private double ganancia;
    private int resultado;
 */



public class DBManager extends SQLiteOpenHelper {

    public static final String DB_NOMBRE = "ApuestasDB";
    public static final int DB_VERSION = 1;

    private List<Apuesta> apuestas;
    public static final String TABLA_APUESTAS = "apuestas";
    private int numElem;
    private int maxElem = 1000;

    public static final String APUESTA_ID = "_id";
    public static final String APUESTA_EVENTO = "evento";
    public static final String APUESTA_PRONOSTICO = "pronostico";
    public static final String APUESTA_IMPORTE = "importe";
    public static final String APUESTA_CUOTA = "cuota";

    public static final String APUESTA_RESULTADO = "resultado";


    public DBManager(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBManager",
                "Creando BBDD " + DB_NOMBRE + " v" + DB_VERSION);

        try {
            db.beginTransaction();


            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_APUESTAS + "("
                    + APUESTA_ID + " int PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + APUESTA_EVENTO + " string(255) NOT NULL,"
                    + APUESTA_PRONOSTICO + " string(255) NOT NULL,"
                    + APUESTA_IMPORTE + " real NOT NULL, "
                    + APUESTA_CUOTA + " real NOT NULL,"
                    + APUESTA_RESULTADO + " int NOT NULL )");


         /* db.execSQL( "CREATE TABLE IF NOT EXISTS apuestas( "
                    + "id int PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "evento string(255) NOT NULL, "
                    + "pronostico string(255) NOT NULL, "
                    + "importe real NOT NULL, "
                    + "cuota real NOT NULL, "
                    + "ganancia real NOT NULL,"
                    + "resultado int)");*/

            db.setTransactionSuccessful();
        } catch (SQLException exc) {
            Log.e("DBManager.onCreate", exc.getMessage());
        } finally {
            db.endTransaction();
        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBManager",
                "DB: " + DB_NOMBRE + ": v" + oldVersion + " -> v" + newVersion);

        try {
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_APUESTAS);
            db.setTransactionSuccessful();
        } catch (SQLException exc) {
            Log.e("DBManager.onUpgrade", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        this.onCreate(db);
    }

    public List<Apuesta> getListaApuestas() {
        this.leerBDApuestas();
        return apuestas;

    }

    private void leerBDApuestas() {
        SQLiteDatabase db = this.getReadableDatabase();
        this.apuestas.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM apuestas ", null);

        if (cursor.moveToFirst()) {
            do {
                Apuesta apu = new Apuesta(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getDouble(5), cursor.getInt(6));
                this.apuestas.add(apu);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    private void getNumElemBD() {
        numElem = 0;
        List<Apuesta> apusAux = getListaApuestas();
        for (Apuesta apAux : apusAux) {
            numElem++;
        }
    }

    public List<String> listEventos() {
        List<Apuesta> apuest = getListaApuestas();
        List<String> events = new LinkedList<>();
        for (Apuesta apAux : apuest) {
            events.add(apAux.getEvento());
        }
        return events;
    }


    public void add(String even, String pron, double imp, double cuo, int result) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        ContentValues values = new ContentValues();
        values.put(APUESTA_EVENTO, even);
        values.put(APUESTA_PRONOSTICO, pron);
        values.put(APUESTA_IMPORTE, imp);
        values.put(APUESTA_CUOTA, cuo);
        values.put(APUESTA_RESULTADO, result);

        try {
            db.beginTransaction();
            cursor = db.query();



        } finally {
            if (cursor != null) {
                cursor.close();
            }

            db.endTransaction();


        }

    }


}