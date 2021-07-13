package com.alamat.alamattrainingapp.roomDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ActivitySaveRoomDatabaseBinding;
import com.alamat.alamattrainingapp.roomDatabase.dataBaseUtils.RoomDataBase;
import com.alamat.alamattrainingapp.roomDatabase.dataBaseUtils.roomModel.TodoModel;

public class SaveRoomDatabaseActivity extends AppCompatActivity {

    ActivitySaveRoomDatabaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_save_room_database);

        binding.btnSaveTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTodo();
            }
        });
    }

    public void insertTodo(){
        TodoModel todoModel = new TodoModel(binding.edTodoTile.getText().toString(),
                binding.edTodoContent.getText().toString());
        RoomDataBase.getInstance(this).todoDao().insertTodo(todoModel);
        finish();
    }
}