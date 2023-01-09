package com.example.contract;

import android.app.Activity;
import android.view.LayoutInflater;

public interface FRSUI {
    public String getToken();
    public Activity getAct();
    public LayoutInflater getLayout();
    public void menampilkanError(String error);
}
