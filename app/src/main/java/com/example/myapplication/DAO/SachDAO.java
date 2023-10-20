package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.Sach;
import com.example.myapplication.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DBHelper taoDataBase = new DBHelper(context);
        db = taoDataBase.getWritableDatabase();
    }

    public long insert(Sach x) {
        ContentValues values = new ContentValues();
        values.put("tenSach", x.getTenSach());
        values.put("giaThue", x.getGiaThue());
        values.put("maLoai", x.getMaLoai());
        return db.insert("Sach", null, values);
    }

    public int update(Sach x) {
        ContentValues values = new ContentValues();
        values.put("tenSach", x.getTenSach());
        values.put("giaThue", x.getGiaThue());
        values.put("maLoai", x.getMaLoai());
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(x.getMaSach())});
    }

    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    public List<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    private List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Sach x = new Sach();
            x.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            x.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            x.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            x.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(x);
        }
        c.close();
        return list;
    }

    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> lis = getData(sql, id);
        if (!lis.isEmpty()) {
            return lis.get(0);
        } else {
            return null; // Trả về null nếu không tìm thấy dữ liệu phù hợp
        }
    }

    public int updateMaLoaiToNull(String id) {
        ContentValues values = new ContentValues();
        values.put("maLoai", "-1");
        return db.update("Sach", values, "maLoai = ?", new String[]{id});
    }
}
