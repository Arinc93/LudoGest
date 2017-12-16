package com.example.dominpc.ludogest.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dominpc.ludogest.Core.Apuesta;
import com.example.dominpc.ludogest.Core.DBManager;
import com.example.dominpc.ludogest.R;

public class apuestaDetallada extends AppCompatActivity {
    DBManager DBMgr;
    Apuesta apuestaShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuesta_detallada);
        DBMgr = new DBManager( this.getApplicationContext() );
        final Button btSupr = this.findViewById( R.id.deleteBt );
        Bundle datosEnviados = this.getIntent().getExtras();

       final int idapu = datosEnviados.getInt("id");



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
    }


    public void eliminarApuesta(int idSupr){
     DBMgr.elimina(idSupr);

    }




}
