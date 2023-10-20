package com.example.myapplication.Model;

public class LoaiSach {
    int maLoai;
    String TenLoai;

    public LoaiSach() {
    }

    public LoaiSach(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        TenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
