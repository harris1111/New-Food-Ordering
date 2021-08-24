package com.example.p4f_project;

import android.app.Application;
import android.content.Context;

public class p4f_project extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }
    @Override

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}