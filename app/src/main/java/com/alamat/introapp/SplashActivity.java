package com.alamat.introapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alamat.introapp.bottomNav.BottomNavActivity;
import com.alamat.introapp.databinding.ActivitySplashBinding;
import com.alamat.introapp.layoutAndLifeCycle.MainActivity;
import com.alamat.introapp.recyclerView.RecyclerViewListActivity;
import com.alamat.introapp.sendAndGetData.FirstActivity;
import com.alamat.introapp.tabLayout.TabLayoutActivity;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash);

        binding.btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , MainActivity.class );
                startActivity(intent);
            }
        });


        binding.btnSendDataAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , FirstActivity.class );

                startActivity(intent);
            }
        });


        binding.btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , BottomNavActivity.class );
                startActivity(intent);
            }
        });

        binding.btnTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , TabLayoutActivity.class );
                startActivity(intent);
            }
        });

        binding.btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , RecyclerViewListActivity.class );
                startActivity(intent);
            }
        });

    }
}