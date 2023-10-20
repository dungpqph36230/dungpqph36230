package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.SachDAO;
import com.example.myapplication.DAO.ThanhVienDAO;
import com.example.myapplication.Model.PhieuMuon;
import com.example.myapplication.Model.Sach;
import com.example.myapplication.Model.ThanhVien;
import com.example.myapplication.R;
import com.example.myapplication.fragment.PhieuMuonFragment;

import java.util.List;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.viewHolder> {
    List<PhieuMuon> ds_PhieuMuon;
    Context context;
    PhieuMuonFragment phieuMuonFragment;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;

    public interface onclick {
        void onItemClick(PhieuMuon x);
    }

    onclick onclicks;
    public void  setonclick(onclick x){
        this.onclicks=x;
    }

    public PhieuMuonAdapter(List<PhieuMuon> ds_PhieuMuon, Context context, PhieuMuonFragment phieuMuonFragment) {
        this.ds_PhieuMuon = ds_PhieuMuon;
        this.context = context;
        this.phieuMuonFragment = phieuMuonFragment;
        this.thanhVienDAO=new ThanhVienDAO(context);
        this.sachDAO=new SachDAO(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phieu_muon_item, parent, false);
        return new PhieuMuonAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PhieuMuon phieuMuon=ds_PhieuMuon.get(position);
        sachDAO=new SachDAO(context);
        holder.tvMaPhieu.setText(String.valueOf(phieuMuon.getMaPM()));
        ThanhVien tv=thanhVienDAO.getID(String.valueOf(phieuMuon.getMaTv()));
        if(tv!=null){
            if(tv.getMaTv()==-1){
                holder.tvTenThanhVien.setText("trống");
            } else {
                holder.tvTenThanhVien.setText("tên:"+tv.getHoten());
            }
        } else {
            holder.tvTenThanhVien.setText("trống");
        }
        Sach sach=sachDAO.getID(String.valueOf(phieuMuon.getMaSach()));
        if(sach!=null){
            if(sach.getMaSach()==-1){
                holder.tvTenSach.setText("Trống");
                holder.tvGiaSach.setText("Trống");
            } else {
                holder.tvTenSach.setText("Tên sách:"+sach.getTenSach());
                holder.tvGiaSach.setText("Gia:"+phieuMuon.getTienThue());
            }
        } else {
            holder.tvTenSach.setText("Trống");
            holder.tvGiaSach.setText("Trống");
        }
        holder.tvNgay.setText("ngày:"+phieuMuon.getNgay());
        if(phieuMuon.getTraSach()==1){
            holder.tvTrangThai.setTextColor(Color.GREEN);
            holder.tvTrangThai.setText("Đã trả");
        } else {
            holder.tvTrangThai.setTextColor(Color.RED);
            holder.tvTrangThai.setText("Chưa chả");
        }
        holder.tvGio.setText("giờ:"+phieuMuon.getGio());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuonFragment.xoa(phieuMuon);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclicks.onItemClick(phieuMuon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds_PhieuMuon.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaPhieu,tvTenThanhVien, tvTenSach,tvGiaSach,tvNgay,tvTrangThai,tvGio;
        private ImageView btnRemove;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieu=itemView.findViewById(R.id.tv_MaPhieu);
            tvTenThanhVien = itemView.findViewById(R.id.tv_ThanhVienPM);
            tvTenSach = itemView.findViewById(R.id.tv_TenSachPM);
            tvGiaSach=itemView.findViewById(R.id.tv_GiaSachPM);
            tvNgay = itemView.findViewById(R.id.tv_Ngay);
            tvTrangThai=itemView.findViewById(R.id.tv_TrangThai);
            tvGio=itemView.findViewById(R.id.tv_Gio);
            btnRemove=itemView.findViewById(R.id.imXoaPhieu);
        }
    }
}
