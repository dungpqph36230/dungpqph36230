package com.example.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Sach;
import com.example.myapplication.R;
import com.example.myapplication.fragment.SachFragment;

import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.viewHolder>{
    List<Sach> ds_sach;
    Context context;
    SachFragment sachFragment;
    public interface onclick {
        void onItemClick(Sach x);
    }

    onclick onclicks;
    public void  setonclick(onclick x){
        this.onclicks=x;
    }

    public SachAdapter(List<Sach> ds_sach, Context context,SachFragment x) {
        this.ds_sach = ds_sach;
        this.context = context;
        this.sachFragment=x;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sach_item, parent, false);
        return new SachAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Sach x=ds_sach.get(position);
        holder.tvMaSach.setText("mã:"+String.valueOf(x.getMaSach()));
        holder.tvTenSach.setText("tên:"+x.getTenSach());
        holder.tvGiaSach.setText(String.valueOf("giá:"+x.getGiaThue()));
        String maLoaiText = (x.getMaLoai() == -1) ? "Trống" : String.valueOf(x.getMaLoai());
        holder.tvMaLoai.setText("Mã loại: " + maLoaiText);
        if(x.getMaLoai()==-1){
            holder.tvMaLoai.setText("mã loại:Trống");
        } else {
            holder.tvMaLoai.setText(String.valueOf("mã loại:"+x.getMaLoai()));
        }
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sachFragment.xoa(x);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclicks.onItemClick(x);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ds_sach.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaSach,tvMaLoai, tvTenSach,tvGiaSach;
        private ImageView btnRemove;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaSach=itemView.findViewById(R.id.tv_MaSach);
            tvMaLoai = itemView.findViewById(R.id.tv_SachThuocLoai);
            tvTenSach = itemView.findViewById(R.id.tv_TenSach);
            tvGiaSach=itemView.findViewById(R.id.tv_GiaSach);
            btnRemove = itemView.findViewById(R.id.imXoaSach);
        }
    }
}
