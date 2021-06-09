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

import com.example.barcode.model.Bahan;

public class Page3Fragment extends Fragment implements OnCardListener {

    private View view;
    private TextView frag_page3_label;
    private RecyclerView bahan_recyclerView;
    private ArrayList<Bahan> dataBahan;
    private BahanRVAdapter adapter;
    private FloatingActionButton bahan_FAB_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page3, container, false);
        initView();
        setupRecyclerView();
        //addDummyData();
        loadDataDB();
        setListener();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dataBahan.clear();
        loadDataDB();
    }

    private void initView(){
        frag_page3_label = view.findViewById(R.id.frag_page3_label);
        bahan_recyclerView = view.findViewById(R.id.bahan_recyclerView);
        bahan_FAB_add = view.findViewById(R.id.bahan_FAB_add);
        dataBahan = new ArrayList<Bahan>();
        adapter = new BahanRVAdapter(dataBahan, this);
        adapter.notifyDataSetChanged();
    }

    private void setListener(){
        bahan_FAB_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InputBahanActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    private void addDummyData(){
        dataBahan.add(new Bahan("Ayam",100));
        dataBahan.add(new Bahan("Telur",50));
        dataBahan.add(new Bahan("Udang",300));
        dataBahan.add(new Bahan("Tepung",100));
        dataBahan.add(new Bahan("Gula",5000));
        dataBahan.add(new Bahan("Garam",500));
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        bahan_recyclerView.setLayoutManager(manager);
        bahan_recyclerView.setAdapter(adapter);
    }

    private void loadDataDB(){
        String url = "http://192.168.0.5/Bukalapar/bahan/ReadAllBahan.php";
        RequestQueue myQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonBahan = response.getJSONArray("bahan");
                            for (int i = 0; i < jsonBahan.length(); i++){
                                JSONObject objBahan = jsonBahan.getJSONObject(i);
                                Bahan bahanBaru = new Bahan();
                                bahanBaru.setId(objBahan.getString("id"));
                                bahanBaru.setNama(objBahan.getString("nama"));
                                bahanBaru.setJumlah(objBahan.getInt("jumlah"));
                                dataBahan.add(bahanBaru);
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

    @Override
    public void onCardClick(int position) {
        String id = dataBahan.get(position).getId();
        Intent intent = new Intent(getContext(), detailBahanActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}