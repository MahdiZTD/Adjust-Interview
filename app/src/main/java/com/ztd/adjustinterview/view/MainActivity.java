package com.ztd.adjustinterview.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ztd.adjustinterview.R;
import com.ztd.adjustinterview.infrustructure.local.PreferenceHelperImpl;
import com.ztd.adjustinterview.infrustructure.remote.ApiHelperImpl;
import com.ztd.adjustinterview.infrustructure.repository.DataManager;
import com.ztd.adjustinterview.infrustructure.repository.DataManagerImpl;
import com.ztd.adjustinterview.infrustructure.utils.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    DataManager dataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceHelperImpl preferenceHelper = PreferenceHelperImpl.getInstance(this);
        ApiHelperImpl apiHelper = new ApiHelperImpl();

        dataManager = new DataManagerImpl(preferenceHelper,apiHelper);

        Button log = findViewById(R.id.btn_log);

        log.setOnClickListener(v -> {
            dataManager.addCurrentSecondToLogger();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataManager.storeNotSavedLogs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataManager.loadNotSavedLogs();
    }



}