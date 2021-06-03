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

import modal.Bahan;

public class InputBahanActivity extends AppCompatActivity {

    private TextInputLayout inputbarang_textInputLayout_nama, inputBarang_textInputLayout_jumlah;
    private Button inputBarang_button_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bahan);

        inputbarang_textInputLayout_nama = findViewById(R.id.inputbarang_textInputLayout_nama);
        inputBarang_textInputLayout_jumlah = findViewById(R.id.inputBarang_textInputLayout_jumlah);
        inputBarang_button_tambah = findViewById(R.id.inputBarang_button_tambah);

        setListener();
    }

    private void setListener(){
        inputBarang_button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = inputbarang_textInputLayout_nama.getEditText().getText().toString().trim();
                int jumlah = Integer.parseInt(inputBarang_textInputLayout_jumlah.getEditText().getText().toString().trim());
                Bahan temp = new Bahan(nama, jumlah);

                postData(temp);
            }
        });
    }

    private void postData(final Bahan temp){
        String url = "http://192.168.0.5/PT/CreateBarang.php";
        RequestQueue myRequest = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent= new Intent(getBaseContext(), Page3Fragment.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
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