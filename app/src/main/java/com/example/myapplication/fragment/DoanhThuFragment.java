package com.example.myapplication.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DAO.ThongKeDAO;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DoanhThuFragment extends Fragment {
    Button btnTuNgay,btnDenNgay,btnDoanhThu;
    EditText edTuNgay,edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    int mYear,mMoth,mDay;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnTuNgay=view.findViewById(R.id.btnTuNgay);
        btnDenNgay=view.findViewById(R.id.btnDenNgay);
        btnDoanhThu=view.findViewById(R.id.btnDoanhThu);
        edTuNgay=view.findViewById(R.id.edTuNgay);
        edDenNgay=view.findViewById(R.id.edDenNgay);
        tvDoanhThu=view.findViewById(R.id.tv_DoanhThu);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(edTuNgay);
            }
        });

        // Xử lý sự kiện khi người dùng nhấn vào nút "Đến Ngày"
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(edDenNgay);
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay=edTuNgay.getText().toString();
                String denNgay=edDenNgay.getText().toString();
                ThongKeDAO thongKeDAO=new ThongKeDAO(getActivity());
                tvDoanhThu.setText("Doanh Thu:"+thongKeDAO.getDoanhThu(tuNgay,denNgay));
            }
        });
    }
    private void showDatePickerDialog(final EditText editText) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMoth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Hiển thị DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý khi người dùng chọn ngày
                        c.set(Calendar.YEAR, year);
                        c.set(Calendar.MONTH, monthOfYear);
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Định dạng ngày và hiển thị trong EditText
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        editText.setText(sdf.format(c.getTime()));
                    }
                }, mYear, mMoth, mDay);
        datePickerDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }
}
