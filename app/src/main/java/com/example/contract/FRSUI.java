package com.example.contract;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.List;

public interface FRSUI {
    public String getToken();
    //buat get email d presenter
    public String getEmail();
    public Activity getAct();
    public int getActiveYear();
    public void setActiveYear(int activeYear);
    public LayoutInflater getLayout();
    public void menampilkanError(String error);
    public void updateList(List<Integer> list);
    //bundle bundle buat ngirim ke change pagenya
    public void changePage(Bundle bundle);
    public void loadingAdapter();
}
