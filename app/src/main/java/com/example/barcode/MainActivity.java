package com.example.barcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout main_framelayout;
    private BottomNavigationView botnav_botnavbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setBotttomNavBar();
    }
    private void setBotttomNavBar(){
        botnav_botnavbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if(item.getItemId() == R.id.menu_page1){
                    selectedFragment = new Page1Fragment();
                }else if(item.getItemId() == R.id.menu_page2){
                    selectedFragment = new Page2Fragment();
                }else if(item.getItemId() == R.id.menu_page3){
                    selectedFragment = new Page3Fragment();
                }

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_framelayout, selectedFragment).commit();
                return true;
            }
        });
    }

    private void initView(){
        botnav_botnavbar = findViewById(R.id.main_botnavbar);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.main_framelayout,new Page1Fragment()).commit();
    }
}