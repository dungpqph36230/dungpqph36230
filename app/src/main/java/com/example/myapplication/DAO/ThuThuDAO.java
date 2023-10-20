package com.example.myapplication.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.ThuThu;
import com.example.myapplication.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DBHelper taoDataBase = new DBHelper(context);
        db = taoDataBase.getWritableDatabase();
    }

    public long insert(ThuThu x) {
        ContentValues values = new ContentValues();
        values.put("maTT", x.getMaTT());
        values.put("hoTen", x.getHoTen());
        values.put("matKhau", x.getMatKhau());
        return db.insert("ThuThu", null, values);
    }

    public int updatePass(ThuThu x) {
        ContentValues values = new ContentValues();
        values.put("hoTen", x.getHoTen());
        values.put("matKhau", x.getMatKhau());
        return db.update("ThuThu", values, "maTT=?", new String[]{x.getMaTT()});
    }

    public int delete(String id) {
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    public List<ThuThu> getAll() {
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String... selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThuThu x = new ThuThu();
            x.setMaTT(c.getString(c.getColumnIndex("maTT")));
            x.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            x.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            list.add(x);
        }
        c.close();
        return list;
    }

    public ThuThu getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> lis = getData(sql, id);
        if (!lis.isEmpty()) {
            return lis.get(0);
        } else {
            return null; // Trả về null nếu không tìm thấy dữ liệu phù hợp
        }
    }
    //check login
    public int checkLogin(String id,String password){
        String sql= "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list=getData(sql,id,password);
        if(list.size()==0)
            return -1;
        return 1;
    }
}
