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

public class SuaThu extends AppCompatActivity {
    private RadioButton rbThu,rbChi;
    private Button btnXoa, btnSua;
    private EditText edtName, edtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thu);

        rbThu = findViewById(R.id.rbThu);
        rbChi = findViewById(R.id.rbChi);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        DBManager dbManager = new DBManager(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Taichinh taichinh = dbManager.getByID(id);
        setUi(taichinh);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteCountryByID(id);
                Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SuaThu.this,MainActivity2.class));
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type =0;
                if (rbThu.isChecked()){
                    type=1;
                }
                Taichinh taichinh = new Taichinh(id,edtName.getText().toString(),Integer.parseInt(edtPrice.getText().toString()),type);
                dbManager.update(taichinh);
                startActivity(new Intent(SuaThu.this,MainActivity2.class));
                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void setUi(Taichinh taichinh){
        edtName.setText(taichinh.getName());
        edtPrice.setText(String.valueOf(taichinh.getPrice()));
        if(taichinh.getType() == 1){
            rbThu.setChecked(true);
        }
        else rbChi.setChecked(true);
    }
}