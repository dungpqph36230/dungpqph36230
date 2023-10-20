package com.example.myapplication.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.Sach;
import com.example.myapplication.Model.Top;
import com.example.myapplication.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context=context;
        DBHelper taoDataBase=new DBHelper(context);
        db=taoDataBase.getWritableDatabase();
    }
    //Thống kê top 10
    public List<Top> getTop(){
        String sqlTop= "SELECT maSach, count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top> list=new ArrayList<>();
        SachDAO sachDAO=new SachDAO(context);
        Cursor c=db.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            Top top=new Top();
            Sach sach=sachDAO.getID(c.getString(c.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return  list;
    }
    //thống kê doanh thu
    public int getDoanhThu(String tuNgay,String denNgay){
        String splDoanhThu= "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list=new ArrayList<>();
        Cursor c=db.rawQuery(splDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            } catch (Exception e){
                list.add(0);
            }
        }
        return  list.get(0);
    }
}
