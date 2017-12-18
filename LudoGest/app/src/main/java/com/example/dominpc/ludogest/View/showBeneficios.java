package com.example.dominpc.ludogest.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dominpc.ludogest.Core.DBManager;
import com.example.dominpc.ludogest.R;

public class showBeneficios extends AppCompatActivity {
    DBManager DBMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_beneficios);
        DBMgr = new DBManager( this.getApplicationContext() );

        TextView apuestaTotal= this.findViewById(R.id.apuestaTotalTXT);
        String imporStr=Double.toString(DBMgr.consultarApuestaTotal());

        apuestaTotal.setText(imporStr);



        TextView ganaciasVi= this.findViewById(R.id.gananciasTXT);
        String gananSTR=Double.toString( DBMgr.getGanancias());

        ganaciasVi.setText(gananSTR);


        TextView perdidasVi= this.findViewById(R.id.perdidasTXT);
        String perdidasSTR=Double.toString( DBMgr.getPerdidas());
        perdidasVi.setText(perdidasSTR);


        TextView beneficiosVi= this.findViewById(R.id.beneficiosTXT);
        String beneficiosSTR=Double.toString( DBMgr.getBeneficios());
        beneficiosVi.setText(beneficiosSTR);



    }


    public void onPause()
    {
        super.onPause();

        this.DBMgr.close();

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

                Intent intent = new Intent(showBeneficios.this, apuestasShowAll.class);

                showBeneficios.this.startActivity(intent);
                toret = true;
                break;


            case R.id.consultarEstadisticasMenu:

                Intent intent2 = new Intent(showBeneficios.this, showBeneficios.class);

                showBeneficios.this.startActivity(intent2);
                toret = true;
                break;

            case R.id.apuAddMenu:

                Intent intent3 = new Intent(showBeneficios.this, ApuestaAdd.class);

                showBeneficios.this.startActivity(intent3);
                toret = true;
                break;



        }

        return toret;

    }


}
