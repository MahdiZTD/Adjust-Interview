package com.ztd.adjustinterview.infrustructure.repository;

import static org.junit.Assert.*;

import com.ztd.adjustinterview.infrustructure.local.PreferenceHelper;
import com.ztd.adjustinterview.infrustructure.remote.ApiHelper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mahdi ZTD
 * on 10/22/2021 .
 * Email: mz@imagineOn.de
 */
public class DataManagerImplTest {
    PreferenceHelper preferenceHelper;
    ApiHelper apiHelper;

    DataManagerImpl dataManager;

    @Before
    public void setUp() throws Exception {
        preferenceHelper = new MockPrefHelper();
        apiHelper = new MockApiHelper();
        dataManager = new DataManagerImpl(preferenceHelper,apiHelper);
    }

    @Test
    public void addCurrentSecondToLogger_hasCorrectSecondValue(){
        dataManager.addCurrentSecondToLogger();
        SimpleDateFormat sdf = new SimpleDateFormat("ss", Locale.getDefault());
        String second = sdf.format(new Date());
        assertTrue(dataManager.getLogQueue().contains(second));
    }

    @Test
    public void addCurrentSecondToLogger_sendLogWithSuccessResponse() throws Exception{
        dataManager.addCurrentSecondToLogger();
        SimpleDateFormat sdf = new SimpleDateFormat("ss", Locale.getDefault());
        String second = sdf.format(new Date());
        Thread.sleep(1000);
        assertTrue(preferenceHelper.isExist(second));
    }

    @Test
    public void loadNotSavedLogs_testTryToLog() throws Exception{
        dataManager.logQueue.add("12");
        dataManager.logQueue.add("34");
        dataManager.logQueue.add("56");
        dataManager.logQueue.add("89");
        dataManager.logQueue.add("35");
        dataManager.logQueue.add("03");
        dataManager.storeNotSavedLogs();
        dataManager.loadNotSavedLogs();
        Thread.sleep(1000);
        assertTrue(preferenceHelper.isExist("12"));
        assertTrue(preferenceHelper.isExist("34"));
        assertTrue(preferenceHelper.isExist("56"));
        assertTrue(preferenceHelper.isExist("89"));
        assertTrue(preferenceHelper.isExist("35"));
        assertTrue(preferenceHelper.isExist("03"));
    }


}