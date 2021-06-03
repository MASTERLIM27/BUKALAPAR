package com.example.barcode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import modal.Resep;

public class ResepRVAdapter extends RecyclerView.Adapter<ResepRVAdapter.ResepViewHolder>{

    private ArrayList <Resep> listResep;
    protected OnCardListener cardListener;

    public ResepRVAdapter(ArrayList<Resep> listResep, OnCardListener cardListener) {
        this.cardListener = cardListener;
        this.listResep = listResep;
    }

    @NonNull
    @Override
    public ResepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_resep, parent, false);
        return new ResepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResepViewHolder holder, int position) {
        holder.card_textView_nama.setText(listResep.get(position).getNama());
        holder.card_TextView_deskripsi.setText(String.valueOf(listResep.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return listResep.size();
    }

    public class ResepViewHolder extends RecyclerView.ViewHolder {

        private TextView card_textView_nama,card_TextView_deskripsi;
        private ImageView card_image_item;

        public ResepViewHolder(@NonNull View itemView) {
            super(itemView);

            card_textView_nama = itemView.findViewById(R.id.card_textView_nama);
            card_TextView_deskripsi = itemView.findViewById(R.id.card_TextView_deskripsi);
            card_image_item = itemView.findViewById(R.id.card_image_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.onCardClick(getAdapterPosition());
                }
            });
        }
    }
}
