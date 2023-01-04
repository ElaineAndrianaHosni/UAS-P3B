package com.example.uas_p3b;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.contract.HomeUI;
import com.example.presenter.HomePresenter;
import com.example.presenter.MainPresenter;
import com.example.uas_p3b.databinding.ActivityMainBinding;
import com.example.uas_p3b.databinding.HomeBinding;
import com.example.uas_p3b.databinding.HomeFragmentBinding;

public class HomeActivity extends AppCompatActivity implements HomeUI {
    private HomeBinding binding;
    private HomePresenter hp;
    private String token;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  HomeBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        //ambil token dari login
        this.token = intent.getStringExtra("token");

        setContentView(binding.getRoot());
        hp = new HomePresenter(this);

        this.getSupportFragmentManager().setFragmentResultListener(
                "changePage", this,new FragmentResultListener(){
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result){
                        String page = result.getString("page");
                        int pos=result.getInt("pos");

//                        if(page=="Dokter Form"||page=="Pertemuan Form"){
//                            changePage(page,pos);
//                        }else{
                            changePage(page);
//                        }
                        binding.drawerLayout.closeDrawers();
                    }


                }
        );
        this.getSupportFragmentManager().setFragmentResultListener(
                "closeApp", this,new FragmentResultListener(){
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result){
                        closeApp();
                    }


                }
        );
    }

    @Override
    public void changePage(String page) {
        FragmentTransaction ft=this.getSupportFragmentManager().beginTransaction();
        switch(page){
            case "Home":
                ft.replace(binding.fragmentContainer.getId(), HomeFragment.newInstance());
                ft.addToBackStack(null);
                break;
        }

        ft.commit();
    }

    @Override
    public void closeApp() {
        this.moveTaskToBack(true);
        this.finish();
    }

    @Override
    public String getToken() {
        return null;
    }
}
