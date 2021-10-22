package com.ztd.adjustinterview.infrustructure.utils;

import com.ztd.adjustinterview.infrustructure.remote.ResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mahdi ZTD
 * on 10/22/2021 .
 * Email: mz@imagineOn.de
 */
public class Parser {

    public ResponseModel parseResponse(String json) {
        ResponseModel out;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String seconds = jsonObject.getString("seconds");
            int id = jsonObject.getInt("id");
            out = new ResponseModel(id, seconds);
        } catch (JSONException exception) {
            exception.printStackTrace();
            out = new ResponseModel();
        }
        return out;
    }
}
