package com.example.presenter;

import com.example.contract.TambahPengumumanUI;
import com.example.model.Config;
import com.example.model.Tags;

public class TambahPengumumanPresenter {
    private TambahPengumumanUI ui;
    private final static String announcementsURL = Config.BASE_URL + "announcements";

    public TambahPengumumanPresenter(TambahPengumumanUI ui) {
        this.ui = ui;
    }
    public void callAPI(String title, String content, Tags[] tags){

    }
}
