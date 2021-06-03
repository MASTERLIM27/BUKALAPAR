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

public class Page1Fragment extends Fragment implements OnCardListener{

    private View view;
    private TextView frag_page1_label;
    private RecyclerView home_recylerView;
    private ArrayList<Resep> dataResep;
    private ResepRVAdapter adapter;
    private FloatingActionButton recyclerView_FAB_add;

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
        frag_page1_label = view.findViewById(R.id.frag_page3_label);
        home_recylerView = view.findViewById(R.id.bahan_recylerView);
//        recyclerView_FAB_add = findViewById(R.id.recyclerView_FAB_add);
        dataResep = new ArrayList<Resep>();
        adapter = new ResepRVAdapter(dataResep, this);
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
//    private void addDummyData(){
//        dataBarang.add(new Barang("Meja belajar",100));
//        dataBarang.add(new Barang("Kursi belajar",50));
//        dataBarang.add(new Barang("TV",300));
//        dataBarang.add(new Barang("Laptop",100));
//        dataBarang.add(new Barang("Botol",5000));
//        dataBarang.add(new Barang("Charger",500));
//        adapter.notifyDataSetChanged();
//    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        home_recylerView.setLayoutManager(manager);
        home_recylerView.setAdapter(adapter);
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

    @Override
    public void onCardClick(int position) {
        int id = dataResep.get(position).getId();
        Intent intent = new Intent(getContext(), detailResepActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}