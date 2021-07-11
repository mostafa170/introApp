package com.alamat.alamattrainingapp.fireBase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ActivityInsertFireBaseBinding;

public class InsertFireBaseActivity extends AppCompatActivity {

    ActivityInsertFireBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_insert_fire_base);

    }
}