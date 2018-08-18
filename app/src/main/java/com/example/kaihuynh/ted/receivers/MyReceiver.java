package com.example.kaihuynh.ted.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent();
        i.setAction("service.transfers.data");
        i.putExtra("position", intent.getIntExtra("position", 0));
        context.sendBroadcast(i);
    }
}
