package com.example.dominpc.ludogest.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        });



        final Button btUpda = this.findViewById( R.id.modificaBT );
        btUpda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("resultaux antes de actualizar"+resultAux);
                apuestaDetallada.this.modificaApuesta(idapu,apuestaShow.getEvento(),apuestaShow.getPronostico(),apuestaShow.getImporte(),apuestaShow.getCuota(),resultAux);
            }
        });


    }


    public void eliminarApuesta(int idSupr){
     DBMgr.elimina(idSupr);
    }



    public void modificaApuesta(int idU,String even, String pron, double imp, double cuo, int result  ){
        System.out.println("id en la funcion"+idU);


        System.out.println("resultaux en la funcion"+resultAux);
       DBMgr.actualizarApuesta(idU,even,pron,imp,cuo,result);


    }



}
