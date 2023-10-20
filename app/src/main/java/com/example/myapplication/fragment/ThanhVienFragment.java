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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DAO.PhieuMuonDAO;
import com.example.myapplication.DAO.ThanhVienDAO;
import com.example.myapplication.Model.ThanhVien;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ThanhVienAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ThanhVienFragment extends Fragment {
    ListView lv;
    List<ThanhVien> ds_ThanhVien;
    FloatingActionButton fab;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    static ThanhVienDAO thanhVienDAO;
    PhieuMuonDAO phieuMuonDAO;
    ThanhVienAdapter thanhVienAdapter;
    ThanhVien item;
    Dialog dialog;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.lvThanhVien);
        fab = view.findViewById(R.id.fabThanhVien);
        thanhVienDAO = new ThanhVienDAO(getActivity());
        phieuMuonDAO=new PhieuMuonDAO(getActivity());
        capNhaplv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = ds_ThanhVien.get(i);
                openDialog(getActivity(), 1);
            }
        });
    }
    protected void openDialog(final Context context, final int tyte) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_thanh_vien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnCancel = dialog.findViewById(R.id.btnCacelTV);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        //kiểm tra tyte insert 0 hya update 1
        edMaTV.setEnabled(false);
        if (tyte != 0) {
            edMaTV.setText(String.valueOf(item.getMaTv()));
            edTenTV.setText(item.getHoten());
            edNamSinh.setText(item.getNamSinh());
        }
        btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new ThanhVien();
                item.setHoten(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                if (item != null) {
                    if (validate() > 0) {
                        if (tyte == 0) {
                            if (thanhVienDAO.insert(item) > 0) {
                                Toast.makeText(context, "thêm thành công", Toast.LENGTH_SHORT).show();
                                capNhaplv();
                                Log.d("size", String.valueOf(ds_ThanhVien.size()));
                            } else {
                                Toast.makeText(context, "thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            item.setMaTv(Integer.parseInt(edMaTV.getText().toString()));
                            if (thanhVienDAO.update(item) > 0) {
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                capNhaplv();
                            } else {
                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                        capNhaplv();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá hay không?");
        builder.setCancelable(true);
        builder.setPositiveButton("yes", (dialog, id) -> {
            thanhVienDAO.delete(Id);
            phieuMuonDAO.updateMaThanhVienNull(Id);
            dialog.cancel();
            capNhaplv();
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

    void capNhaplv() {
        ds_ThanhVien = thanhVienDAO.getAll();
        thanhVienAdapter = new ThanhVienAdapter(getActivity(), ds_ThanhVien, this);
        lv.setAdapter(thanhVienAdapter);
        thanhVienAdapter.notifyDataSetChanged();
    }

    public int validate() {
        int check = 1;
        String TenTv,NamSinhTv;
        TenTv=edTenTV.getText().toString();
        NamSinhTv=edNamSinh.getText().toString();
        if (TenTv.trim().isEmpty()|| NamSinhTv.trim().isEmpty()) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else if (!isNumeric(NamSinhTv)) {
            Toast.makeText(getContext(), "Năm sinh không hợp lệ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
    public  boolean isNumeric(String str) {
        try {
            // Sử dụng phương thức parseInt để cố gắng chuyển đổi chuỗi thành số nguyên
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            // Nếu có ngoại lệ NumberFormatException xảy ra, chuỗi không phải là số
            return false;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thanh_vien, container, false);
    }
}
