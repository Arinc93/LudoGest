package com.example.dominpc.ludogest.Core;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
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

    private List<Apuesta> apuestas= new ArrayList<>();
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
                    + APUESTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
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






    public void add(String even, String pron, double imp, double cuo, int result) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(APUESTA_EVENTO, even);
        values.put(APUESTA_PRONOSTICO, pron);
        values.put(APUESTA_IMPORTE, imp);
        values.put(APUESTA_CUOTA, cuo);
        values.put(APUESTA_RESULTADO, result);

        try {

            db.beginTransaction();

            db.insert( TABLA_APUESTAS, null, values );


            db.setTransactionSuccessful();

        }catch(SQLException exc) {
            Log.e( "dbAdd", exc.getMessage() );
        } finally {

            db.endTransaction();

        }

    }

    public int getIDPosApuesta(int posi){
        int IDpos=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur= db.query( DBManager.TABLA_APUESTAS, new  String[]{APUESTA_ID}, null, null, null, null, null);
        cur.moveToFirst();

        for(int i=0;i<posi;i++){
          cur.moveToNext();


        }

        IDpos=cur.getInt(0);
        return IDpos;

    }


        public int getNumApuestas(){
            int numero=0;
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cur= db.query( DBManager.TABLA_APUESTAS, null, null, null, null, null, null);

            numero=cur.getCount();
        return numero;
}





    public void elimina( int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("Antes de eliminar");
        try{
            db.beginTransaction();
            db.delete( TABLA_APUESTAS,  APUESTA_ID + " =?", new  String[] {toString().valueOf(id)} );
            db.setTransactionSuccessful();
            System.out.println("Despues de eliminar");
        }catch(SQLException exc) {
            Log.e("dbElimina", exc.getMessage() );

        }
        finally{
            db.endTransaction();
        }
    }
    public Apuesta searchFor(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String idStr = ""+id;
        Cursor toret = null;
        Apuesta apu= null;
        try{
            toret= db.query(TABLA_APUESTAS,null, APUESTA_ID + " LIKE ?", new String[]{idStr}, null,null,null);
        }catch(SQLException exc) {
            Log.e( "DBManager.searchFor", exc.getMessage() );
        }

            toret.moveToFirst();

                apu = new Apuesta(toret.getInt( 0 ), toret.getString( 1 ),toret.getString( 2),toret.getDouble( 3),toret.getDouble( 4),toret.getInt( 5 ));
            toret.close();


        return apu;
    }


    public Cursor getAllApuestas()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.query( DBManager.TABLA_APUESTAS, null, null, null, null, null, null);
    }




    public void actualizarApuesta(int idU,String even, String pron, double imp, double cuo, int result){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(APUESTA_EVENTO, even);
        values.put(APUESTA_PRONOSTICO, pron);
        values.put(APUESTA_IMPORTE, imp);
        values.put(APUESTA_CUOTA, cuo);
        values.put(APUESTA_RESULTADO, result);


        try{
            System.out.println("Antes de actualizar");
            db.beginTransaction();
            db.update(TABLA_APUESTAS, values,APUESTA_ID +" =?",new  String[] {toString().valueOf(idU)});

            System.out.println("Despues de actualizar");

            db.setTransactionSuccessful();
        }catch(SQLException exc){
            Log.e("actualizaApuesta", exc.getMessage() );

        }finally{
            db.endTransaction();
        }



    }


}