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
    private ApuestasApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Colocar icono en la Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_ludogest);

        this.app = (ApuestasApp) this.getApplication();

        Button btAdd = (Button) this.findViewById(R.id.Addapuesta_button);
        final ListView listApuestas = (ListView) this.findViewById(R.id.listApuestas);

        this.list = app.getListaApuestas();
        this.listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_selectable_list_item,
                this.list
        );


        listApuestas.setAdapter(this.listAdapter);

        //Al hacer clic en la serie pasamos al activity donde se gestionan las temporadas.
        listSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,SeriesActivity.class);
                Serie serie = app.getListaSeries().get( position );
                int idSerie = serie.getId();
                intent.putExtra("idSerie",idSerie);
                MainActivity.this.startActivity(intent);

            }
        });

        //Mediante un clic largo podemos eliminar o modificar el nombre de una serie.
        listSeries.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Serie serie = app.getListaSeries().get( position );
                MainActivity.this.setLista(position, serie.getId() );
                return false;
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onAdd();
            }
        });

        if(listAdapter.getCount() == 0){
            Toast t =Toast.makeText(getApplicationContext(),"Añade las series que estas viendo mediante el boton añadir.",Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
        }

    }

    //Crea el menu cuando se pulse en la opcion
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate( R.menu.main_menu, menu);
        return true;
    }

    //Recorre el menu para saber cual es la opcion que hemos escogido,buscar una serie en la web,tener una lista de pendientes o salir.
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch ( menuItem.getItemId()){
            case R.id.opBuscar:
                Intent intentBuscar = new Intent(MainActivity.this,favoritasActivity.class);
                MainActivity.this.startActivity(intentBuscar);
                break;
            case R.id.opPendientes:
                Intent intentPendientes = new Intent(MainActivity.this,DeseadasActivity.class);
                MainActivity.this.startActivity(intentPendientes);
            case R.id.opSalir:
                finish();
        }
        return true;
    }

    //Funcion que muestra dos opciones y las llama segun cual eligas(modificar serie o eliminar serie)
    public void setLista(final int position, final int id){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setMessage("¿Que desea hacer?");
        dlg.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.modify(position,id);
            }
        });
        dlg.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.remove(position,id);
            }
        });
        dlg.create().show();
    }

    //Funcion que añade a la lista una serie.
    private void onAdd(){
        final EditText edText = new EditText( this );

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setMessage( "Nombre" );
        builder.setView( edText );
        builder.setPositiveButton( "Añadir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String text = edText.getText().toString();
                app.insertarSerie( text );
                MainActivity.this.list = app.getListaSeries();
                MainActivity.this.listAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }

    //Funcion que modifica el nombre de una serie
    private void modify( final int position,final int id){
        final EditText edText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Nombre");
        builder.setTitle("Modificacion");
        builder.setView(edText);
        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String text = edText.getText().toString();
                app.modificarSerie(id,text);
                MainActivity.this.listAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }

    //Funcion que elimina una serie
    private void remove(final int position,final int id){
        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setMessage("¿Quiere borrar esta serie?");
        dlg.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if( position >= 0) {
                    app.eliminarSerie(id);
                    //MainActivity.this.list.remove(position);
                    MainActivity.this.listAdapter.notifyDataSetChanged();
                }else{
                    dlg.setMessage("No se pudo borrar");
                }
            }
        });
        dlg.setNegativeButton("No", null);
        dlg.create().show();
    }
}
