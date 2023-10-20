package com.example.myapplication.Model;

import java.io.Serializable;

public class ThanhVien implements Serializable {
    int maTv;
    String hoten;
    String namSinh;

    public ThanhVien(int maTv, String hoten, String namSinh) {
        this.maTv = maTv;
        this.hoten = hoten;
        this.namSinh = namSinh;
    }

    public ThanhVien() {
    }

    public int getMaTv() {
        return maTv;
    }

    public void setMaTv(int maTv) {
        this.maTv = maTv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
