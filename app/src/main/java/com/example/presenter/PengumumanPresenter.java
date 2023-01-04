package com.example.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.lights.LightState;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.contract.PengumumanUI;
import com.example.model.Config;
import com.example.model.Pengumuman;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PengumumanPresenter {
    private PengumumanUI ui;
    private List<Pengumuman>list;
    private List<String> checkTag,checkTagId;
    private final static String announcementsURL = Config.BASE_URL + "announcements";
    private SharedPreferences sp;
    private Gson gson;

    public PengumumanPresenter(PengumumanUI ui) {
        this.ui = ui;
        this.gson=new Gson();
        this.list= new LinkedList<>();
        this.checkTag = new LinkedList<>();
        this.checkTagId = new LinkedList<>();
        this.sp = this.ui.getAct().getPreferences(Context.MODE_PRIVATE);
        this.ambilTags();
    }
    public void callAPI(String Base_URL,boolean isCursor){
        if(!isCursor){
            Base_URL+="?filter[title]="+this.ui.getTitle();
            if(checkTagId.size()!=0){
                for(int i=0;i<checkTagId.size();i++){
                    Base_URL+="&filter[tags][]="+checkTagId.get(i);
                }
            }
        }
//        Toast.makeText(getActivity(),Base_URL,Toast.LENGTH_LONG).show();
        this.ui.loadingAdapter();
        RequestQueue queue = Volley.newRequestQueue(this.ui.getLayout().getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    memprosesKeluaranBerhasil(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    memprosesKeluaranGagal(error);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer ");
                return map;
            }
        };
        queue.add(stringRequest);
    }
    public void ambilTags(){
//        if(sp.contains("checkTag")){
        if(sp.getString("checkTag","").equals("")){
            checkTag.clear();
            this.ui.setFilter("Filter : None");
        }
        else{
            checkTag = new LinkedList<>(Arrays.asList(sp.getString("checkTag","").split(",")));
            this.ui.setFilter("Filter:"+sp.getString("checkTag",""));
        }
//        }
//        if(sp.contains("checkTagId")){
        if(sp.getString("checkTagId","").equals("")){
            checkTagId.clear();
        }else{
            checkTagId = new LinkedList<>(Arrays.asList(sp.getString("checkTagId","").split(",")));
        }

//        }
    }
}
