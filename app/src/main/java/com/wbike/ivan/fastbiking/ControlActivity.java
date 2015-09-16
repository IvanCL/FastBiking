package com.wbike.ivan.fastbiking;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Actividad encargada de la inicialización de la principal UI y nexo de unión entre las demás actividades
 */
public class ControlActivity extends ActionBarActivity {
    private ListView lstOpciones;
    private ImageView imgProfile;
    private Button newRuta, mapEye, diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        //Ejemplo básico
        final String[] datos =
                new String[]{"Ruta 1","Ruta 2","Ruta 3","Ruta 4","Ruta 5"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, datos);

        lstOpciones = (ListView)findViewById(R.id.trackList);

        lstOpciones.setAdapter(adaptador);

        imgProfile= (ImageView) findViewById(R.id.imgProfile);
        imgProfile.setImageResource(R.mipmap.ic_cyclist);

        newRuta= (Button) findViewById(R.id.newTrack);
        newRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(ControlActivity.this, RutaActivity.class);
                startActivity(actividad);
            }
        });
        mapEye= (Button) findViewById(R.id.mapView);
        mapEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(ControlActivity.this, MapaActivity.class);
                startActivity(actividad);
                //setContentView(R.layout.activity_mapa);
            }
        });

        diary= (Button) findViewById(R.id.noteView);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad= new Intent(ControlActivity.this, MapaActivity.class);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
