package com.alamat.introapp.layoutAndLifeCycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alamat.introapp.R;
import com.alamat.introapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding  binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Log.e("MainActivity", "onCreate: " );

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume: " );

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "onPause: " );

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop: " );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "onDestroy: " );

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity", "onRestart: " );
    }
}