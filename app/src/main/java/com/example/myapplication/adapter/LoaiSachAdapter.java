package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.LoaiSach;
import com.example.myapplication.R;
import com.example.myapplication.fragment.LoaiSachFragment;

import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.viewHolder> {
    List<LoaiSach> ds_LoaiSach;
    Context context;
    LoaiSachFragment loaiSachFragment;

    public interface onclick {
        void onItemClick(LoaiSach x);
    }

    onclick onclicks;
    public void  setonclick(onclick x){
        this.onclicks=x;
    }

    public LoaiSachAdapter(List<LoaiSach> ds_LoaiSach, Context context, LoaiSachFragment x) {
        this.ds_LoaiSach = ds_LoaiSach;
        this.context = context;
        this.loaiSachFragment = x;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loai_sach_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        LoaiSach x = ds_LoaiSach.get(position);
        holder.tvMaLoai.setText("mã :"+String.valueOf(x.getMaLoai()));
        holder.tvTenLoai.setText("tên:"+x.getTenLoai());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiSachFragment.xoa(x);
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
        return ds_LoaiSach.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaLoai, tvTenLoai;
        private ImageView btnRemove;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaLoai = itemView.findViewById(R.id.tv_MaLoai);
            tvTenLoai = itemView.findViewById(R.id.tv_TenLoai);
            btnRemove = itemView.findViewById(R.id.imXoaLoai);
        }
    }
}
