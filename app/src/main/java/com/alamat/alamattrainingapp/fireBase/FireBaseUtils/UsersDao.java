package com.alamat.alamattrainingapp.fireBase.FireBaseUtils;

import com.alamat.alamattrainingapp.fireBase.FireBaseModel.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class UsersDao {

    public static void addUser(User user,
                               OnSuccessListener onSuccessListener,
                               OnFailureListener onFailureListener){

        DatabaseReference userNode = FireBaseDataBase.getUserBranch().push();
        user.setId(userNode.getKey());
        userNode.setValue(user).addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);

    }
}
