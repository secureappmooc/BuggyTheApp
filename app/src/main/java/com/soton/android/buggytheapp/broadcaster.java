package com.soton.android.buggytheapp;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by AndroidHPE on 12/6/2016.
 */

public class broadcaster extends IntentService {
    //this class do not have default constructor
    public broadcaster()
    {
        super("mybroadcaster");
    }
    public static boolean broadcasting=false;

    @Override
    protected void onHandleIntent(Intent intent)
    {
        while ( broadcasting) {
            Intent broadcastintent = new Intent();
            broadcastintent.setAction("com.soton.android.bugysqliteapp");
            broadcastintent.putExtra("Message", "This is always running service");
            sendBroadcast(broadcastintent);
            try
            {
                Thread.sleep(3000);
            }catch (Exception ex){}


        }

    }
}