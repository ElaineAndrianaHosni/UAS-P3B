package com.example.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentActivity;

import com.example.contract.FRSUI;
import com.example.uas_p3b.databinding.ListFrsBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class FRSAdapter extends BaseAdapter {
    private ListFrsBinding binding;
    private FRSUI ui;
    private ArrayList<Integer> tahundansemester;
    private Activity fragmentActivity;
    private HashMap<Integer,String> map;
    private int active_year;

    public FRSAdapter(FRSUI ui){
        this.ui = ui;
        this.tahundansemester = new ArrayList<>();
        this.fragmentActivity = ui.getAct();
        this.active_year = active_year;
    }

    @Override
    public int getCount() {
        return tahundansemester.size();
    }

    @Override
    public Object getItem(int position) {
        return tahundansemester.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        binding = ListFrsBinding.inflate(fragmentActivity.getLayoutInflater());
        View view = binding.getRoot();
        map = new HashMap<>();
        map.put(1,"Semester Ganjil");
        map.put(2,"Semester Genap");
        map.put(3,"Semester Pendek");
        int tahun = tahundansemester.get(position)/10;
        String semester = map.get(tahundansemester.get(position)%10);
        binding.smt.setText(semester+" "+tahun+"/"+(tahun+1));
        if(tahundansemester.get(position)==active_year){
            binding.smt.setTypeface(binding.smt.getTypeface(), Typeface.BOLD_ITALIC);
            binding.containerSemester.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle result = new Bundle();
                    result.putString("page","tambahMatkul");
                    result.putInt("tahundansem",tahundansemester.get(position));
                    result.putString("heading",semester+" "+tahun+"/"+(tahun+1));
                    fragmentActivity.getSupportFragmentManager().setFragmentResult("changePage",result);
                }
            });
        }
        else{
            binding.containerSemester.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle result = new Bundle();
                    result.putString("page","detailSemester");
                    result.putInt("tahundansem",tahundansemester.get(position));
                    result.putString("heading",semester+" "+tahun+"/"+(tahun+1));
                    fragmentActivity.getSupportFragmentManager().setFragmentResult("changePage",result);
                }
            });
        }
//        if(position==tahundansemester.size()-1) {
//            binding.containerSemester.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle result = new Bundle();
//                    result.putString("page","tambahMatkul");
//                    result.putInt("sem",position+1);
//                    result.putString("heading",binding.smt.getText().toString());
//                    fragmentActivity.getSupportFragmentManager().setFragmentResult("changePage",result);
//                }
//            });
//        }
//        else{
//            binding.containerSemester.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle result = new Bundle();
//                    result.putString("page","detailSemester");
//                    result.putInt("sem",position+1);
//                    result.putString("heading",binding.smt.getText().toString());
//                    fragmentActivity.getSupportFragmentManager().setFragmentResult("changePage",result);
//                }
//            });
//        }

        return view;
    }
}
