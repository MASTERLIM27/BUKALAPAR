package com.example.barcode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Page2Fragment extends Fragment {

    private View view;
    private TextView frag_page2_label;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page2, container, false);
        initView();
        return view;
    }

    private void initView(){
        frag_page2_label = view.findViewById(R.id.frag_page2_label);
        frag_page2_label.setText("Berhasil 2!");
    }
}