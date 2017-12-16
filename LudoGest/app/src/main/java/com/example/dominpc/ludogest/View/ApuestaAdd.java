package com.example.dominpc.ludogest.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.dominpc.ludogest.Core.Apuesta;
import com.example.dominpc.ludogest.Core.DBManager;
import com.example.dominpc.ludogest.R;

import java.util.List;

public class ApuestaAdd extends AppCompatActivity {
    private DBManager DBmgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuesta_add);

        final Button btAdd = this.findViewById( R.id.ApuAddBt );
        this.DBmgr = new DBManager( this.getApplicationContext() );

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApuestaAdd.this.insertaPrueba();
            }
        });

      //






    }



    public void insertaPrueba(){

        EditText EvenTXT = (EditText) this.findViewById(R.id.EventoTXT);
        EditText PronoTXT = (EditText) this.findViewById(R.id.PronosticoTXT);
        EditText ImporTXT = (EditText) this.findViewById(R.id.ImporteTXT);
        EditText CuoTXT = (EditText) this.findViewById(R.id.CuotaTXT);

        String even= EvenTXT.getText().toString();
        String pron= PronoTXT.getText().toString();
        String imp= ImporTXT.getText().toString();
        String cuo= CuoTXT.getText().toString();
        int result = 0;


        double impor= Double.parseDouble(imp);
        double cuot= Double.parseDouble(cuo);



        DBmgr.add(even,pron,impor,cuot,result);


    }





}


