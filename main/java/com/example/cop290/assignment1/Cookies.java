package com.example.cop290.assignment1;

import android.app.Application;

import java.net.CookieHandler;
import java.net.CookieManager;

/**
 * Created by Aayan Kumar on 22-02-2016.
 */
public class Cookies extends Application {


    CookieHandler cookieManage;
    public void onCreate() {
        cookieManage= new CookieManager();
        CookieHandler.setDefault(cookieManage);
        super.onCreate();
    }


}
