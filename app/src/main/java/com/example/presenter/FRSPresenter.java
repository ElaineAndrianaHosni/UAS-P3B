package com.example.presenter;

import android.widget.ArrayAdapter;
import android.widget.Toast;

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
import com.example.model.Config;
import com.example.uas_p3b.MainActivity;
import com.example.uas_p3b.R;
import com.example.uas_p3b.databinding.LayoutFrsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FRSPresenter {
    private LayoutFrsBinding frsBinding;
    private FRSUI ui;
    private FRSAdapter adapterFRS;
    //initial dapet waktu login
    private String email;
    private int initial_year;
    private int active_year;
    private ArrayList<Integer> semester;
    private String announcementsURL;

    public FRSPresenter (FRSUI ui){
        this.ui = ui;
        this.semester = new ArrayList<>();
        MainActivity m = new MainActivity();
        this.email = m.ambilEmail();
        this.announcementsURL = Config.BASE_URL + this.email;
    }
    public void callAPI(String ngapain){
//        frsBinding.lstFrs.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_item_spinner,R.id.isi,new String[]{"Harap Tunggu..."}));
        RequestQueue queue = Volley.newRequestQueue(this.ui.getLayout().getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                this.announcementsURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if(ngapain.equals("cariTahun")){
                        memprosesKeluaranBerhasil(response);
                    }
                    else{
                        memprosesInitialYear(response);
                    }
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
                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiI2ZTY2ODZmMC0yOTZlLTRjNzItOGE0NS1hNmFjMWVkNDhlNDQiLCJyb2xlIjoiYWRtaW4ifSwiaWF0IjoxNjcxODY3ODQ5fQ.1i7kt7EWvw_q9EzRPFGePWiZxx4c5dRmMSm1jV93g_I");
                return map;
            }
        };
        queue.add(stringRequest);
    }
    public void memprosesKeluaranBerhasil(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("academic_years");
        this.active_year = jsonObject.getInt("active_year");
        for(int i=0;i<jsonArray.length();i++){
            int masukan = jsonArray.getInt(i);
            int tahun = masukan/10;
            if(tahun<=(this.initial_year/10)+7 && tahun>=(this.initial_year/10)){
                semester.add(masukan);
            }
        }
        adapterFRS = new FRSAdapter(semester,getActivity(),active_year);
        frsBinding.lstFrs.setAdapter(adapterFRS);
    }
    public void memprosesKeluaranGagal(VolleyError error) throws JSONException {
        String res = "";
        if(error instanceof NoConnectionError){
            res="Tidak ada koneksi internet";
        }else if(error instanceof TimeoutError){
            res="Server memakan waktu lama untuk merespon\nCoba Lagi!";
        }
        else{
            String jsonKeluaran = new String(error.networkResponse.data);
            JSONObject jsonObject = new JSONObject(jsonKeluaran);
            res = jsonObject.get("errcode").toString();
        }
        this.ui.menampilkanError(res);
    }
    public void memprosesInitialYear(String response) throws JSONException {
        initial_year = new JSONObject(response).getInt("initial_year");
        callAPI("cariTahun");
    }
}
