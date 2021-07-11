package com.alamat.alamattrainingapp.sendAndGetData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ActivityFirstBinding;

public class FirstActivity extends AppCompatActivity {
    ActivityFirstBinding binding;
    int age;
    String data = "test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first);

        binding.btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = Integer.parseInt(binding.edEdText.getText().toString());
                Intent intent = new Intent(FirstActivity.this , SecActivity.class );
                intent.putExtra("data",data);
                intent.putExtra("age",age);
                startActivity(intent);
            }
        });
    }
}