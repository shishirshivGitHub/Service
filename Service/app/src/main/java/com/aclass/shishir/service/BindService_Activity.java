package com.aclass.shishir.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BindService_Activity extends AppCompatActivity implements View.OnClickListener{

    Button add,Multiple;
    EditText firstNumber,secondNumber;
    TextView result;
    boolean isBound;
    int firstN, secondN;
    private MyBoundService myBoundService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            isBound = true;
            MyBoundService.MyLocalBinder myLocalBinder = (MyBoundService.MyLocalBinder) iBinder;
            myBoundService = myLocalBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service_);

        add = (Button)findViewById(R.id.add);
        Multiple = (Button)findViewById(R.id.Multiple);
        firstNumber = (EditText) findViewById(R.id.firstNumber);
        secondNumber = (EditText)findViewById(R.id.secondNumber);
        result = (TextView) findViewById(R.id.result);

        add.setOnClickListener(this);
        Multiple.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        bindService(new Intent(BindService_Activity.this,MyBoundService.class),
                serviceConnection,
                BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View view) {

        firstN = Integer.parseInt(firstNumber.getText().toString());
        secondN = Integer.parseInt(secondNumber.getText().toString());

        if(isBound){

            switch (view.getId()){
                case R.id.add:
                    result.setText(myBoundService.add(firstN,secondN)+"");
                    break;

                case R.id.Multiple:
                    result.setText(myBoundService.multiply(firstN,secondN)+"");
                    break;
            }
        }
    }
}
