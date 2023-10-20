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

import com.example.myapplication.Model.Sach;
import com.example.myapplication.R;

import java.util.List;

public class sachSpinnerAdapter extends ArrayAdapter<Sach> {
    List<Sach> list;
    Context context;
    public sachSpinnerAdapter(@NonNull Context context, List<Sach> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.sach_item, parent, false);
        }



        Sach sach = getItem(position);


        TextView textViewMaSach = convertView.findViewById(R.id.tv_MaSach);
        TextView textViewTenSach = convertView.findViewById(R.id.tv_TenSach);
        TextView textViewGiaSach= convertView.findViewById(R.id.tv_GiaSach);
        TextView textViewSachThuocLoai = convertView.findViewById(R.id.tv_SachThuocLoai);
        ImageView imXoa=convertView.findViewById(R.id.imXoaSach);

        imXoa.setVisibility(View.GONE);
        textViewGiaSach.setVisibility(View.GONE);
        textViewSachThuocLoai.setVisibility(View.GONE);
        textViewMaSach.setText(String.valueOf(sach.getMaSach()));
        textViewTenSach.setText(sach.getTenSach());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.sach_item, parent, false);
        }

        Sach sach = getItem(position);

        TextView textViewMaSach = convertView.findViewById(R.id.tv_MaSach);
        TextView textViewTenSach = convertView.findViewById(R.id.tv_TenSach);
        TextView textViewGiaSach= convertView.findViewById(R.id.tv_GiaSach);
        TextView textViewSachThuocLoai = convertView.findViewById(R.id.tv_SachThuocLoai);
        ImageView imXoa=convertView.findViewById(R.id.imXoaSach);

        imXoa.setVisibility(View.GONE);
        textViewGiaSach.setVisibility(View.GONE);
        textViewSachThuocLoai.setVisibility(View.GONE);

        textViewMaSach.setText(String.valueOf(sach.getMaSach()));
        textViewTenSach.setText(sach.getTenSach());

        return convertView;
    }
}
