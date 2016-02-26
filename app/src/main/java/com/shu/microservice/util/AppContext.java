package com.shu.microservice.util;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wxl on 2016/2/25.
 */
public class AppContext extends Application{
    private static RequestQueue appQueue;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        appQueue = Volley.newRequestQueue(appContext);
    }

    public static Context getAppContext(){
        return appContext;
    }
    public static RequestQueue getAppQueue(){
        return appQueue;
    }
}
