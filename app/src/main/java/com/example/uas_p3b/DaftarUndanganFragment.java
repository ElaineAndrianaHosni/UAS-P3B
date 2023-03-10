package com.example.uas_p3b;

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
import com.example.adapter.AdapterUndangan;
import com.example.uas_p3b.databinding.DaftarUndanganBinding;
//import com.example.tubes_2.databinding.DaftarUndanganBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class DaftarUndanganFragment extends Fragment {
    private String token;
    DaftarUndanganBinding binding;
    AdapterUndangan adapterUndangan;

    public DaftarUndanganFragment(String token) {
        this.token = token;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =DaftarUndanganBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        adapterUndangan = new AdapterUndangan(getActivity(),this);
        binding.undangan.setAdapter(adapterUndangan);
        callAPI("https://ifportal.labftis.net/api/v1/appointments/invitations");
        return view;
    }
    public void callAPI(String Base_URL){
        this.adapterUndangan.clear();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    cariUndangan(response);

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
//                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiJkNGNjOGZjMS02YmUyLTQ0MTEtOTJmNC01MDI3YTkyODEzNmMiLCJyb2xlIjoic3R1ZGVudCJ9LCJpYXQiOjE2NzM0MTI4NzR9.gMV_1UfVq_zRtynlNpkjrQ2SJf279b7k10ekqEkUyFs");
                map.put("Authorization","Bearer " + token);
                return map;
            }
        };
        queue.add(stringRequest);
    }
    public void cariUndangan(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        for(int i=0;i<jsonArray.length();i++){
            String id = jsonArray.getJSONObject(i).getString("appointment_id");
            String title = jsonArray.getJSONObject(i).getString("title");
            String description = jsonArray.getJSONObject(i).getString("description");
            String start_datetime = jsonArray.getJSONObject(i).getString("start_datetime");
            String end_datetime = jsonArray.getJSONObject(i).getString("end_datetime");
            String organizer_name = jsonArray.getJSONObject(i).getString("organizer_name");
            boolean attending = jsonArray.getJSONObject(i).getBoolean("attending");
            adapterUndangan.add(id,title,start_datetime,end_datetime,description,organizer_name,attending);
        }
    }
    public void memprosesKeluaranGagal(VolleyError error) throws JSONException {
        if(error instanceof NoConnectionError){
            Toast.makeText(getActivity(),"Tidak ada koneksi internet",Toast.LENGTH_LONG).show();
        }else if(error instanceof TimeoutError){
            Toast.makeText(getActivity(),"Server memakan waktu lama untuk merespon\nCoba Lagi!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(),"GAGAL",Toast.LENGTH_LONG).show();
        }
    }
    public void refresh(){
        callAPI("https://ifportal.labftis.net/api/v1/appointments/invitations");
    }
}
