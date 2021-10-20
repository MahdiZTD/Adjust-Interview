package com.ztd.adjustinterview.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ztd.adjustinterview.R;

public class MainActivity extends AppCompatActivity {
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button log = findViewById(R.id.btn_log);
        
        log.setOnClickListener(v -> {
            
        });
    }
}