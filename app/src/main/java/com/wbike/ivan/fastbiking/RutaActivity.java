package com.wbike.ivan.fastbiking;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
/*import android.support.v7.app.ActionBarActivity;*/
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Actividad designada al control de una nueva ruta (Seguimiento, duracion, distancia...)
 */
public class RutaActivity extends FragmentActivity {
    private GoogleMap mMap;
    private TextView distanciaTrack, velocidadTrack;
    private Chronometer duracionTrack;
    private Button startTrack, stopTrack;

    private LocationManager locationManager;
    private LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);
        setUpMapIfNeeded();

        //Init TextViews
        distanciaTrack= (TextView) findViewById(R.id.distanciaRuta);
        velocidadTrack= (TextView) findViewById(R.id.velocidadRuta);

        //Init Chronometer
        duracionTrack= (Chronometer) findViewById(R.id.chronometer);

        //Iniciar ruta
        //Obtenemos una referencia al LocationManager
        locationManager =
                (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        startTrack= (Button) findViewById(R.id.startTrack);
        startTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duracionTrack.start();
                startTrack.setVisibility(View.INVISIBLE);
                stopTrack.setVisibility(View.VISIBLE);
                //startTrack.setText("Detener");
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }else{
                    actualizarPosicion();
                }

            }
        });
        stopTrack= (Button) findViewById(R.id.stopTrack);
        stopTrack.setVisibility(View.INVISIBLE);
        stopTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duracionTrack.stop();
                startTrack.setVisibility(View.VISIBLE);
                stopTrack.setVisibility(View.INVISIBLE);
                if(locationManager!=null){
                    locationManager.removeUpdates(locationListener);
                }
            }
        });

        //Mapa y localización
        mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapTrack)).getMap();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
                Projection proj = mMap.getProjection();
                Point coord = proj.toScreenLocation(point);

                Toast.makeText(
                        RutaActivity.this,
                        "Click\n" +
                                "Lat: " + point.latitude + "\n" +
                                "Lng: " + point.longitude + "\n" +
                                "X: " + coord.x + " - Y: " + coord.y,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapTrack))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
               setUpMap();
            }
        }
    }
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
    private void mostrarMarcador(double lat, double lng)
    {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title("Pais: España"));
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
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

    private void actualizarPosicion()
    {
        //Obtenemos la última posición conocida
        Location location =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Mostramos la última posición conocida
        muestraPosicion(location);

        //Nos registramos para recibir actualizaciones de la posición
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                muestraPosicion(location);
            }
            public void onProviderDisabled(String provider){
                //lblEstado.setText("Provider OFF");
            }
            public void onProviderEnabled(String provider){
                //lblEstado.setText("Provider ON");
            }
            public void onStatusChanged(String provider, int status, Bundle extras){
                Log.i("LocAndroid", "Provider Status: " + status);
                //lblEstado.setText("Provider Status: " + status);
            }
        };

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 15000, 0, locationListener);
    }

    private void muestraPosicion(Location loc) {
        if(loc != null)
        {
            /*lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
            lblPrecision.setText("Precision: " + String.valueOf(loc.getAccuracy()));*/

                    //loc.getLatitude(), loc.getLongitude()

            /*PolylineOptions lineas = new PolylineOptions()
                    .add(new LatLng(40.3857051, -3.6687032))
                    .add(new LatLng(40.391813, -3.6644194));

            lineas.width(8);
            lineas.color(Color.RED);
            mMap.addPolyline(lineas);*/

            LatLng posActual = new LatLng(loc.getLatitude(), loc.getLongitude());
            CameraPosition camActivePos= new CameraPosition.Builder().target(posActual).zoom(18).bearing(0).build();
            CameraUpdate camActive= CameraUpdateFactory.newCameraPosition(camActivePos);
            mMap.animateCamera(camActive);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(loc.getLatitude(), loc.getLongitude()))
                    .title("prueba"));
            velocidadTrack.setText(String.valueOf(loc.getSpeed()));
            Log.i("LocAndroid", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
        }
        else
        {
            /*lblLatitud.setText("Latitud: (sin_datos)");
            lblLongitud.setText("Longitud: (sin_datos)");
            lblPrecision.setText("Precision: (sin_datos)");*/
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ruta, menu);
        return true;
    }
}
