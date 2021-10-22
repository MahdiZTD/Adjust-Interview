package com.ztd.adjustinterview.infrustructure.remote;

/**
 * Created by Mahdi ZTD
 * on 10/20/2021 .
 * Email: mz@imagineOn.de
 */
public class ResponseModel {
    public Integer id;
    public String seconds;

    public ResponseModel() {
    }

    public ResponseModel(Integer id, String seconds) {
        this.id = id;
        this.seconds = seconds;
    }
}
