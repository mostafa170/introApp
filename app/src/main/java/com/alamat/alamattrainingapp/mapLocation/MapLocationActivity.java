package com.alamat.alamattrainingapp.mapLocation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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
import android.util.Log;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ActivityMapLocationBinding;
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
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    public Boolean mLocationPermissionsGranted = false;
    FusedLocationProviderClient mFusedLocationProviderClient;
    public Location currentLocation;
    Marker currentMarker;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_map_location);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLocationPermission();
    }

    @SuppressLint("NewApi")
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                Log.e("TAG", "getLocationPermission: " );
                initMap();
                isGpsEnabled();
            } else {
                requestPermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE);

            }
        } else {
            requestPermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionsGranted = false;
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        mLocationPermissionsGranted = false;

                    }
                }
                mLocationPermissionsGranted = true;
                //initialize our map
                initMap();
                isGpsEnabled();
            }
        }


    }

    public void isGpsEnabled(){
        final LocationManager manager =
                (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER)){
            buildAlertMessageNoGps();
        }else {
            startCurrentLocationUpdates();
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            Log.e("TAG", "initMap: ");
            mapFragment.getMapAsync(this);
        }
    }

    private void buildAlertMessageNoGps(){
        final androidx.appcompat.app.AlertDialog.Builder builder =
                new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(@SuppressWarnings("unused")
                                                final DialogInterface dialog,
                                                @SuppressWarnings("unused") final int id) {
                                Intent enableGpsIntent = new Intent(android.provider
                                        .Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                someActivityResultLauncher.launch(enableGpsIntent);
                            }
                        });
        final androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        isGpsEnabled();

                    }
                }
            });

    @SuppressLint("MissingPermission")
    private void startCurrentLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(60000);
        locationRequest.setExpirationDuration(60000);

        mFusedLocationProviderClient.requestLocationUpdates(locationRequest, mLocationCallback,
                Looper.getMainLooper());


    }

    private final LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult.getLastLocation() == null)
                return;

            currentLocation = locationResult.getLastLocation();
            LatLng currentLatLng=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
            Log.e("TAG", "onLocationResult: "+currentLatLng );
            if (mMap!=null){
                Log.e("TAG", "onLocationResult: mMap");
                moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),mMap);
                MarkerOptions markerOptions =
                        new MarkerOptions().position(currentLatLng)
                                .title("current Location");
                if(currentMarker==null)
                    currentMarker=mMap.addMarker(markerOptions);
                else currentMarker.setPosition(currentLatLng);
            }



        }
    };

    public static void moveCamera(LatLng latLng, GoogleMap mMap){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float) 15.0));
        Log.e("TAG", "moveCamera: " );

    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        Log.e("TAG", "onMapReady: " );

        if (mLocationPermissionsGranted) {
            Log.e("TAG", "onMapReady:22 " );

            startCurrentLocationUpdates();
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }

    }
}