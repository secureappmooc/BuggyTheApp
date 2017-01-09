package com.soton.android.buggytheapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by AndroidHPE on 12/6/2016.
 */

public class Blistener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        //if   (intent.getAction().equalsIgnoreCase("com.soton.android.buggytheapp"))
        Toast.makeText(context,bundle.getString("Message"),Toast.LENGTH_SHORT).show();
    }
}