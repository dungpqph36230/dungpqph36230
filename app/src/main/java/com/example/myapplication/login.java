package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DAO.ThuThuDAO;

public class login extends AppCompatActivity {
    EditText edUserName,edPassword;
    Button btnLogin,btnCancel;
    CheckBox chkRememberPass;
    String strUser,strPass;
    ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng nhập");
        edUserName=findViewById(R.id.edUserName);
        edPassword=findViewById(R.id.edPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnCancel=findViewById(R.id.btnCancel);
        chkRememberPass=findViewById(R.id.chkRememberPass);
        thuThuDAO=new ThuThuDAO(this);
        SharedPreferences sharedPreferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(sharedPreferences.getString("USERNAME",""));
        edPassword.setText(sharedPreferences.getString("PASSWORD",""));
        chkRememberPass.setChecked(sharedPreferences.getBoolean("REMEMBER",false));
        btnCancel.setOnClickListener((v)->{
            edUserName.setText("");
            edPassword.setText("");
        });
        btnLogin.setOnClickListener((v)->{
            checkLogin();
        });
    }
    public void checkLogin(){
        strUser=edUserName.getText().toString();
        strPass=edPassword.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(this, "Không để trống tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            if(thuThuDAO.checkLogin(strUser,strPass)>0||(strUser.equals("admin") && strPass.equals("admin"))){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void rememberUser(String strUser, String strPass, boolean checked) {
        SharedPreferences x=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=x.edit();
        if(!checked){
            editor.clear();
        } else {
            editor.putString("USERNAME",strUser);
            editor.putString("PASSWORD",strPass);
            editor.putBoolean("REMEMBER",checked);
        }
        editor.commit();
    }
}