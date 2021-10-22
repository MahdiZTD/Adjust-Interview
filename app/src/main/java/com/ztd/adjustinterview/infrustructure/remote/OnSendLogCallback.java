package com.ztd.adjustinterview.infrustructure.remote;

/**
 * Created by Mahdi ZTD
 * on 10/22/2021 .
 * Email: mz@imagineOn.de
 */
public interface OnSendLogCallback {
    void onSuccess (ResponseModel responseModel);
    void onFailure (Exception exception);
}
