package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import modal.ListUser;
import modal.User;

public class LoginRegisterActivity extends AppCompatActivity {

    private TextInputLayout login_textInputLayout_username, login_textInputLayout_password ;
    private Button login_buttonLogin, login_buttonSignUp;
    private ArrayList<User> users = ListUser.arrayUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        initView();

        //register
        login_buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginRegisterActivity.this, SignUpActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //login
        login_buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = login_textInputLayout_username.getEditText().getText().toString().trim();
                String password = login_textInputLayout_password.getEditText().getText().toString().trim();

                for (int i = 0; i < users.size(); i++){
                    User tempUser = users.get(i);
//                    String test1 = tempUser.getUsername();
//                    String test2 = tempUser.getPassword();
//                    Toast.makeText(getApplicationContext(), test1, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), test2, Toast.LENGTH_SHORT).show();
                    if ((tempUser.getUsername().equalsIgnoreCase(username))){
                        if(tempUser.getPassword().equalsIgnoreCase(password)){
                            String pUsername = tempUser.getUsername();
                            String pEmail = tempUser.getEmail();
                            Intent intent = new Intent(getBaseContext(), BahanActivity.class);
                            intent.putExtra("IDuser", i);
                            intent.putExtra("IDusername",pUsername);
                            intent.putExtra("IDemail", pEmail);
                            finish();
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                            break;
                        }else{
                            Toast.makeText(getApplicationContext(), "Unable to Login, wrong email/password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Unable to Login, No Account Registered", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void initView(){
        login_textInputLayout_username = (TextInputLayout)findViewById(R.id.login_textInputLayout_username);
        login_textInputLayout_password = (TextInputLayout)findViewById(R.id.login_textInputLayout_password);
        login_buttonLogin = (Button)findViewById(R.id.login_buttonLogin);
        login_buttonSignUp = (Button)findViewById(R.id.login_buttonSignUp);
    }
}