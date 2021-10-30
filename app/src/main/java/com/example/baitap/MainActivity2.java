package com.example.baitap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitap.Other.DBManager;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private Button btnThem;
    private RecyclerView rclList;
    private TextView txtTongthu,txtTongchi;
    private TaichinhAdapter taichinhAdapter;
    private ArrayList<Taichinh> alTaichinh = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnThem = findViewById(R.id.btnThem);
        rclList = findViewById(R.id.rclList);
        txtTongthu = findViewById(R.id.txtTongthu);
        txtTongchi = findViewById(R.id.txtTongchi);

        DBManager dbManager = new DBManager(this);
        alTaichinh = new ArrayList<>(dbManager.getAllCountry());
        //////
        int tongthu =0,tongchi=0;

        for (int i =0; i<alTaichinh.size();i++){
            Taichinh taichinh = alTaichinh.get(i);

            if(taichinh !=null) {
                if (taichinh.getType() == 1) {
                    tongthu += taichinh.getPrice();
                } else
                    tongchi += taichinh.getPrice();
            }
        }
        txtTongchi.setText("Tổng Chi:"+tongchi);
        txtTongthu.setText("Tổng Thu:"+tongthu);


        ///
        rclList.setHasFixedSize(true);
        taichinhAdapter = new TaichinhAdapter(alTaichinh, this, new TaichinhAdapter.TaichinhAdapterListener() {
            @Override
            public void click(View v, int position) {
                int id = alTaichinh.get(position).getId();
               Intent intent = new Intent(MainActivity2.this,SuaThu.class);
                intent.putExtra("id",id);
            startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rclList.setLayoutManager(linearLayoutManager);
        rclList.setAdapter(taichinhAdapter);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,ThemThu.class));
            }
        });

    }

}