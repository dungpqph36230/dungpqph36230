package com.example.myapplication.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DAO.ThuThuDAO;
import com.example.myapplication.Model.ThuThu;
import com.example.myapplication.R;

public class ChangePassFragment extends Fragment {
    EditText edPassOld,edPass,edRePass;
    Button btnSave,btnCancel;
    ThuThuDAO thuThuDAO;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edPassOld=view.findViewById(R.id.edPassOld);
        edPass=view.findViewById(R.id.edPassChange);
        edRePass=view.findViewById(R.id.edRePassChage);
        btnSave=view.findViewById(R.id.btnSaveUserChange);
        btnCancel=view.findViewById(R.id.btnCancelUserChange);
        thuThuDAO=new ThuThuDAO(getActivity());
        btnCancel.setOnClickListener((v)->{
            edPassOld.setText("");
            edPass.setText("");
            edRePass.setText("");
        });
        btnSave.setOnClickListener((v)->{
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String user=sharedPreferences.getString("USERNAME","");
            if(validate()>0){
                ThuThu thuThu=thuThuDAO.getID(user);
                thuThu.setMatKhau(edPass.getText().toString());
                thuThuDAO.updatePass(thuThu);
                if(thuThuDAO.updatePass(thuThu)>0){
                    Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    edPassOld.setText("");
                    edPass.setText("");
                    edRePass.setText("");
                } else {
                    Toast.makeText(getContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    public  int validate(){
        int check=1;
        String mkCu,mk,nhapLaiMk;
        mkCu=edPassOld.getText().toString();
        mk=edPass.getText().toString();
        nhapLaiMk=edRePass.getText().toString();
        if(mkCu.trim().isEmpty()||mk.trim().isEmpty()||nhapLaiMk.trim().isEmpty()){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        } else {
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld=sharedPreferences.getString("PASSWORD","");
            String pass=edPass.getText().toString();
            String rePass=edRePass.getText().toString();
            if(!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if(!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp ", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return  check;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_pass, container, false);
    }
}
