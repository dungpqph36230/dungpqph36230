package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static  final String dbName="PNLIB";
    static  final  int dbVersion=3;
    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo bảng thu thu
        String createTableThuThu=
                "create table ThuThu (" +
                        "maTT TEXT PRIMARY KEY, " +
                        "hoTen TEXT NOT NULL, " +
                        "matKhau TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableThuThu);

        //Tạo bảng Thanh vien
        String createTableThanhVien=
                "create table ThanhVien (" +
                        "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "hoTen TEXT NOT NULL, " +
                        "namSinh TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableThanhVien);

        //Tạo bảng Loại sách
        String createTableLoaiSach=
                "create table LoaiSach (" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenLoai TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableLoaiSach);
        //Tạo bảng  sách
        String createTableSach=
                "create table Sach (" +
                        "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenSach TEXT NOT NULL, " +
                        "giaThue INTEGER NOT NULL, " +
                        "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        sqLiteDatabase.execSQL(createTableSach);

        String createTablePhieuMuon=
                "create table PhieuMuon (" +
                        "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "maTT TEXT REFERENCES ThuThu(maTT), " +
                        "maTV INTEGER REFERENCES ThanhVien(maTv), " +
                        "maSach INTEGER REFERENCES Sach(maSach), " +
                        "tienThue INTEGER NOT NULL ," +
                        "ngay DATE NOT NULL ," +
                        "gio TIME NOT NULL ," +
                        "traSach INTEGER NOT NULL )";
        sqLiteDatabase.execSQL(createTablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        sqLiteDatabase.execSQL(dropTableLoaiThuThu);

        String dropTableThanhVien = "drop table if exists ThanhVien";
        sqLiteDatabase.execSQL(dropTableThanhVien);

        String dropTableLoaiSach = "drop table if exists LoaiSach";
        sqLiteDatabase.execSQL(dropTableLoaiSach);

        String dropTableSachSach = "drop table if exists SachSach";
        sqLiteDatabase.execSQL(dropTableSachSach);

        String dropTableLoaiPhieuMuon = "drop table if exists PhieuMuon";
        sqLiteDatabase.execSQL(dropTableLoaiPhieuMuon);

        onCreate(sqLiteDatabase);
    }
}
