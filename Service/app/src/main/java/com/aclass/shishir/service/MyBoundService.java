package com.aclass.shishir.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pc on 4/18/2017.
 */

public class MyBoundService extends Service {

    public int add(int a,int b){
        return a+b;
    }

    public int multiply(int a,int b){
        return a*b;
    }

    private MyLocalBinder myLocalBinder = new MyLocalBinder();

    public class MyLocalBinder extends Binder{

        MyBoundService getService(){
            return MyBoundService.this;
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myLocalBinder;
    }

}
