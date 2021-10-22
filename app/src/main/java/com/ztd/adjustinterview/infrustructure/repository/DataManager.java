package com.ztd.adjustinterview.infrustructure.repository;

/**
 * Created by Mahdi ZTD
 * on 10/21/2021 .
 * Email: mz@imagineOn.de
 */
public interface DataManager {


    void addCurrentSecondToLogger();
    void storeNotSavedLogs();
    void loadNotSavedLogs();

}
