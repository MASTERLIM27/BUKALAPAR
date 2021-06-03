package com.example.barcode;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modal.Resep;

public class Page3Fragment extends Fragment {

    private View view;
    private TextView frag_page3_label;
    private RecyclerView bahan_recylerView;
    private ArrayList<Resep> dataResep;
    private ResepRVAdapter adapter;
    private FloatingActionButton recyclerView_FAB_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page3, container, false);
        initView();
        setupRecyclerView();
//        addDummyData();
        loadDataDB();
//        setListener();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if (resultCode == 200){
                Resep resepBaru = data.getParcelableExtra("resepBaru");
                dataResep.add(resepBaru);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void initView(){
        frag_page3_label = view.findViewById(R.id.frag_page3_label);
        bahan_recylerView = view.findViewById(R.id.bahan_recylerView);
//        recyclerView_FAB_add = findViewById(R.id.recyclerView_FAB_add);
        dataResep = new ArrayList<Resep>();
//        adapter = new BahanRVAdapter(dataResep, this);
    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        bahan_recylerView.setLayoutManager(manager);
        bahan_recylerView.setAdapter(adapter);
    }

    private void loadDataDB(){
        String url = "https://192.168.0.5/Bukalapar/resep/ReadAllResep.php";
        RequestQueue myQueue = Volley.newRequestQueue(this.getContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonResep = response.getJSONArray("resep");
                            for (int i = 0; i < jsonResep.length(); i++){
                                JSONObject objResep = jsonResep.getJSONObject(i);
                                Resep resepBaru = new Resep();
                                resepBaru.setId(objResep.getInt("id"));
                                resepBaru.setNama(objResep.getString("nama"));
                                resepBaru.setImage_path(objResep.getString("image_path"));
                                resepBaru.setJumlah(objResep.getInt("jumlah"));
                                resepBaru.setCreated(objResep.getString("created"));
                                dataResep.add(resepBaru);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        myQueue.add(request);
    }

//    @Override
//    public void onCardClick(int position) {
//        int id = dataResep.get(position).getId();
//        Intent intent = new Intent(getContext(), detailResepActivity.class);
//        intent.putExtra("id", id);
//        startActivity(intent);
//    }
}