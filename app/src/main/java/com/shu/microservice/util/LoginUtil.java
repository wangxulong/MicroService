package com.shu.microservice.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
public class LoginUtil {
    private static android.content.SharedPreferences SharedPreferences;

    public static boolean isLogin(){
        Context ctx = AppContext.getAppContext();
        SharedPreferences = ctx.getSharedPreferences("MyInfo", Context.MODE_PRIVATE);
        Long id = SharedPreferences.getLong("id",0);
        if(id==0) return false;
        return true;
    }

    public Long getLoginUser(){
        Context ctx = AppContext.getAppContext();
        SharedPreferences = ctx.getSharedPreferences("MyInfo", Context.MODE_PRIVATE);
        Long id = SharedPreferences.getLong("id",0);
        if(id==0) return null;
        return id;
    }
}
