package com.example.uas_p3b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contract.MainUI;
import com.example.presenter.MainPresenter;
import com.example.uas_p3b.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainUI, View.OnClickListener {
    private ActivityMainBinding binding;
    private MainPresenter mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityMainBinding.inflate(getLayoutInflater());
        binding.btnLogin.setOnClickListener(this);
        mp = new MainPresenter(this);
        setContentView(binding.getRoot());
    }

    @Override
    public void onClick(View view) {
        if(view==binding.btnLogin){
            mp.Login(binding.etEmail.getText().toString(),binding.etPassword.getText().toString(),binding.etRole.getText().toString());
        }
    }

    @Override
    public void loginBerhasil(String token) {
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginGagal(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}