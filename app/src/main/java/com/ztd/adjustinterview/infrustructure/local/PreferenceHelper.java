package com.ztd.adjustinterview.infrustructure.local;

/**
 * Created by Mahdi ZTD
 * on 10/21/2021 .
 * Email: mz@imagineOn.de
 */
public interface PreferenceHelper {

    void saveQue(String[] objects);
    String[] loadQue();

    void saveLogged(String second , Integer id);
    Boolean isExist(String second);
}
