package com.ztd.adjustinterview.infrustructure.remote;

/**
 * Created by Mahdi ZTD
 * on 10/21/2021 .
 * Email: mz@imagineOn.de
 */
public interface ApiHelper {

    void sendRequest(String log, OnSendLogCallback onSendLogCallback);
}
