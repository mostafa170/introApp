package com.alamat.alamattrainingapp.roomDatabase.dataBaseUtils;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alamat.alamattrainingapp.roomDatabase.dataBaseUtils.roomModel.TodoModel;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insertTodo(TodoModel todoModel);

    @Query("select * from todomodel;")
    List<TodoModel> getAllTodo();
}
