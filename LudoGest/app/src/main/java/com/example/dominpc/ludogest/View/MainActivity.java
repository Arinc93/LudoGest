package com.example.dominpc.ludogest.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dominpc.ludogest.Core.Apuesta;
import com.example.dominpc.ludogest.Core.DBManager;
import com.example.dominpc.ludogest.R;

import com.example.dominpc.ludogest.Core.Apuesta;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Apuesta> list;
    private ArrayAdapter<Apuesta> listAdapter;
    DBManager DBmgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.DBmgr = new DBManager( this.getApplicationContext() );


    }



    public void goToMisApuestas(View view) {
        Intent intent = new Intent(MainActivity.this, apuestasShowAll.class);
        MainActivity.this.startActivity(intent);
    }

    public void goToAddApuesta(View view) {

        Intent intent = new Intent(MainActivity.this, ApuestaAdd.class);
        MainActivity.this.startActivity(intent);
    }
    public void goToBeneficios(View view) {
        Intent intent = new Intent(MainActivity.this, showBeneficios.class);
        MainActivity.this.startActivity(intent);
    }

    public void insertaPrueba(){
        String even ="hue";
        String pron="hue";
        double imp=0;
        double cuo =0;
        int result = 0;


        DBmgr.add(even,pron,imp,cuo,result);
    }


    public void eliminaPrueba(){
        DBmgr.elimina(2);

    }
}











