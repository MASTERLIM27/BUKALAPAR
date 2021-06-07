package com.example.barcode.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Bahan implements Parcelable {

    private int id;
    private String nama;
    private int jumlah;

    public Bahan() {
        this.id = 0;
        this.nama = "";
        this.jumlah = 0;
    }

    public Bahan(String nama, int jumlah) {
        this.id = 0;
        this.nama = nama;
        this.jumlah = jumlah;
    }

    public Bahan(int id, String nama, int jumlah) {
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
    }

    protected Bahan(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        jumlah = in.readInt();
    }

    public static final Creator<Bahan> CREATOR = new Creator<Bahan>() {
        @Override
        public Bahan createFromParcel(Parcel in) {
            return new Bahan(in);
        }

        @Override
        public Bahan[] newArray(int size) {
            return new Bahan[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeInt(jumlah);
    }
}
