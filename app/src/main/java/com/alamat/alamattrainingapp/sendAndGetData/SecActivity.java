package com.alamat.alamattrainingapp.sendAndGetData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ActivitySecBinding;

public class SecActivity extends AppCompatActivity {

    String getData;
    int age;
    ActivitySecBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sec);


        getData = getIntent().getExtras().getString("data");
        age = getIntent().getExtras().getInt("age");

        binding.tvDataGet.setText(getData);
        binding.tvDataGetModel.setText(String.valueOf(age));



    }
}