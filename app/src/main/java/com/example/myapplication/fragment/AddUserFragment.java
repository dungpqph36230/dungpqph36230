package com.example.myapplication.fragment;

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

public class AddUserFragment extends Fragment {
    EditText edUser,edHoTen,edPass,edRePass;
    Button btnSave,btnCancel;
    ThuThuDAO thuThuDAO;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edUser=view.findViewById(R.id.edUser);
        edHoTen=view.findViewById(R.id.edHoTen);
        edPass=view.findViewById(R.id.edPass);
        edRePass=view.findViewById(R.id.edRePass);
        btnSave=view.findViewById(R.id.btnSaveUser);
        btnCancel=view.findViewById(R.id.btnCancelUser);
        thuThuDAO=new ThuThuDAO(getActivity());
        btnCancel.setOnClickListener((v)->{
            edUser.setText("");
            edHoTen.setText("");
            edPass.setText("");
            edRePass.setText("");
        });
        btnSave.setOnClickListener((v->{
            ThuThu thuThu=new ThuThu();
            thuThu.setMaTT(edUser.getText().toString());
            thuThu.setHoTen(edHoTen.getText().toString());
            thuThu.setMatKhau(edPass.getText().toString());
            if(validate()>0){
                if(thuThuDAO.insert(thuThu)>0){
                    Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    edUser.setText("");
                    edHoTen.setText("");
                    edPass.setText("");
                    edRePass.setText("");
                } else {
                    Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();

                }
            }
        }));
    }
    public  int validate(){
        int check=1;
        if(edUser.getText().length()==0||edHoTen.getText().length()==0||edPass.getText().length()==0
                ||edRePass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        } else {
            String pass=edPass.getText().toString();
            String rePass=edRePass.getText().toString();
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
        return  inflater.inflate(R.layout.fragment_add_user, container, false);
    }
}
