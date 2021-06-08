package com.example.barcode.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Resep implements Parcelable {

    private int id, jumlah;
    private String nama,instruksi, image_path, bahan_1, bahan_2,bahan_3,bahan_4,bahan_5;
    private int jumlah_bahan_1,jumlah_bahan_2,jumlah_bahan_3,jumlah_bahan_4,jumlah_bahan_5;

//    public Resep() {
//        this.id = 0;
//        this.nama = "";
//        this.jumlah = 0;
//    }

    public Resep(String nama, int jumlah) {
        this.id = 0;
        this.nama = nama;
        this.jumlah = jumlah;
    }

    public Resep() {
        this.id = 0;
        this.nama = "";
        this.image_path = "";
        this.bahan_1 = "";
        this.bahan_2 = "";
        this.bahan_3 = "";
        this.bahan_4 = "";
        this.bahan_5 = "";
        this.jumlah_bahan_1 = 0;
        this.jumlah_bahan_2 = 0;
        this.jumlah_bahan_3 = 0;
        this.jumlah_bahan_4 = 0;
        this.jumlah_bahan_5 = 0;
        this.instruksi = "";
    }

    public Resep(int id, String nama, String image_path, String bahan_1, String bahan_2, String bahan_3, String bahan_4, String bahan_5, int jumlah_bahan_1, int jumlah_bahan_2, int jumlah_bahan_3, int jumlah_bahan_4, int jumlah_bahan_5, String instruksi) {
        this.id = id;
        this.nama = nama;
        this.image_path = image_path;
        this.bahan_1 = bahan_1;
        this.bahan_2 = bahan_2;
        this.bahan_3 = bahan_3;
        this.bahan_4 = bahan_4;
        this.bahan_5 = bahan_5;
        this.jumlah_bahan_1 = jumlah_bahan_1;
        this.jumlah_bahan_2 = jumlah_bahan_2;
        this.jumlah_bahan_3 = jumlah_bahan_3;
        this.jumlah_bahan_4 = jumlah_bahan_4;
        this.jumlah_bahan_5 = jumlah_bahan_5;
        this.instruksi = instruksi;
    }

    protected Resep(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        image_path = in.readString();
        bahan_1 = in.readString();
        bahan_2 = in.readString();
        bahan_3 = in.readString();
        bahan_4 = in.readString();
        bahan_5 = in.readString();
        jumlah_bahan_1 = in.readInt();
        jumlah_bahan_2 = in.readInt();
        jumlah_bahan_3 = in.readInt();
        jumlah_bahan_4 = in.readInt();
        jumlah_bahan_5 = in.readInt();
        instruksi = in.readString();
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

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getInstruksi() {
        return instruksi;
    }

    public void setInstruksi(String instruksi) {
        this.instruksi = instruksi;
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

    public String getBahan_1() {
        return bahan_1;
    }

    public void setBahan_1(String bahan_1) {
        this.bahan_1 = bahan_1;
    }

    public String getBahan_2() {
        return bahan_2;
    }

    public void setBahan_2(String bahan_2) {
        this.bahan_2 = bahan_2;
    }

    public String getBahan_3() {
        return bahan_3;
    }

    public void setBahan_3(String bahan_3) {
        this.bahan_3 = bahan_3;
    }

    public String getBahan_4() {
        return bahan_4;
    }

    public void setBahan_4(String bahan_4) {
        this.bahan_4 = bahan_4;
    }

    public String getBahan_5() {
        return bahan_5;
    }

    public void setBahan_5(String bahan_5) {
        this.bahan_5 = bahan_5;
    }

    public int getJumlah_bahan_1() {
        return jumlah_bahan_1;
    }

    public void setJumlah_bahan_1(int jumlah_bahan_1) {
        this.jumlah_bahan_1 = jumlah_bahan_1;
    }

    public int getJumlah_bahan_2() {
        return jumlah_bahan_2;
    }

    public void setJumlah_bahan_2(int jumlah_bahan_2) {
        this.jumlah_bahan_2 = jumlah_bahan_2;
    }

    public int getJumlah_bahan_3() {
        return jumlah_bahan_3;
    }

    public void setJumlah_bahan_3(int jumlah_bahan_3) {
        this.jumlah_bahan_3 = jumlah_bahan_3;
    }

    public int getJumlah_bahan_4() {
        return jumlah_bahan_4;
    }

    public void setJumlah_bahan_4(int jumlah_bahan_4) {
        this.jumlah_bahan_4 = jumlah_bahan_4;
    }

    public int getJumlah_bahan_5() {
        return jumlah_bahan_5;
    }

    public void setJumlah_bahan_5(int jumlah_bahan_5) {
        this.jumlah_bahan_5 = jumlah_bahan_5;
    }

    public static Creator<Resep> getCREATOR() {
        return CREATOR;
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
        dest.writeString(bahan_1);
        dest.writeString(bahan_2);
        dest.writeString(bahan_3);
        dest.writeString(bahan_4);
        dest.writeString(bahan_5);
        dest.writeInt(jumlah_bahan_1);
        dest.writeInt(jumlah_bahan_2);
        dest.writeInt(jumlah_bahan_3);
        dest.writeInt(jumlah_bahan_4);
        dest.writeInt(jumlah_bahan_5);
        dest.writeString(instruksi);
    }
}
