package com.example.presenter;

import com.example.contract.HomeUI;

public class HomePresenter {
    private HomeUI ui;


    public HomePresenter(HomeUI ui) {
        this.ui = ui;
        if(ui.getRole()!="student"){

        }
        //inisialisasi page pertama yang kebuka di home
        this.ui.changePage("Home", null);
    }
}
