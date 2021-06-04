package com.example.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import com.example.barcode.model.Bahan;

public class InputBahanActivity extends AppCompatActivity {

    private TextInputLayout inputBahan_textInputLayout_nama, inputBahan_textInputLayout_jumlah;
    private Button inputBahan_button_tambah;
    private BahanRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bahan);

        inputBahan_textInputLayout_nama = findViewById(R.id.inputBahan_textInputLayout_nama);
        inputBahan_textInputLayout_jumlah = findViewById(R.id.inputBahan_textInputLayout_jumlah);
        inputBahan_button_tambah = findViewById(R.id.inputBahan_button_tambah);

        setListener();
    }

    private void setListener(){
        inputBahan_button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = inputBahan_textInputLayout_nama.getEditText().getText().toString().trim();
                int jumlah = Integer.parseInt(inputBahan_textInputLayout_jumlah.getEditText().getText().toString().trim());
                Bahan temp = new Bahan(nama, jumlah);
                postData(temp);
//                adapter.notifyDataSetChanged();
            }
        });
    }

    private void postData(final Bahan temp){
        String url = "http://192.168.0.5/Bukalapar/bahan/CreateBahan.php";
        RequestQueue myRequest = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Intent intent= new Intent(getBaseContext(), Page3Fragment.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("nama", temp.getNama());
                data.put("jumlah", String.valueOf(temp.getJumlah()));

                return data;
            }
        };
        myRequest.add(request);
    }
}