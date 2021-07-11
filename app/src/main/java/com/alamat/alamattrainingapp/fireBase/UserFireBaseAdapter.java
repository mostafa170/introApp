package com.alamat.alamattrainingapp.fireBase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ItemUserFirebaseBinding;
import com.alamat.alamattrainingapp.fireBase.FireBaseModel.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserFireBaseAdapter extends RecyclerView.Adapter<UserFireBaseAdapter.ViewHolder> {

    List<User> userList;

    public UserFireBaseAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemUserFirebaseBinding itemUserFirebaseBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_user_firebase,parent,false);
        return new ViewHolder(itemUserFirebaseBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        User user =  userList.get(position);
        holder.binding.tvUserNameData.setText(user.getUserName());
        holder.binding.tvUserEmailData.setText(user.geteMail());
        holder.binding.tvUserPasswordData.setText(user.getPassword());
        holder.binding.tvUserPhoneData.setText(String.valueOf(user.getPhone()));

    }

    @Override
    public int getItemCount() {
        if (userList==null){
            return 0;
        }else {
            return userList.size();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{


        ItemUserFirebaseBinding binding;

        public ViewHolder(@NonNull @NotNull ItemUserFirebaseBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
