package com.alamat.introapp.tabLayout;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamat.introapp.R;
import com.alamat.introapp.databinding.FragmentRegBinding;

public class RegFragment extends Fragment {

    FragmentRegBinding binding;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_reg,container,false);
        view = binding.getRoot();
        return view;
    }
}