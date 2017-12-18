package com.example.dominpc.ludogest.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.dominpc.ludogest.Core.DBManager;
import com.example.dominpc.ludogest.R;

public class apuestasShowAll extends AppCompatActivity {
    SimpleCursorAdapter mainCursorAdapter;
    DBManager DBMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuestas_show_all);

        final ListView listApuestas = this.findViewById( R.id.lvApuestas );

        this.registerForContextMenu( listApuestas );
       DBMgr = new DBManager( this.getApplicationContext() );

       listApuestas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(apuestasShowAll.this,apuestaDetallada.class);

               int idDetalle= DBMgr.getIDPosApuesta(i);

               intent.putExtra("id",idDetalle);

               apuestasShowAll.this.startActivity(intent);
           }
       });


    }
    protected void onResume() {
        super.onResume();
        final ListView listApuestas = this.findViewById( R.id.lvApuestas );


         mainCursorAdapter = new SimpleCursorAdapter( this,
                R.layout.lvlistaapuestas,
                null,
                new String[]{ DBManager.APUESTA_ID ,DBManager.APUESTA_EVENTO },
                new int[] { R.id.lvidApuesta, R.id.lveventoApuesta } );


        listApuestas.setAdapter( mainCursorAdapter );
        this.updateLista();



    }

    private void updateLista()
    {
        mainCursorAdapter.changeCursor( DBMgr.getAllApuestas() );
    }

    public void onPause()
    {
        super.onPause();

        DBMgr.close();
        this.mainCursorAdapter.getCursor().close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.menu, menu );
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        boolean   toret= false;


        switch( menuItem.getItemId() ) {
            case R.id.misApuestasMenu:

                Intent intent = new Intent(apuestasShowAll.this, apuestasShowAll.class);

                apuestasShowAll.this.startActivity(intent);
                toret = true;
                break;


            case R.id.consultarEstadisticasMenu:

                Intent intent2 = new Intent(apuestasShowAll.this, showBeneficios.class);

                apuestasShowAll.this.startActivity(intent2);
                toret = true;
                break;

            case R.id.apuAddMenu:

                Intent intent3 = new Intent(apuestasShowAll.this, ApuestaAdd.class);

                apuestasShowAll.this.startActivity(intent3);
                toret = true;
                break;



        }

        return toret;

    }







}
