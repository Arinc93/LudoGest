package com.example.dominpc.ludogest.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.dominpc.ludogest.Core.DBManager;
import com.example.dominpc.ludogest.R;

public class apuestasShowAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuestas_show_all);

        final ListView listApuestas = this.findViewById( R.id.lvApuestas );

        this.registerForContextMenu( listApuestas );
        DBManager DBMgr = new DBManager( this.getApplicationContext() );


    }
    protected void onResume() {
        super.onResume();
        final ListView listApuestas = this.findViewById( R.id.lvApuestas );


        SimpleCursorAdapter mainCursorAdapter = new SimpleCursorAdapter( this,
                R.layout.lvlistaapuestas,
                null,
                new String[]{ DBManager.APUESTA_ID ,DBManager.APUESTA_EVENTO },
                new int[] { R.id.lvidApuesta, R.id.lveventoApuesta } );
        listApuestas.setAdapter( mainCursorAdapter );




    }

    private void updateContacts()
    {
        this.mainCursorAdapter.changeCursor( this.dbManager.getAllContacts() );
    }
}
