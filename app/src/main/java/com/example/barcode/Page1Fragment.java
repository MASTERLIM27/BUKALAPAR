package com.example.barcode;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.example.barcode.model.Resep;

public class Page1Fragment extends Fragment implements OnCardListener{

    private View view;
    private RecyclerView recommend_recyclerView;
    private ArrayList<Resep> dataResep;
    private ResepRVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page1, container, false);
        initView();
        setupRecyclerView();
//        addDummyData();
        loadDataDB();
        setListener();
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
        recommend_recyclerView = view.findViewById(R.id.recommend_recyclerView);
        dataResep = new ArrayList<Resep>();
        adapter = new ResepRVAdapter(dataResep, this,getContext());
    }
    private void setListener(){
//        recyclerView_FAB_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(), InputBarangActivity.class);
//                startActivityForResult(intent,1);
//            }
//        });
    }

    private void addDummyData(){
        dataResep.add(new Resep("Meja belajar",100));
        dataResep.add(new Resep("Kursi belajar",50));
        dataResep.add(new Resep("TV",300));
        dataResep.add(new Resep("Laptop",100));
        dataResep.add(new Resep("Botol",5000));
        dataResep.add(new Resep("Charger",500));
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recommend_recyclerView.setLayoutManager(manager);
        recommend_recyclerView.setAdapter(adapter);
    }

    private void loadDataDB(){
        String url = "http://192.168.0.5/Bukalapar/resep/ReadAllResep.php";
        RequestQueue myQueue = Volley.newRequestQueue(getContext());

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
                                resepBaru.setBahan_1(objResep.getString("bahan_1"));
                                resepBaru.setBahan_2(objResep.getString("bahan_2"));
                                resepBaru.setBahan_3(objResep.getString("bahan_3"));
                                resepBaru.setBahan_4(objResep.getString("bahan_4"));
                                resepBaru.setBahan_5(objResep.getString("bahan_5"));
                                resepBaru.setJumlah_bahan_1(objResep.getInt("jumlah_bahan_1"));
                                resepBaru.setJumlah_bahan_2(objResep.getInt("jumlah_bahan_2"));
                                resepBaru.setJumlah_bahan_3(objResep.getInt("jumlah_bahan_3"));
                                resepBaru.setJumlah_bahan_4(objResep.getInt("jumlah_bahan_4"));
                                resepBaru.setJumlah_bahan_5(objResep.getInt("jumlah_bahan_5"));
                                resepBaru.setInstruksi(objResep.getString("instruksi"));
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

    @Override
    public void onCardClick(int position) {
        int id = dataResep.get(position).getId();
        Intent intent = new Intent(getContext(), detailResepActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}