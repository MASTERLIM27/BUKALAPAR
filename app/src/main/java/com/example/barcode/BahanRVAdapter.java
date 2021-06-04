package com.example.barcode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.barcode.model.Bahan;

public class BahanRVAdapter extends RecyclerView.Adapter<BahanRVAdapter.BahanViewHolder>{

    private ArrayList <Bahan> listBahan;
    protected OnCardListener cardListener;

    public BahanRVAdapter(ArrayList<Bahan> listBahan, OnCardListener cardListener) {
        this.cardListener = cardListener;
        this.listBahan = listBahan;
    }

    @NonNull
    @Override
    public BahanRVAdapter.BahanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_bahan, parent, false);
        return new BahanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BahanRVAdapter.BahanViewHolder holder, int position) {
        holder.card_textView_nama.setText(listBahan.get(position).getNama());
        holder.card_TextView_deskripsi.setText(String.valueOf(listBahan.get(position).getJumlah()));
    }

    @Override
    public int getItemCount() {
        return listBahan.size();
    }

    public class BahanViewHolder extends RecyclerView.ViewHolder {

        private TextView card_textView_nama,card_TextView_deskripsi;
        private ImageView card_image_item;

        public BahanViewHolder(@NonNull View itemView) {
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
