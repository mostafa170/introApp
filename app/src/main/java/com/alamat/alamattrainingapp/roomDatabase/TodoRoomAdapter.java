package com.alamat.alamattrainingapp.roomDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ItemTodoRoomBinding;
import com.alamat.alamattrainingapp.roomDatabase.dataBaseUtils.roomModel.TodoModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TodoRoomAdapter extends RecyclerView.Adapter<TodoRoomAdapter.ViewHolder> {

    List<TodoModel> todoModels;

    public TodoRoomAdapter(List<TodoModel> todoModels) {
        this.todoModels = todoModels;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemTodoRoomBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_todo_room,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        TodoModel todoModel = todoModels.get(position);
        holder.itemTodoRoomBinding.tvTodoTitle.setText(todoModel.getTodoTitle());
        holder.itemTodoRoomBinding.tvTodoContent.setText(todoModel.getTodoContect());
    }

    @Override
    public int getItemCount() {
        if (todoModels==null){
            return 0;

        }else {
            return todoModels.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemTodoRoomBinding itemTodoRoomBinding;

        public ViewHolder(@NonNull @NotNull ItemTodoRoomBinding itemView) {
            super(itemView.getRoot());
            this.itemTodoRoomBinding = itemView;
        }
    }
}
