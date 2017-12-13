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
import com.example.dominpc.ludogest.R;

import com.example.dominpc.ludogest.Core.Apuesta;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Apuesta> list;
    private ArrayAdapter<Apuesta> listAdapter;
   // private ApuestasApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Colocar icono en la Action Bar
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_ludogest);*/

        //this.app = (ApuestasApp) this.getApplication();

        //Button btAdd = (Button) this.findViewById(R.id.Misapuestas_button);


       /* this.list = app.getListaApuestas();
        this.listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_selectable_list_item,
                this.list
        );*/


        //listApuestas.setAdapter(this.listAdapter);

        //Al hacer clic en la serie pasamos al activity donde se gestionan las temporadas.
       /* btAdd.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,misApuestasActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });*/
    }

    /*public void goToMisApuestas() {
        Intent intent = new Intent(MainActivity.this, misApuestasActivity.class);
        MainActivity.this.startActivity(intent);
    }*/


    public void goToMisApuestas(View view) {
        Intent intent = new Intent(MainActivity.this, misApuestasActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void goToAddApuesta(View view) {
        Intent intent = new Intent(MainActivity.this, ApuestaAdd.class);
        MainActivity.this.startActivity(intent);
    }

}











