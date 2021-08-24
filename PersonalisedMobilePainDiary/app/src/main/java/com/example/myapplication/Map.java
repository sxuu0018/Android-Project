package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Map extends AppCompatActivity {
    private MapView mapView;
    private Button confirm;
    private EditText address;

    private double la = 0;
    private double lo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = getString(R.string.mapbox_access_token);
        Mapbox.getInstance(this, token);
        setContentView(R.layout.activity_map);

        confirm = findViewById(R.id.confirm);
        address = findViewById(R.id.address);
        //final LatLng latLng= new LatLng(-37.876823, 145.045837);
        final LatLng latLng= new LatLng(-37.8, 144.96);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        CameraPosition position = new CameraPosition.Builder()
                                .target(latLng)
                                .zoom(10)
                                .build();
                        mapboxMap.setCameraPosition(position);

                    }
                });
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String locationName = "133 clayton road";
                String locationName = address.getText().toString();
                Geocoder geocoder = new Geocoder(Map.this, Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocationName(locationName,3);
                    if (addressList.size()>0){
                        Address address = addressList.get(0);
                        la = address.getLatitude();
                        lo = address.getLongitude();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final LatLng latLng= new LatLng(la,lo);
                //final LatLng latLng= new LatLng();
                mapView = (MapView) findViewById(R.id.mapView);
                mapView.onCreate(savedInstanceState);
                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                        //MarkerViewManager markerViewManager = new MarkerViewManager(mapView, mapboxMap);
                        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                CameraPosition position = new CameraPosition.Builder()
                                        .target(latLng)
                                        .zoom(10)
                                        .build();
                                mapboxMap.setCameraPosition(position);

                                mapboxMap.addMarker(new MarkerOptions().setPosition(latLng));
                                //MarkerView markerView = new MarkerView(new LatLng(doubleLatitude,doubleLongitude), mapView);

                                //markerViewManager.addMarker(markerView);
                            }
                        });
//                MarkerViewManager markerViewManager = new MarkerViewManager(mapView, mapboxMap);
//                MarkerView markerView = new MarkerView(new LatLng(doubleLatitude,doubleLongitude), mapView);
//
//                markerViewManager.addMarker(markerView);
                    }
                });
            }
        });


//lat and long are hardcoded here but could be provided at run time

    }
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}