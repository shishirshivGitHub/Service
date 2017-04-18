package com.aclass.shishir.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button StartService, startIntentService,bindService;
    static Button StopService;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(MainActivity.this.getMainLooper());

        StartService = (Button)findViewById(R.id.StartService);
        StopService = (Button)findViewById(R.id.StopService);
        startIntentService = (Button)findViewById(R.id.startIntentService);
        bindService = (Button)findViewById(R.id.bindService);

        StartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            startService(new Intent(MainActivity.this,MyService.class));

            }
        });

        StopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopService(new Intent(MainActivity.this,MyService.class));

            }
        });

        startIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResultReceiverSubClass resultReceiverSubClass = new ResultReceiverSubClass(null);
                startService(new Intent(MainActivity.this,IntentServiceExample.class)
                        .putExtra("resultReceiver",resultReceiverSubClass));
            }
        });

        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,BindService_Activity.class));

            }
        });
    }

    static public class BroadCastReceiverClass extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            String updateValue = intent.getStringExtra("update");
            StopService.setText(updateValue);

        }
    }

    private class ResultReceiverSubClass extends ResultReceiver{

        public ResultReceiverSubClass(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            if(resultCode == 18 && resultData != null){

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startIntentService.setText(resultData.getString("value"));
                    }
                },100);


            }
        }
    }

}
