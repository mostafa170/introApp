package com.alamat.introapp.mapLocation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;

import com.alamat.introapp.R;
import com.alamat.introapp.databinding.ActivityMapLocationBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

public class MapLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    ActivityMapLocationBinding binding;
    public static final int PERMISSION_REQUEST_CODE = 500;
    public boolean locationPermissionGranded = false;
    FusedLocationProviderClient fusedLocationProviderClient;
    public Location location;
    GoogleMap googleMap;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_map_location);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLocationPermission();
    }

    @SuppressLint("NewApi")
    public void getLocationPermission(){
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                locationPermissionGranded = true;
                //pop us isGPSEnabled
                //initMap
                initMap();
                isGpsEnabled();

            }else {
                requestPermissions(permission,PERMISSION_REQUEST_CODE);

            }

        }else {
            requestPermissions(permission,PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length>0){
                for (int grandResult :grantResults){
                    if (grandResult!=PackageManager.PERMISSION_GRANTED){
                        locationPermissionGranded = false;
                    }
                    locationPermissionGranded = true;
                    //initMap
                    initMap();
                    //isGpsEnabled
                    isGpsEnabled();

                }
            }
        }
    }

    public void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if(mapFragment!=null){
            mapFragment.getMapAsync(this);
        }

    }

    public void isGpsEnabled (){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //AlertGPSMSG
            alertGPSMSG();
        }else {
            //getCurrentLocation
            getCurrentLocation();
        }
    }

    public void alertGPSMSG(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("this App requires GPS to work , do you want enable ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        resultLauncher.launch(intent);

                    }
                });
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK){
                isGpsEnabled();
            }

        }
    });

    @SuppressLint("MissingPermission")
    public void getCurrentLocation(){
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(60000);
        locationRequest.setExpirationDuration(60000);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest , locationCallBack, Looper.getMainLooper());


    }


    private final LocationCallback locationCallBack = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult.getLastLocation()==null){
                return;
            }

            location = locationResult.getLastLocation();
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            moveCamera(latLng,googleMap);
            MarkerOptions markerOptions = new MarkerOptions().position(latLng);
            if (marker == null){
                marker = googleMap.addMarker(markerOptions);

            }else {
                marker.setPosition(latLng);
            }



        }
    };

    public void moveCamera(LatLng latLng ,GoogleMap googleMap){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float) 15.0));
    }

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        getCurrentLocation();
    }
}