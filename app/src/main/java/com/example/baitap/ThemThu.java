package com.example.baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.baitap.Other.DBManager;

import java.lang.reflect.Type;

public class ThemThu extends AppCompatActivity {
    private RadioButton rbThu,rbChi;
    private EditText edtName,edtPrice;
    private Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thu);
        rbChi = findViewById(R.id.rbChi);
        rbThu = findViewById(R.id.rbThu);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        btnThem = findViewById(R.id.btnThem);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type =0;
                if (rbThu.isChecked()){
                    type=1;
                }
                Taichinh taichinh = new Taichinh(0,edtName.getText().toString(),Integer.parseInt(edtPrice.getText().toString()),type);
                DBManager dbManager = new DBManager(ThemThu.this);
                dbManager.insertCountry(taichinh);
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemThu.this,MainActivity2.class));
            }
        });
    }
}