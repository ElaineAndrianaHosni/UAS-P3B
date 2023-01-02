package com.example.uas_p3b;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.presenter.MainPresenter;
import com.example.uas_p3b.databinding.ActivityMainBinding;
import com.example.uas_p3b.databinding.HomeBinding;

public class HomeActivity extends AppCompatActivity  {
    private HomeBinding binding;
    private MainPresenter mp;
    private String token;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  HomeBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        //ambil token dari login
        this.token = intent.getStringExtra("token");
        setContentView(binding.getRoot());


    }
}
