package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

public class detailResepActivity extends AppCompatActivity {

    private TextView detail_label_nama, detailResep_label_bahan1,detailResep_label_bahan2,detailResep_label_bahan3,detailResep_label_bahan4,detailResep_label_bahan5,
            detailResep_label_instruksi, detailResep_label_jumlahbahan1, detailResep_label_jumlahbahan2, detailResep_label_jumlahbahan3, detailResep_label_jumlahbahan4, detailResep_label_jumlahbahan5;
    private int id;
    private ImageView detailResep_imageView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resep);
        initView();
        getData();
    }

    private void getData(){
        String url = "http://192.168.0.5/Bukalapar/resep/ReadResepByID.php";
        RequestQueue myQueue = Volley.newRequestQueue(this);

        JSONObject parameter = new JSONObject();
        try {
            parameter.put("id", id);
            parameter.put("context", context);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject dresep = response.getJSONObject("resep");
                            detail_label_nama.setText(dresep.getString("nama"));
                            Glide.with(getApplicationContext()).load(dresep.getString("image_path")).thumbnail(1f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(detailResep_imageView);
                            detailResep_label_bahan1.setText(dresep.getString("bahan_1"));
                            detailResep_label_bahan2.setText(dresep.getString("bahan_2"));
                            detailResep_label_bahan3.setText(dresep.getString("bahan_3"));
                            detailResep_label_bahan4.setText(dresep.getString("bahan_4"));
                            detailResep_label_bahan5.setText(dresep.getString("bahan_5"));
                            detailResep_label_jumlahbahan1.setText(String.valueOf(dresep.getInt("jumlah_bahan_1")));
                            detailResep_label_jumlahbahan2.setText(String.valueOf(dresep.getInt("jumlah_bahan_2")));
                            detailResep_label_jumlahbahan3.setText(String.valueOf(dresep.getInt("jumlah_bahan_3")));
                            detailResep_label_jumlahbahan4.setText(String.valueOf(dresep.getInt("jumlah_bahan_4")));
                            detailResep_label_jumlahbahan5.setText(String.valueOf(dresep.getInt("jumlah_bahan_5")));
                            detailResep_label_instruksi.setText(dresep.getString("instruksi"));


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

    private void initView(){
        detail_label_nama = findViewById(R.id.detailResep_label_nama);
        detailResep_label_bahan1 = findViewById(R.id.detailResep_label_bahan1);
        detailResep_label_bahan2 = findViewById(R.id.detailResep_label_bahan2);
        detailResep_label_bahan3 = findViewById(R.id.detailResep_label_bahan3);
        detailResep_label_bahan4 = findViewById(R.id.detailResep_label_bahan4);
        detailResep_label_bahan5 = findViewById(R.id.detailResep_label_bahan5);
        detailResep_label_jumlahbahan1 = findViewById(R.id.detailResep_label_jumlahbahan1);
        detailResep_label_jumlahbahan2 = findViewById(R.id.detailResep_label_jumlahbahan2);
        detailResep_label_jumlahbahan3 = findViewById(R.id.detailResep_label_jumlahbahan3);
        detailResep_label_jumlahbahan4 = findViewById(R.id.detailResep_label_jumlahbahan4);
        detailResep_label_jumlahbahan5 = findViewById(R.id.detailResep_label_jumlahbahan5);
        detailResep_label_instruksi = findViewById(R.id.detailResep_label_instruksi);
        detailResep_imageView = findViewById(R.id.detailResep_imageView);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
    }
}