package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Database db;
    Button button, button2;
    private int waktu_loading=3000;
    public static TextView result_text;
    private Button btn_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        db = new Database(this);
        button = (Button)findViewById(R.id.button);

        //Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke LoginRegister activity
                Intent home=new Intent(MainActivity.this, LoginRegisterActivity.class);
                startActivity(home);
                finish();
            }
        },waktu_loading);

        //Database
        Boolean checkSession = db.checkSession("ada");
        if(checkSession == false){
            Intent loginIntent = new Intent(MainActivity.this,LoginRegisterActivity.class);
            startActivity(loginIntent);
            finish();
        }




        //init();
        //setListener();

    }

    public void init (){
        result_text = findViewById(R.id.result_text);
        btn_scan = findViewById(R.id.btn_scan);
    }

    public void setListener(){
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ScanCodeActivity.class));
            }
        });
    }
}