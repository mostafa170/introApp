package com.alamat.alamattrainingapp.fireBase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ActivityInsertFireBaseBinding;
import com.alamat.alamattrainingapp.fireBase.FireBaseModel.User;
import com.alamat.alamattrainingapp.fireBase.FireBaseUtils.UsersDao;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.jetbrains.annotations.NotNull;

public class InsertFireBaseActivity extends AppCompatActivity {

    ActivityInsertFireBaseBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_insert_fire_base);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListen();
            }
        });


    }
    public void onClickListen(){
        user = new User();
        user.setUserName(binding.edUserName.getText().toString());
        user.seteMail(binding.edUserEmail.getText().toString());
        user.setPassword(binding.edUserPassword.getText().toString());
        user.setPhone(Integer.parseInt(binding.edUserPhone.getText().toString()));
        UsersDao.addUser(user,onSuccessListener,onFailureListener);

    }

    OnSuccessListener onSuccessListener = new OnSuccessListener() {
        @Override
        public void onSuccess(Object o) {
            Toast.makeText(InsertFireBaseActivity.this, "user Added Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    OnFailureListener onFailureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull @NotNull Exception e) {
            Toast.makeText(InsertFireBaseActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}