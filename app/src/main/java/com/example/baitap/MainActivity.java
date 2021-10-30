package com.example.baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton tgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      tgBtn = findViewById(R.id.tgBtn);

        tgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doService(v);
            }
        });


    }
    public void doService(View view)
    {
        // tham ToggleButton
        ToggleButton btn = (ToggleButton) view;
        if (btn.isChecked()==true) {
            startService( new Intent(this,MyService.class) );
        }
        else {

            stopService( new Intent(this,MyService.class) );


        }
    }
}