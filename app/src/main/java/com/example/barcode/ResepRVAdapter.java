package com.example.barcode;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.barcode.model.Resep;

public class ResepRVAdapter extends RecyclerView.Adapter<ResepRVAdapter.ResepViewHolder>{

    private ArrayList <Resep> listResep;
    protected OnCardListener cardListener;
    private Context context;

    public ResepRVAdapter(ArrayList<Resep> listResep, OnCardListener cardListener, Context context) {
        this.cardListener = cardListener;
        this.listResep = listResep;
        this.context = context;
    }

    @Override
    public ResepRVAdapter.ResepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_resep, parent, false);
        return new ResepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResepRVAdapter.ResepViewHolder holder, int position) {
        holder.cardResep_textView_nama.setText(listResep.get(position).getNama());
        holder.cardResep_TextView_jumlah.setText(String.valueOf(listResep.get(position).getJumlah()));
        Glide.with(context).load(listResep.get(position).getImage_path()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.cardResep_image_item);
    }

    @Override
    public int getItemCount() {
        return listResep.size();
    }

    public class ResepViewHolder extends RecyclerView.ViewHolder {

        private TextView cardResep_textView_nama,cardResep_TextView_jumlah;
        private ImageView cardResep_image_item;

        public ResepViewHolder(@NonNull View itemView) {
            super(itemView);

            cardResep_textView_nama = itemView.findViewById(R.id.cardResep_textView_nama);
            cardResep_TextView_jumlah = itemView.findViewById(R.id.cardResep_TextView_jumlah);
            cardResep_image_item = itemView.findViewById(R.id.cardResep_image_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.onCardClick(getAdapterPosition());
                }
            });
        }
    }
}
