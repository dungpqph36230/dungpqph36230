package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.ThanhVien;
import com.example.myapplication.R;

import java.util.List;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    List<ThanhVien> list;
    Context context;
    public ThanhVienSpinnerAdapter(@NonNull Context context, List<ThanhVien> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.thanh_vien_item, parent, false);
        }
        ThanhVien thanhVien = getItem(position);


        TextView textViewMaThanhVien = convertView.findViewById(R.id.tvMaTV);
        TextView textViewTenThanhVien = convertView.findViewById(R.id.tvTenTV);
        TextView tvNamSinh=convertView.findViewById(R.id.tvNamSinh);
        ImageView img_xoa=convertView.findViewById(R.id.imgDeleteLS);
        img_xoa.setVisibility(View.GONE);

        textViewMaThanhVien.setText(String.valueOf(thanhVien.getMaTv()));
        textViewTenThanhVien.setText(thanhVien.getHoten());
        tvNamSinh.setText(String.valueOf(thanhVien.getNamSinh()));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.thanh_vien_item, parent, false);
        }


        ThanhVien thanhVien = getItem(position);


        TextView textViewMaThanhVien = convertView.findViewById(R.id.tvMaTV);
        TextView textViewTenThanhVien = convertView.findViewById(R.id.tvTenTV);
        TextView tvNamSinh=convertView.findViewById(R.id.tvNamSinh);
        ImageView img_xoa=convertView.findViewById(R.id.imgDeleteLS);
        img_xoa.setVisibility(View.GONE);

        textViewMaThanhVien.setText(String.valueOf(thanhVien.getMaTv()));
        textViewTenThanhVien.setText(thanhVien.getHoten());
        tvNamSinh.setText(String.valueOf(thanhVien.getNamSinh()));
        return convertView;
    }
}
