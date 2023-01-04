package com.example.uas_p3b;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.PengumumanAdapter;
import com.example.contract.PengumumanUI;
import com.example.model.Pengumuman;
import com.example.presenter.PengumumanPresenter;
import com.example.uas_p3b.databinding.PengumumanBinding;

import java.util.List;

public class PengumumanFragment extends Fragment implements PengumumanUI, View.OnClickListener {
    private PengumumanBinding binding;
    private PengumumanAdapter adapter;
    private PengumumanPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PengumumanBinding.inflate(inflater,container,false);
        adapter = new PengumumanAdapter(this);
        presenter = new PengumumanPresenter(this);
        binding.listview.setAdapter(adapter);
        return binding.getRoot();

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public LayoutInflater getLayout() {
        return getLayoutInflater();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void setFilter(String Filter) {
        binding.filterApply.setText(Filter);
    }

    @Override
    public String getTitle() {
        return binding.searchTitle.getText().toString();
    }

    @Override
    public void loadingAdapter() {
        binding.listview.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.item_list_pengumuman,R.id.TampilJudul,new String[]{"loading...."}));
    }


    @Override
    public void updateList(List<Pengumuman> list) {
        this.adapter.update(list);
    }

    @Override
    public void listenerOnClick(int pos) {

    }
}
