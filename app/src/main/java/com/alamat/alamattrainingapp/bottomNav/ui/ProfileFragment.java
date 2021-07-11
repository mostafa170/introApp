package com.alamat.alamattrainingapp.bottomNav.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;

   View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false);
        view = binding.getRoot();
        return view;
    }
}