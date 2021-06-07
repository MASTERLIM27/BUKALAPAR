package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class detailResepActivity extends AppCompatActivity {

    private TextView detail_label_nama, detail_label_jumlah,detail_label_created;
    private Button detail_button_edit, detail_button_delete;
    private int id;

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
                            detail_label_jumlah.setText(String.valueOf(dresep.getInt("jumlah")));
                            detail_label_created.setText(dresep.getString("created"));
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
        detail_label_nama = findViewById(R.id.detail_label_nama);
        detail_label_jumlah = findViewById(R.id.detail_label_jumlah);
        detail_label_created = findViewById(R.id.detail_label_created);
        detail_button_edit = findViewById(R.id.detail_button_edit);
        detail_button_delete = findViewById(R.id.detail_button_delete);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
    }
}