package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginRegisterActivity extends AppCompatActivity {
    Database db;
    private TextInputLayout login_textInputLayout_email, login_textInputLayout_password ;
    private Button button, button2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        db = new Database(this);

        login_textInputLayout_email = (TextInputLayout)findViewById(R.id.login_textInputLayout_email);
        login_textInputLayout_password = (TextInputLayout)findViewById(R.id.login_textInputLayout_password);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);




        //register
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginRegisterActivity.this, SignUpActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //login
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strUsername = login_textInputLayout_email.getEditText().toString();
                String strPassword = login_textInputLayout_password.getEditText().toString();
                Boolean masuk = db.checkLogin(strUsername, strPassword);
                if (masuk == true){
                    Boolean updateSession = db.upgradeSession("ada",1);
                    if(updateSession == true){
                        Toast.makeText(getApplicationContext(),"Berhasil masuk", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginRegisterActivity.this,Home.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Masuk Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}