package com.alamat.alamattrainingapp.roomDatabase.dataBaseUtils.roomModel;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class TodoModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    String todoTitle;

    @ColumnInfo
    String todoContect;

    public TodoModel() {
    }

    @Ignore
    public TodoModel(String todoTitle, String todoContect) {
        this.todoTitle = todoTitle;
        this.todoContect = todoContect;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoContect() {
        return todoContect;
    }

    public void setTodoContect(String todoContect) {
        this.todoContect = todoContect;
    }
}
