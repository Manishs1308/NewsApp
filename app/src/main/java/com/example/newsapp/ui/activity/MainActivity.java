package com.example.newsapp.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.example.newsapp.model.MResponse;
import com.example.newsapp.ui.adapter.ViewPagerAdapter;
import com.example.newsapp.ui.utils.Utility;
import com.example.newsapp.ui.viewmodel.MainActivityViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        initializeViewModel();
        if (Utility.isNetworkAvailable(this))
            getSources();
        else Toast.makeText(this,"No Internet Connection", Toast.LENGTH_SHORT).show();
    }

    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }
    private void getSources() {
        viewModel.sourceLiveData().observe(this, this:: setUpViewPager);
    }

    private void setUpViewPager(MResponse response) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, response.getSourceList());
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewPagerAdapter.createFragment(position);
                viewPagerAdapter.notifyDataSetChanged();
            }
        });
        new TabLayoutMediator(binding.tabLayout, binding.viewpager, (tab, position) -> tab.setText(response.getSourceList().get(position).getName())).attach();
    }
}