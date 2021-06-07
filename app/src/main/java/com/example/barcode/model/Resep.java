package com.example.barcode.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Resep implements Parcelable {

    private int id;
    private String nama, image_path, created;
    private int jumlah;

    public Resep() {
        this.id = 0;
        this.nama = "";
        this.image_path = "";
        this.created = "";
        this.jumlah = 0;
    }

    public Resep(String nama, int jumlah) {
        this.id = 0;
        this.nama = nama;
        this.image_path = "";
        this.created = "";
        this.jumlah = jumlah;
    }

    public Resep(int id, String nama, String image_path, String created, int jumlah) {
        this.id = id;
        this.nama = nama;
        this.image_path = image_path;
        this.created = created;
        this.jumlah = jumlah;
    }

    protected Resep(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        image_path = in.readString();
        created = in.readString();
        jumlah = in.readInt();
    }

    public static final Creator<Resep> CREATOR = new Creator<Resep>() {
        @Override
        public Resep createFromParcel(Parcel in) {
            return new Resep(in);
        }

        @Override
        public Resep[] newArray(int size) {
            return new Resep[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
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
        dest.writeString(image_path);
        dest.writeString(created);
        dest.writeInt(jumlah);
    }
}