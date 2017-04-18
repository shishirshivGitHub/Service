package com.aclass.shishir.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    MediaPlayer player;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.mritunjaya_mwu3qrqf);
        player.setLooping(true);
        player.setVolume(10,10);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // This will update the UI of the MainActivity
        // this.sendBroadcast(new Intent("com.aclass.shishir.service.android.action.broadcast").putExtra("update","MIND"));

        // return START_STICKY;            // please restart the service
        // return START_NOT_STICKY;        // please not worry to restart
        return START_REDELIVER_INTENT;     // please restart with last intent value
    }

    @Override // it is not guaranteed  that this method is called when app is killed, this is killed further by OS.
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
        // startService(new Intent(MyService.this,MyService.class));
        Toast.makeText(MyService.this,"onDestroy called",Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

}
