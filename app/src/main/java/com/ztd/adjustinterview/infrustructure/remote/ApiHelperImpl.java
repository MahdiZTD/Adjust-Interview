package com.ztd.adjustinterview.infrustructure.remote;

import android.util.Log;

import com.ztd.adjustinterview.infrustructure.utils.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by Mahdi ZTD
 * on 10/21/2021 .
 * Email: mz@imagineOn.de
 */
public class ApiHelperImpl  implements ApiHelper{


    @Override
    public void sendRequest(String log, OnSendLogCallback onSendLogCallback) {
        try {
            Log.d("DATA_SENDING", log);
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"seconds\":\"" + log + "\"}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                ResponseModel parsedRes = new Parser().parseResponse(response.toString());
                onSendLogCallback.onSuccess(parsedRes);
                Log.d("LOG_OUTPUT", response.toString());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            onSendLogCallback.onFailure(exception);
        }
    }
}
