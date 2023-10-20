package com.example.myapplication.adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.LoaiSach;
import com.example.myapplication.R;

import java.util.List;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    List<LoaiSach> list;
    Context context;
    public LoaiSachSpinnerAdapter(@NonNull Context context, List<LoaiSach> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.loai_sach_item, parent, false);
        }


        // Lấy đối tượng LoaiSach tại vị trí position trong danh sách
        LoaiSach loaiSach = getItem(position);

        // Lấy các View trong layout loai_sach_item
        TextView textViewMaLoai = convertView.findViewById(R.id.tv_MaLoai);
        TextView textViewTenLoai = convertView.findViewById(R.id.tv_TenLoai);
        ImageView img_xoa=convertView.findViewById(R.id.imXoaLoai);
        img_xoa.setVisibility(View.GONE);

        // Đặt dữ liệu từ đối tượng LoaiSach vào các TextView
        textViewMaLoai.setText(String.valueOf(loaiSach.getMaLoai()));
        textViewTenLoai.setText(loaiSach.getTenLoai());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.loai_sach_item, parent, false);
        }

        // Lấy đối tượng LoaiSach tại vị trí position trong danh sách
        LoaiSach loaiSach = getItem(position);

        // Lấy các View trong layout loai_sach_item
        TextView textViewMaLoai = convertView.findViewById(R.id.tv_MaLoai);
        TextView textViewTenLoai = convertView.findViewById(R.id.tv_TenLoai);
        ImageView img_xoa=convertView.findViewById(R.id.imXoaLoai);
        img_xoa.setVisibility(View.GONE);

        // Đặt dữ liệu từ đối tượng LoaiSach vào các TextView
        textViewMaLoai.setText(String.valueOf(loaiSach.getMaLoai()));
        textViewTenLoai.setText(loaiSach.getTenLoai());

        return convertView;
    }
}
