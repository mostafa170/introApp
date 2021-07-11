package com.alamat.alamattrainingapp.fireBase;

import android.os.Bundle;

import com.alamat.alamattrainingapp.databinding.ActivityShowFireBaseBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.databinding.DataBindingUtil;


import com.alamat.alamattrainingapp.R;

public class ShowFireBaseActivity extends AppCompatActivity {

    ActivityShowFireBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_fire_base);



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


}