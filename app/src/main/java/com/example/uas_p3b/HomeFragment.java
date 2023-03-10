package com.example.uas_p3b;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.contract.HomeFragmentUI;
import com.example.contract.HomeUI;
import com.example.presenter.HomeFragmentPresenter;
import com.example.uas_p3b.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeFragmentUI {
    private HomeFragmentBinding binding;
    private HomeUI homeUI;
    private HomeFragmentPresenter presenter;

    public HomeFragment(HomeUI homeUI) {
        this.homeUI = homeUI;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater,container,false);
        binding.pengumuman.setOnClickListener(this);
        binding.keluar.setOnClickListener(this);
        binding.frs.setOnClickListener(this);
        binding.pertemuan.setOnClickListener(this);
        presenter= new HomeFragmentPresenter(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==binding.pengumuman){
            changePage("pengumuman");
        }else if(view==binding.keluar){
            this.getParentFragmentManager().setFragmentResult("closeApp",new Bundle());
        }else if (view==binding.frs){
            changePage("FRS");
        }else if(view == binding.pertemuan){
            changePage("pertemuan");
        }
    }
    private void changePage(String page){
        Bundle result = new Bundle();
        result.putString("page",page);
        getParentFragmentManager().setFragmentResult("changePage",result);
    }
    public static HomeFragment newInstance(HomeUI homeUI){
        return new HomeFragment(homeUI);
    }

    @Override
    public String getRole() {
        return homeUI.getRole();
    }

    @Override
    public void enabledBtnFRS(boolean enabled) {
        if(enabled){
            binding.frs.setVisibility(View.VISIBLE);

        }else{
            binding.frs.setVisibility(View.INVISIBLE);
        }
    }

}