package com.example.uas_p3b;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uas_p3b.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private HomeFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater,container,false);
        binding.pengumuman.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==binding.pengumuman){
            changePage("pengumuman");
        }
    }
    private void changePage(String page){
        Bundle result = new Bundle();
        result.putString("page",page);
        getParentFragmentManager().setFragmentResult("changePage",result);
    }
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
}
