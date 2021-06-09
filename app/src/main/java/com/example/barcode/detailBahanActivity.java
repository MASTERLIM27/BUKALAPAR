package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class detailBahanActivity extends AppCompatActivity {

    private TextView datailBahan_textView_nama, datailBahan_textView_jumlah;
    private TextInputLayout detailBahan_textInputLayout_id, detailBahan_textInputLayout_nama, detailBahan_textInputLayout_jumlah;
    public static TextInputEditText detailBahan_EditText_id;
    private TextInputEditText detailBahan_EditText_nama, detailBahan_EditText_jumlah;
    private Button detailBahan_button_save;
    private ImageButton detailBahan_imageButton_code;
    private String id;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bahan);
        initView();
        getData();
        setListener();
    }

    private void getData(){
        String url = "http://192.168.0.5/Bukalapar/bahan/ReadBahanByID.php";
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
                            JSONObject dresep = response.getJSONObject("bahan");
                            datailBahan_textView_nama.setText(dresep.getString("nama"));
                            datailBahan_textView_jumlah.setText(dresep.getString("jumlah"));
                            detailBahan_EditText_id.setText(dresep.getString("id"));
                            detailBahan_EditText_nama.setText(dresep.getString("nama"));
                            detailBahan_EditText_jumlah.setText(String.valueOf(dresep.getInt("jumlah")));
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

    private void updateData(){
        String url = "http://192.168.0.5/Bukalapar/bahan/UpdateBahan.php";
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
                            JSONObject dresep = response.getJSONObject("bahan");
                            datailBahan_textView_nama.setText(dresep.getString("nama"));
                            datailBahan_textView_jumlah.setText(dresep.getString("jumlah"));
                            detailBahan_EditText_id.setText(dresep.getString("id"));
                            detailBahan_EditText_nama.setText(dresep.getString("nama"));
                            detailBahan_EditText_jumlah.setText(String.valueOf(dresep.getInt("jumlah")));
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
        datailBahan_textView_nama = findViewById(R.id.datailBahan_textView_nama);
        datailBahan_textView_jumlah = findViewById(R.id.datailBahan_textView_jumlah);
        detailBahan_textInputLayout_id = findViewById(R.id.detailBahan_textInputLayout_id);
        detailBahan_textInputLayout_nama = findViewById(R.id.detailBahan_textInputLayout_nama);
        detailBahan_textInputLayout_jumlah = findViewById(R.id.detailBahan_textInputLayout_jumlah);
        detailBahan_button_save = findViewById(R.id.detailBahan_button_save);
        detailBahan_imageButton_code = findViewById(R.id.detailBahan_imageButton_code);
        detailBahan_EditText_id = findViewById(R.id.detailBahan_EditText_id);
        detailBahan_EditText_nama = findViewById(R.id.detailBahan_EditText_nama);
        detailBahan_EditText_jumlah = findViewById(R.id.detailBahan_EditText_jumlah);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    private void setListener(){
        detailBahan_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        detailBahan_imageButton_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ScanCodeActivity.class));
            }
        });
    }
}