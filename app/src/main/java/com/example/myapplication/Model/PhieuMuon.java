package com.example.myapplication.Model;

import java.io.Serializable;

public class PhieuMuon implements Serializable {
    int maPM;
    String maTT;
    int maTv;
    int maSach;
    int tienThue;
    int traSach;
    String ngay;
    String gio;

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public PhieuMuon(int maPM, String maTT, int maTv, int maSach, int tienThue, int traSach, String ngay, String gio) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTv = maTv;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.ngay = ngay;
        this.gio = gio;
    }

    public PhieuMuon() {
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTv() {
        return maTv;
    }

    public void setMaTv(int maTv) {
        this.maTv = maTv;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
