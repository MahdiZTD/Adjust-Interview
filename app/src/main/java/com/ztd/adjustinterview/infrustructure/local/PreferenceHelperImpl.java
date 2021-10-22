package com.ztd.adjustinterview.infrustructure.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mahdi ZTD
 * on 10/20/2021 .
 * Email: mz@imagineOn.de
 */
public class PreferenceHelperImpl implements PreferenceHelper {

    private static final String PREFERENCES_NAME = "ADJUST_APP";
    private static final String PREF_PREVIOUS_LOGS = "PREV_LOGS";

    private static PreferenceHelperImpl preferenceHelper;

    private SharedPreferences mPrefs;


    public static PreferenceHelperImpl getInstance(Context context){
        if(preferenceHelper == null) {
            preferenceHelper = new PreferenceHelperImpl(context);
        }
        return preferenceHelper;
    }

    private PreferenceHelperImpl(Context context){
        mPrefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveQue(String[] objects){
        StringBuilder stringObject = new StringBuilder();
        for (String object : objects) {
            stringObject.append(object);
            stringObject.append(";");
        }
        mPrefs.edit().putString(PREF_PREVIOUS_LOGS,stringObject.toString()).apply();
    }

    @Override
    public String[] loadQue(){
        String prevLog = mPrefs.getString(PREF_PREVIOUS_LOGS,"");
        if(prevLog.isEmpty()){
            return new String[0];
        }
        return prevLog.split(";");
    }

    @Override
    public void saveLogged(String second , Integer id){
        mPrefs.edit().putInt(second,id).apply();
    }

    @Override
    public Boolean isExist(String second) {
        return mPrefs.getInt(second,-1) != -1;
    }


}
