package com.example.homepc.boundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView output;
    Button showTime;
    MyBoundService service = new MyBoundService();
    boolean bound = false;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,MyBoundService.class);
        bindService(intent,serviceConnection,this.BIND_AUTO_CREATE);
        bound=true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bound=false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.outputTV);
        showTime = findViewById(R.id.timeBtn);



        showTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyBoundService myBoundService = new MyBoundService();
                String result = myBoundService.getTime();
                output.setText(result);
            }
        });


    }
        private ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MyBoundService.MyBinder binder = (MyBoundService.MyBinder) iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

}
