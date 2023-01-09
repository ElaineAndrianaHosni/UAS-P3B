package com.example.uas_p3b;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.FRSAdapter;
import com.example.contract.FRSUI;
import com.example.presenter.FRSPresenter;
import com.example.uas_p3b.databinding.LayoutFrsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FRSFragment extends Fragment implements FRSUI, View.OnClickListener{
    private LayoutFrsBinding frsBinding;
    private String token;
    private FRSAdapter adapter;
    private FRSPresenter presenter;

    public FRSFragment(String token) {
        this.token = token;
    }

    public static FRSFragment newInstance(String token){
        return new FRSFragment(token);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        frsBinding = LayoutFrsBinding.inflate(inflater,container,false);
        View view = frsBinding.getRoot();
        presenter = new FRSPresenter(this);
        adapter = new FRSAdapter(this);

//        semester = new ArrayList<>();
        presenter.callAPI("cariInitialYear");
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public LayoutInflater getLayout() {
        return getLayoutInflater();
    }

    @Override
    public void menampilkanError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
