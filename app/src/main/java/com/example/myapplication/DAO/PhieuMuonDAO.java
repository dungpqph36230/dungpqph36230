package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.PhieuMuon;
import com.example.myapplication.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;

    public PhieuMuonDAO(Context context) {
        DBHelper taoDataBase = new DBHelper(context);
        db = taoDataBase.getWritableDatabase();
    }

    public long insert(PhieuMuon x) {
        ContentValues values = new ContentValues();
        values.put("maTT", x.getMaTT());
        values.put("maTV", x.getMaTv());
        values.put("maSach", x.getMaSach());
        values.put("tienThue", x.getTienThue());
        values.put("ngay", x.getNgay());
        values.put("gio", x.getGio());
        values.put("traSach", x.getTraSach());
        return db.insert("PhieuMuon", null, values);
    }

    public int update(PhieuMuon x) {
        ContentValues values = new ContentValues();
        values.put("maTT", x.getMaTT());
        values.put("maTV", x.getMaTv());
        values.put("maSach", x.getMaSach());
        values.put("tienThue", x.getTienThue());
        values.put("ngay", x.getNgay());
        values.put("traSach", x.getTraSach());
        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(x.getMaPM())});
    }

    public int delete(String id) {
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    public List<PhieuMuon> getAll() {
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    private List<PhieuMuon> getData(String sql, String... selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            PhieuMuon x = new PhieuMuon();
            x.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            x.setMaTT(c.getString(c.getColumnIndex("maTT")));
            x.setMaTv(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            x.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            x.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            x.setNgay(c.getString(c.getColumnIndex("ngay")));
            x.setGio(c.getString(c.getColumnIndex("gio")));
            x.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(x);
        }
        c.close();
        return list;
    }

    public PhieuMuon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> lis = getData(sql, id);
        if (!lis.isEmpty()) {
            return lis.get(0);
        } else {
            return null; // Trả về null nếu không tìm thấy dữ liệu phù hợp
        }
    }

    public int updateMaThanhVienNull(String id) {
        ContentValues values = new ContentValues();
        values.put("maTV", "-1");
        return db.update("ThanhVien", values, "maTV = ?", new String[]{id});
    }
    public int updateMaSachNull(String id) {
        ContentValues values = new ContentValues();
        values.put("maSach", "-1");
        return db.update("Sach", values, "maSach = ?", new String[]{id});
    }
}
