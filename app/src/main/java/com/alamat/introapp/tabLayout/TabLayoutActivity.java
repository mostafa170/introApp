package com.alamat.introapp.tabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.alamat.introapp.R;
import com.alamat.introapp.databinding.ActivityTabLayoutBinding;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class TabLayoutActivity extends AppCompatActivity {

    ActivityTabLayoutBinding binding;
    FragmentPagerItemAdapter fragmentPagerItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tab_layout);
        initTablayout();

    }


    public void initTablayout(){
        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.label_login, LoginFragment.class)
                .add(R.string.label_reg, RegFragment.class)
                .create());


        binding.viewpager.setAdapter(fragmentPagerItemAdapter);
        binding.tabLayout.setViewPager(binding.viewpager);

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.viewpager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}