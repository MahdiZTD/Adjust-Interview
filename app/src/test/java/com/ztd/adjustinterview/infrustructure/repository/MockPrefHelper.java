package com.ztd.adjustinterview.infrustructure.repository;

import com.ztd.adjustinterview.infrustructure.local.PreferenceHelper;

import java.util.HashMap;

/**
 * Created by Mahdi ZTD
 * on 10/22/2021 .
 * Email: mz@imagineOn.de
 */
public class MockPrefHelper implements PreferenceHelper {

    String[] notSavedLogged = new String[0];
    HashMap<String,Integer> storeLogged = new HashMap<>();
    @Override
    public void saveQue(String[] objects) {
        notSavedLogged = objects;
    }

    @Override
    public String[] loadQue() {
        return notSavedLogged;
    }

    @Override
    public void saveLogged(String second, Integer id) {
        storeLogged.put(second,id);
    }

    @Override
    public Boolean isExist(String second) {
        return storeLogged.containsKey(second);
    }
}
