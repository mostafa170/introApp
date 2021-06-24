package com.alamat.introapp.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alamat.introapp.R;
import com.alamat.introapp.SplashActivity;
import com.alamat.introapp.databinding.ActivityRecyclerViewListBinding;
import com.alamat.introapp.layoutAndLifeCycle.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewListActivity extends AppCompatActivity {

    ActivityRecyclerViewListBinding binding;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<ContactModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_view_list);
        createContactList();
        adapter = new RecyclerViewAdapter(modelList,RecyclerViewAdapter.LIST_ITEM);
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(layoutManager);
        changeLayoutManger();
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, ContactModel contactModel) {
                Toast.makeText(RecyclerViewListActivity.this, contactModel.name, Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onItemClick: "+contactModel.name );

            }
        });
    }

    public void createContactList(){
        modelList = new ArrayList<>();
        for (int i= 0;i<50;i++){
            modelList.add(new ContactModel("contact "+i,R.drawable.ic_woman_icon_plain_in_circle,"Available" ));
        }
    }


    public void changeLayoutManger(){
        binding.ivVerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutManager = new LinearLayoutManager(RecyclerViewListActivity.this);
                adapter = new RecyclerViewAdapter(modelList,RecyclerViewAdapter.LIST_ITEM);
                binding.recyclerView.setAdapter(adapter);
                binding.recyclerView.setLayoutManager(layoutManager);
                adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int pos, ContactModel contactModel) {
                        Toast.makeText(RecyclerViewListActivity.this, contactModel.name, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        binding.ivHorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutManager = new LinearLayoutManager(RecyclerViewListActivity.this ,RecyclerView.HORIZONTAL , false);
                adapter = new RecyclerViewAdapter(modelList,RecyclerViewAdapter.LIST_ITEM);
                binding.recyclerView.setAdapter(adapter);
                binding.recyclerView.setLayoutManager(layoutManager);
                adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int pos, ContactModel contactModel) {
                        Toast.makeText(RecyclerViewListActivity.this, contactModel.name, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        binding.ivGridList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutManager = new GridLayoutManager(RecyclerViewListActivity.this ,2);
                adapter = new RecyclerViewAdapter(modelList , RecyclerViewAdapter.GIRD_ITEM);
                binding.recyclerView.setAdapter(adapter);
                binding.recyclerView.setLayoutManager(layoutManager);
                adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int pos, ContactModel contactModel) {
                        Toast.makeText(RecyclerViewListActivity.this, contactModel.name, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}