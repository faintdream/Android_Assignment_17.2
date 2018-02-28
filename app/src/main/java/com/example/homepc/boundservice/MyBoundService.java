package com.example.homepc.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * We define our bound service class here
 */

public class MyBoundService extends Service {


    //creating an object IBinder , so that we can use it later to talk to IBinder from MainActivity
    private final IBinder binder= (IBinder) new MyBinder();


    //defining action onBind
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyBinder extends Binder{

        MyBoundService getService(){
            return MyBoundService.this;
        }

    }

    //our method to pull current date
    public String  getTime(){
        String timeStamp=new SimpleDateFormat("HH.mm.ss").format(new Date());
        return timeStamp;
    }
}
