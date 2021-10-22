package com.ztd.adjustinterview.infrustructure.repository;

import android.os.Handler;

import com.ztd.adjustinterview.infrustructure.remote.ApiHelper;
import com.ztd.adjustinterview.infrustructure.remote.OnSendLogCallback;
import com.ztd.adjustinterview.infrustructure.remote.ResponseModel;


/**
 * Created by Mahdi ZTD
 * on 10/22/2021 .
 * Email: mz@imagineOn.de
 */
public class MockApiHelper implements ApiHelper {
    @Override
    public void sendRequest(String log, OnSendLogCallback onSendLogCallback) {
        onSendLogCallback.onSuccess(new ResponseModel(101, log));
    }
}
