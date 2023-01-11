package com.example.uas_p3b;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.contract.TambahPengumumanUI;
import com.example.presenter.TambahPengumumanPresenter;
import com.example.uas_p3b.databinding.TambahPengumumanBinding;

public class TambahPengumumanFragment extends Fragment implements TambahPengumumanUI {
    private TambahPengumumanBinding binding;
    private TambahPengumumanPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TambahPengumumanBinding.inflate(inflater,container,false);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Context getCtx() {
        return getContext();
    }

    @Override
    public void menampilkanPesanError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void berhasilTambah() {
        Bundle result = new Bundle();
        result.putString("page","pengumman");
        getParentFragmentManager().setFragmentResult("changePage",result);
    }
}
