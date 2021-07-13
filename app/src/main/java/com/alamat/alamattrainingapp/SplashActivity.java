package com.alamat.alamattrainingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alamat.alamattrainingapp.NewsAPI.NewsSourcesActivity;
import com.alamat.alamattrainingapp.bottomNav.BottomNavActivity;
import com.alamat.alamattrainingapp.databinding.ActivitySplashBinding;
import com.alamat.alamattrainingapp.fireBase.ShowFireBaseActivity;
import com.alamat.alamattrainingapp.layoutAndLifeCycle.MainActivity;
import com.alamat.alamattrainingapp.mapLocation.MapLocationActivity;
import com.alamat.alamattrainingapp.radio.RadioActivity;
import com.alamat.alamattrainingapp.recyclerView.RecyclerViewListActivity;
import com.alamat.alamattrainingapp.roomDatabase.GetRoomDatabaseActivity;
import com.alamat.alamattrainingapp.sendAndGetData.FirstActivity;
import com.alamat.alamattrainingapp.tabLayout.TabLayoutActivity;

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

        binding.btnMapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , MapLocationActivity.class );
                startActivity(intent);
            }
        });

        binding.btnNewsAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , NewsSourcesActivity.class );
                startActivity(intent);
            }
        });

        binding.btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , RadioActivity.class );
                startActivity(intent);
            }
        });

        binding.btnFireBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , ShowFireBaseActivity.class );
                startActivity(intent);
            }
        });

        binding.btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this , GetRoomDatabaseActivity.class );
                startActivity(intent);
            }
        });

    }
}