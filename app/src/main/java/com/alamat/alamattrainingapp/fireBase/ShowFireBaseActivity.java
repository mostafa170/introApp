package com.alamat.alamattrainingapp.fireBase;

import android.content.Intent;
import android.os.Bundle;

import com.alamat.alamattrainingapp.SplashActivity;
import com.alamat.alamattrainingapp.databinding.ActivityShowFireBaseBinding;
import com.alamat.alamattrainingapp.fireBase.FireBaseModel.User;
import com.alamat.alamattrainingapp.fireBase.FireBaseUtils.FireBaseDataBase;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.alamat.alamattrainingapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShowFireBaseActivity extends AppCompatActivity {

    ActivityShowFireBaseBinding binding;
    UserFireBaseAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    User user;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_fire_base);
        adapter = new UserFireBaseAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        binding.include.recyclerViewShowData.setAdapter(adapter);
        binding.include.recyclerViewShowData.setLayoutManager(layoutManager);
        FireBaseDataBase.getUserBranch().addValueEventListener(valueEventListener);



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowFireBaseActivity.this , InsertFireBaseActivity.class );
                startActivity(intent);
            }
        });

    }


    ValueEventListener valueEventListener =  new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            users = new ArrayList<>();
            for (DataSnapshot snapshot1:snapshot.getChildren()){
                user = snapshot1.getValue(User.class);
                users.add(user);
                adapter = new UserFireBaseAdapter(users);
                binding.include.recyclerViewShowData.setAdapter(adapter);

            }

        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {
            Toast.makeText(ShowFireBaseActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };


}