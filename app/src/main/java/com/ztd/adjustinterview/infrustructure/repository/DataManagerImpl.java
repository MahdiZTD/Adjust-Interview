package com.ztd.adjustinterview.infrustructure.repository;

import android.util.Log;

import androidx.annotation.VisibleForTesting;

import com.ztd.adjustinterview.infrustructure.local.PreferenceHelper;
import com.ztd.adjustinterview.infrustructure.remote.ApiHelper;
import com.ztd.adjustinterview.infrustructure.remote.OnSendLogCallback;
import com.ztd.adjustinterview.infrustructure.remote.ResponseModel;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mahdi ZTD
 * on 10/21/2021 .
 * Email: mz@imagineOn.de
 */
public class DataManagerImpl implements DataManager {

    PreferenceHelper preferenceHelper;
    ApiHelper apiHelper;

    @VisibleForTesting
    public CopyOnWriteArraySet<String> logQueue = new CopyOnWriteArraySet<>();
    Iterator<String> iterator;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public DataManagerImpl(PreferenceHelper preferenceHelper, ApiHelper apiHelper) {
        this.preferenceHelper = preferenceHelper;
        this.apiHelper = apiHelper;
    }

    @Override
    public void storeNotSavedLogs() {
        preferenceHelper.saveQue(logQueue.toArray(new String[0]));
    }

    @Override
    public void loadNotSavedLogs() {
        List<String> previousLogs = Arrays.asList(preferenceHelper.loadQue());
        if (!previousLogs.isEmpty()) {
            logQueue.addAll(previousLogs);
            runTask();
        }
    }

    @Override
    public void addCurrentSecondToLogger() {
        String currentSec = getCurrentTimeSecond();
        if (!preferenceHelper.isExist(currentSec) && !logQueue.contains(currentSec)) {
            logQueue.add(currentSec);
            runTask();
        } else {
            Log.w("LOG_REPEATED_VALUE", currentSec);
        }
    }

    @VisibleForTesting
    public CopyOnWriteArraySet<String> getLogQueue() {
        return logQueue;
    }

    public void runTask() {
        iterator = logQueue.iterator();
        while (iterator.hasNext()) {
            String log = iterator.next();
            executorService.execute(new SendLogRunnable(log));
        }
    }


    private String getCurrentTimeSecond() {
        SimpleDateFormat sdf = new SimpleDateFormat("ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private class SendLogRunnable implements Runnable {

        String log;

        public SendLogRunnable(String log) {
            this.log = log;
        }

        @Override
        public void run() {
            apiHelper.sendRequest(log, new OnSendLogCallback() {
                @Override
                public void onSuccess(ResponseModel responseModel) {
                    logQueue.remove(log);
                    preferenceHelper.saveLogged(responseModel.seconds, responseModel.id);
                }

                @Override
                public void onFailure(Exception exception) {
                    exception.printStackTrace();
                }
            });

        }
    }
}
