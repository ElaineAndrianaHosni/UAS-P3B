package com.example.adapter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.model.Pertemuan;
import com.example.uas_p3b.DetailPertemuan;
import com.example.uas_p3b.databinding.ItemListPertemuanBinding;

import java.util.ArrayList;

public class AdapterPertemuan extends BaseAdapter {
    private String token;
    ArrayList<Pertemuan> pertemuan;
    ItemListPertemuanBinding binding;
    FragmentActivity fragmentActivity;
    public AdapterPertemuan(FragmentActivity fragmentActivity,String token){
        this.pertemuan = new ArrayList<>();
        this.fragmentActivity = fragmentActivity;
        this.token=token;
        this.notifyDataSetChanged();
    }
    public void add(String id,String title,String started_datetime,String end_datetime){
        Pertemuan pertemuan = new Pertemuan(id,title,started_datetime,end_datetime);
        this.pertemuan.add(pertemuan);
        this.notifyDataSetChanged();

    }
    public void clear(){
        this.pertemuan.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return pertemuan.size();
    }

    @Override
    public Object getItem(int position) {
        return pertemuan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        binding = ItemListPertemuanBinding.inflate(fragmentActivity.getLayoutInflater());
        binding.title.setText(pertemuan.get(position).getTitle());
        binding.startTime.setText("Start: "+pertemuan.get(position).getStarted_datetime());
        binding.endTime.setText("End: "+pertemuan.get(position).getEnd_datetime());
        binding.containerPertemuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailPertemuan detailPertemuan = new DetailPertemuan();
                Bundle bundle = new Bundle();
                bundle.putString("id",pertemuan.get(position).getId());
                detailPertemuan.setArguments(bundle);
                FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
                detailPertemuan.show(ft,"aaa");
            }
        });
        View view = binding.getRoot();
        return view;
    }
}
