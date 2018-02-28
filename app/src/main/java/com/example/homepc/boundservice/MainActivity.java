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


    // out view objects
    TextView output;
    Button showTime;

    //creating an instance of our bound service
    MyBoundService service = new MyBoundService();

    //we use this variable to track binding status
    boolean bound = false;


    //overriding onStart and onStop actions
    @Override
    protected void onStart() {
        super.onStart();

        //defining explicit intent
        Intent intent = new Intent(this, MyBoundService.class);

        //calling service binder
        bindService(intent, serviceConnection, this.BIND_AUTO_CREATE);
        bound = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bound = false;
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

                String result = service.getTime();
                output.setText(result);
            }
        });


    }


    //creating an object of ServiceConnection, it is used to communciate with IBinder
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
