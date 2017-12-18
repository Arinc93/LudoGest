package com.example.dominpc.ludogest.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
if(even.equals("")||pron.equals("")||imp.equals("")||cuo.equals("")){

    Toast t = Toast.makeText( this,"Introduce todos los datos", Toast.LENGTH_LONG );
    t.setGravity( Gravity.BOTTOM| Gravity.CENTER, 0, 0 );
    t.show();





}else{
    double impor= Double.parseDouble(imp);
    double cuot= Double.parseDouble(cuo);

    DBmgr.add(even,pron,impor,cuot,result);

    Toast t = Toast.makeText( this,"Apuesta insertada", Toast.LENGTH_LONG );
    t.setGravity( Gravity.BOTTOM| Gravity.CENTER, 0, 0 );
    t.show();

    Intent intent = new Intent(ApuestaAdd.this, apuestasShowAll.class);

    ApuestaAdd.this.startActivity(intent);

            }

    }

    public void onPause()
    {
        super.onPause();

        this.DBmgr.close();

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

                Intent intent = new Intent(ApuestaAdd.this, apuestasShowAll.class);

                ApuestaAdd.this.startActivity(intent);
                toret = true;
                break;


            case R.id.consultarEstadisticasMenu:

                Intent intent2 = new Intent(ApuestaAdd.this, showBeneficios.class);

                ApuestaAdd.this.startActivity(intent2);
                toret = true;
                break;

            case R.id.apuAddMenu:

                Intent intent3 = new Intent(ApuestaAdd.this, ApuestaAdd.class);

                ApuestaAdd.this.startActivity(intent3);
                toret = true;
                break;



        }

        return toret;

    }

}


