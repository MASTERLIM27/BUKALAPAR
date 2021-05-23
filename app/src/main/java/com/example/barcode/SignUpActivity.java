package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    Database db;
    private TextInputLayout login_textInputLayout_email, login_textInputLayout_password, passwordConf ;
    private Button button, button2;
    private TextInputLayout username,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new Database(this);
        username = (TextInputLayout) findViewById(R.id.regist_textInputLayout_nama);
        email = (TextInputLayout) findViewById(R.id.regist_textInputLayout_email);
        password = (TextInputLayout) findViewById(R.id.regist_textInputLayout_password);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        //login
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignUpActivity.this, LoginRegisterActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getEditText().toString();
                String strEmail = email.getEditText().toString();
                String strPassword = password.getEditText().toString();

                if (strPassword.equals(strPassword)){
                    Boolean daftar = db.insertUser(strUsername,strPassword);
                    if(daftar == true){
                        Toast.makeText(getApplicationContext(),"Daftar Berhasil",Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(SignUpActivity.this, LoginRegisterActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Password tidak cocok",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}