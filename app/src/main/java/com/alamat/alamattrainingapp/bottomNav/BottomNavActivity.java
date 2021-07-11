package com.alamat.alamattrainingapp.bottomNav;

import android.os.Bundle;
import android.view.MenuItem;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.bottomNav.ui.dashboard.DashboardFragment;
import com.alamat.alamattrainingapp.bottomNav.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.alamat.alamattrainingapp.databinding.ActivityBottomNavBinding;

import org.jetbrains.annotations.NotNull;

public class BottomNavActivity extends AppCompatActivity {

    private ActivityBottomNavBinding binding;

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            Fragment fragment = null;
            if (getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_bottom_nav)==null) {
                fragment = new HomeFragment();
            }
            switch (item.getItemId()){
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;

                case R.id.navigation_dashboard :
                    fragment = new DashboardFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().
                    replace(R.id.nav_host_fragment_activity_bottom_nav,fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBottomNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().
                add(R.id.nav_host_fragment_activity_bottom_nav,new HomeFragment()).commit();
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_nav);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}