package com.example.presenter;

import com.example.contract.HomeFragmentUI;

public class HomeFragmentPresenter {
    private HomeFragmentUI ui;

    public HomeFragmentPresenter(HomeFragmentUI ui) {
        this.ui = ui;
        //ilangin btn frs klo bukan student
        this.ui.enabledBtnFRS(ui.getRole().equals("student"));
    }
}