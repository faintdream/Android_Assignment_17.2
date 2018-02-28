package com.example.homepc.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by homepc on 26-02-2018.
 */

public class MyBoundService extends Service {

    private final IBinder binder= (IBinder) new MyBinder();

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
    public String  getTime(){

        String timeStamp=new SimpleDateFormat("HH.mm.ss").format(new Date());
        return timeStamp;
    }
}
