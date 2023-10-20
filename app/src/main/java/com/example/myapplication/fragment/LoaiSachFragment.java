package com.example.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.LoaiSachDAO;
import com.example.myapplication.DAO.SachDAO;
import com.example.myapplication.Model.LoaiSach;
import com.example.myapplication.R;
import com.example.myapplication.adapter.LoaiSachAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LoaiSachFragment extends Fragment {
    RecyclerView rec_LoaiSach;
    FloatingActionButton fab_ThemLoai;
    List<LoaiSach> ds_loai;
    LoaiSachDAO loaiSachDAO;
    SachDAO sachDAO;
    Dialog dialog;
    LoaiSachAdapter loaiSachAdapter;
    EditText edTenLoai ;
    Button btnCancel;
    Button  btnSave ;
    LoaiSach loaiSach;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rec_LoaiSach=view.findViewById(R.id.rec_LoaiSanPham);
        fab_ThemLoai=view.findViewById(R.id.fab_ThemLoai);
        loaiSachDAO=new LoaiSachDAO(getActivity());
        sachDAO=new SachDAO(getActivity());
        CapNhapDanhSachLoai();
        fab_ThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
    }
    void CapNhapDanhSachLoai(){
        ds_loai=loaiSachDAO.getAll();
        loaiSachAdapter=new LoaiSachAdapter(ds_loai,getActivity(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rec_LoaiSach.setLayoutManager(linearLayoutManager);
        rec_LoaiSach.setAdapter(loaiSachAdapter);
        loaiSachAdapter.setonclick(new LoaiSachAdapter.onclick() {
            @Override
            public void onItemClick(LoaiSach x) {
                loaiSach=x;
                openDialog(getActivity(),1);
            }
        });
    }
    protected void openDialog(final Context context, final int tyte) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        TextView tvMa = dialog.findViewById(R.id.tv_ThemMaLoai);
        edTenLoai = dialog.findViewById(R.id.edThemTenLoai);
        btnCancel = dialog.findViewById(R.id.btnCacelLoai);
        btnSave = dialog.findViewById(R.id.btnSaveLoai);
        //kiểm tra tyte insert 0 hya update 1
        if (tyte != 0) {
            tvMa.setText(String.valueOf(loaiSach.getMaLoai()));
            edTenLoai.setText(loaiSach.getTenLoai());
        }
        btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach loaiSach=new LoaiSach();
                loaiSach.setTenLoai(edTenLoai.getText().toString());
                if(validate()>0){
                    if(tyte==0){
                        if(loaiSachDAO.insert(loaiSach)>0){
                            Toast.makeText(context, "thêm thành công", Toast.LENGTH_SHORT).show();
                            ds_loai.add(loaiSach);
                            Log.d("id",String.valueOf(loaiSach.getMaLoai()));
                            CapNhapDanhSachLoai();

                        } else {
                            Toast.makeText(context, "thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        loaiSach.setMaLoai(Integer.parseInt(tvMa.getText().toString()));
                        if(loaiSachDAO.update(loaiSach)>0){
                            int vitri=-1;
                            for (int i=0; i<ds_loai.size();i++){
                                if(ds_loai.get(i).getMaLoai()==loaiSach.getMaLoai()){
                                    vitri=i;
                                }
                            }
                            if(vitri != -1){
                                ds_loai.set(vitri,loaiSach);
                                loaiSachAdapter.notifyDataSetChanged();
                            }
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            loaiSachAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate() {
        int check = 1;
        String TenLoai=edTenLoai.getText().toString();
        if(TenLoai !=null){
            if (TenLoai.trim().isEmpty()) {
                Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                check = -1;
            }

        }
        return check;
    }



    public void xoa(LoaiSach x) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá hay không?");
        builder.setCancelable(true);
        builder.setPositiveButton("yes", (dialog, id) -> {
            if(x!=null){
                if(loaiSachDAO.delete(String.valueOf(x.getMaLoai()))>0){
                    ds_loai.remove(x);
                    sachDAO.updateMaLoaiToNull(String.valueOf(x.getMaLoai()));
                    loaiSachAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Xóa thành công ", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else {
                    Toast.makeText(getContext(), "Xóa thất bại ", Toast.LENGTH_SHORT).show();
                }
            }

        });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_sach, container, false);
    }
}
