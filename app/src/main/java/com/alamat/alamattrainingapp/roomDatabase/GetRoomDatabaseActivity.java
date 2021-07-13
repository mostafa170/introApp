package com.alamat.alamattrainingapp.roomDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ActivityGetRoomDatabaseBinding;
import com.alamat.alamattrainingapp.fireBase.InsertFireBaseActivity;
import com.alamat.alamattrainingapp.fireBase.ShowFireBaseActivity;
import com.alamat.alamattrainingapp.roomDatabase.dataBaseUtils.RoomDataBase;
import com.alamat.alamattrainingapp.roomDatabase.dataBaseUtils.roomModel.TodoModel;

import java.util.List;

public class GetRoomDatabaseActivity extends AppCompatActivity {

    ActivityGetRoomDatabaseBinding binding;
    RecyclerView.LayoutManager layoutManager;
    TodoRoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_get_room_database);
        adapter = new TodoRoomAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewToDo.setLayoutManager(layoutManager);
        binding.recyclerViewToDo.setAdapter(adapter);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetRoomDatabaseActivity.this , SaveRoomDatabaseActivity.class );
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<TodoModel> allTodo = RoomDataBase.getInstance(this).todoDao().getAllTodo();
        adapter = new TodoRoomAdapter(allTodo);
        binding.recyclerViewToDo.setAdapter(adapter);
    }
}