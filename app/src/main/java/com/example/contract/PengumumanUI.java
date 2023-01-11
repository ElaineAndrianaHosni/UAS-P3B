package com.example.contract;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.example.model.Pengumuman;

import java.util.List;

public interface PengumumanUI {
    //karena fragment (buat binding)
    public LayoutInflater getLayout();
    //ambil HomeActivity, karena sudah intent ke homeActivity stlh berhasil login
    //jd yg pny activity itu si homeAct, pengumuman sbnrnya panel, dia berdiri di homeActivity
    public Activity getAct();
    //ngubah filter apply di view
    public void setFilter(String Filter);
    //buat ambil judul pengumuman
    public String getTitle();
    public String getToken();
    public String getRole();
    public void menampilkanError(String error);
    //ini metod kl gaada nextnya ya gaakan visible
    public void setVisibleBtnNext(boolean visible);
    public void setVisibleBtnTambah(boolean visible);
    //nampilin loading
    public void loadingAdapter();
    //nampilin gaada data di adapter
    public void noDataAdapter();
    //untuk mengupdate smua list yang ada
    public void updateList(List<Pengumuman> list);
    //buat manggil kom antara fragment
    public void listenerOnClick (String id);
    public void reloadAdapter();
}
