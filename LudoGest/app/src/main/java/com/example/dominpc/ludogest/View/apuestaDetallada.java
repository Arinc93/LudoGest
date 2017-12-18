package com.example.dominpc.ludogest.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dominpc.ludogest.Core.Apuesta;
import com.example.dominpc.ludogest.Core.DBManager;
import com.example.dominpc.ludogest.R;

public class apuestaDetallada extends AppCompatActivity {
    DBManager DBMgr;
    Apuesta apuestaShow;
    int resultAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuesta_detallada);
        DBMgr = new DBManager( this.getApplicationContext() );

        Bundle datosEnviados = this.getIntent().getExtras();

       final int idapu = datosEnviados.getInt("id");


        final Button btSupr = this.findViewById( R.id.deleteBt );
        btSupr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apuestaDetallada.this.eliminarApuesta(idapu);
            }
        });


        apuestaShow= DBMgr.searchFor(idapu);




        TextView nombreEvento= this.findViewById(R.id.eventoTV);
        nombreEvento.setText(apuestaShow.getEvento());

        TextView pronostico= this.findViewById(R.id.pronosticoTV);
        pronostico.setText(apuestaShow.getPronostico());

        TextView importe= this.findViewById(R.id.importeTV);
        String imporStr=Double.toString(apuestaShow.getImporte());
        importe.setText(imporStr);

        TextView cuota= this.findViewById(R.id.cuotaTV);
        String cuotStr=Double.toString(apuestaShow.getCuota());
        cuota.setText(cuotStr);

        TextView ganancia= this.findViewById(R.id.gananciaTV);
         String gananStr=Double.toString(  apuestaShow.getImporte() * apuestaShow.getCuota());
        ganancia.setText(gananStr);


        final EditText edresult= this.findViewById(R.id.resultadoApuestaTXT);

        edresult.setText(Integer.toString(apuestaShow.getResultado()));
/*
        edresult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                resultAux = Integer.parseInt(edresult.getText().toString());
               System.out.println("resultaux en el listener"+resultAux);
            }
        });*/



        final Button btUpda = this.findViewById( R.id.modificaBT );
        btUpda.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                final EditText edresult= findViewById(R.id.resultadoApuestaTXT);
                resultAux = Integer.parseInt(edresult.getText().toString());




                apuestaDetallada.this.modificaApuesta(idapu,apuestaShow.getEvento(),apuestaShow.getPronostico(),apuestaShow.getImporte(),apuestaShow.getCuota(),resultAux);
            }
        });


    }


    public void eliminarApuesta(int idSupr){
        Toast t = Toast.makeText( this,"Apuesta eliminada", Toast.LENGTH_LONG );
        t.setGravity( Gravity.BOTTOM| Gravity.CENTER, 0, 0 );
        t.show();

        Intent intent = new Intent(apuestaDetallada.this, apuestasShowAll.class);

        apuestaDetallada.this.startActivity(intent);


        DBMgr.elimina(idSupr);
    }



    public void modificaApuesta(int idU,String even, String pron, double imp, double cuo, int result  ){

    if(result<0||result>2){

        Toast t = Toast.makeText( this,"Introduce en el resultado:                                                         0 si está pendiente                                                  1 si está acertada                                                   2 si esta  fallida", Toast.LENGTH_LONG );
        t.setGravity( Gravity.BOTTOM | Gravity.CENTER, 0, 0 );
        t.show();


    }else{
        DBMgr.actualizarApuesta(idU,even,pron,imp,cuo,result);


        Intent intent = new Intent(apuestaDetallada.this, apuestasShowAll.class);

        apuestaDetallada.this.startActivity(intent);

    }


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

                Intent intent = new Intent(apuestaDetallada.this, apuestasShowAll.class);

                apuestaDetallada.this.startActivity(intent);
                toret = true;
                break;


            case R.id.consultarEstadisticasMenu:

                Intent intent2 = new Intent(apuestaDetallada.this, showBeneficios.class);

                apuestaDetallada.this.startActivity(intent2);
                toret = true;
                break;

            case R.id.apuAddMenu:

                Intent intent3 = new Intent(apuestaDetallada.this, ApuestaAdd.class);

                apuestaDetallada.this.startActivity(intent3);
                toret = true;
                break;



        }

        return toret;

    }






    public void onPause()
    {
        super.onPause();

        this.DBMgr.close();

    }

}
