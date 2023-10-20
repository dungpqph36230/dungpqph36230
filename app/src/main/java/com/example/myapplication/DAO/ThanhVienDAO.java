package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.ThanhVien;
import com.example.myapplication.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DBHelper taoDataBase = new DBHelper(context);
        db = taoDataBase.getWritableDatabase();
    }

    public long insert(ThanhVien x) {
        ContentValues values = new ContentValues();
        values.put("hoTen", x.getHoten());
        values.put("namSinh", x.getNamSinh());
        return db.insert("ThanhVien", null, values);
    }

    public int update(ThanhVien x) {
        ContentValues values = new ContentValues();
        values.put("hoTen", x.getHoten());
        values.put("namSinh", x.getNamSinh());
        return db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(x.getMaTv())});
    }

    public int delete(String id) {
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }

    public List<ThanhVien> getAll(){
        String sql="SELECT * FROM ThanhVien";
        return getData(sql);
    }
    private List<ThanhVien> getData(String sql, String...selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThanhVien x = new ThanhVien();
            x.setMaTv(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            x.setHoten(c.getString(c.getColumnIndex("hoTen")));
            x.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            list.add(x);
        }
        c.close();
        return list;
    }
    public  ThanhVien getID(String id){
        String sql="SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> lis=getData(sql,id);
        if (!lis.isEmpty()&&lis != null) {
            return lis.get(0);
        } else {
            return null;
        }
    }
}
