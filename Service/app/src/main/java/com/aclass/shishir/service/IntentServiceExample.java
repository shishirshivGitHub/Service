package com.aclass.shishir.service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

/**
 * Created by pc on 4/4/2017.
 */

public class IntentServiceExample extends IntentService {

    public IntentServiceExample() {
        super("WorkerThread");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(" IntentService class " , Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {  // Long Running Task Here

        ResultReceiver resultReceiver = intent.getParcelableExtra("resultReceiver");

        Log.e(" IntentService class " , Thread.currentThread().getName());
        int ctr = 0;
        do{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            ctr++;
            Log.e("increment the value -->> ", ctr + " ");

        }while (ctr < 10);

        Bundle bundle = new Bundle();
        bundle.putString("value"," Counter Stops at " + ctr +" ");
        resultReceiver.send(18,bundle);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e(" IntentService class " , Thread.currentThread().getName());

    }
}
