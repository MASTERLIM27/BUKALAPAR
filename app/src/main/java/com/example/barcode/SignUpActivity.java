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

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout SignUp_textInputLayout_username, SignUp_textInputLayout_email, SignUp_textInputLayout_password;
    private Button SignUp_buttonSignUp;
    private ArrayList<User> users = ListUser.arrayUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();

        //register
        SignUp_buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = SignUp_textInputLayout_username.getEditText().toString();
                String email = SignUp_textInputLayout_email.getEditText().toString();
                String password = SignUp_textInputLayout_password.getEditText().toString();

                if(email.isEmpty()){
                    SignUp_textInputLayout_email.setError("Please fill the Email column");
                }else{
                    SignUp_textInputLayout_email.setError("");
                }

                if(username.isEmpty()){
                    SignUp_textInputLayout_username.setError("Please fill the name column");
                }else{
                    SignUp_textInputLayout_username.setError("");
                }

                if(password.isEmpty()){
                    SignUp_textInputLayout_password.setError("Please fill the Password column");
                }else{
                    SignUp_textInputLayout_password.setError("");
                }

                if(!username.isEmpty()&& !password.isEmpty()&& !email.isEmpty()){
                    Intent intent = new Intent(getBaseContext(),LoginRegisterActivity.class);
                    User userbaru = new User(username, email, password);
                    users.add(userbaru);
//                    String test1 = userbaru.getUsername();
//                    String test2 = userbaru.getPassword();
//                    Toast.makeText(getApplicationContext(), test1, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), test2, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
       });
    }
    private void initView(){
        SignUp_textInputLayout_username = (TextInputLayout) findViewById(R.id.SignUp_textInputLayout_username);
        SignUp_textInputLayout_email = (TextInputLayout) findViewById(R.id.SignUp_textInputLayout_email);
        SignUp_textInputLayout_password = (TextInputLayout) findViewById(R.id.SignUp_textInputLayout_password);
        SignUp_buttonSignUp = (Button) findViewById(R.id.SignUp_buttonSignUp);
    }
}