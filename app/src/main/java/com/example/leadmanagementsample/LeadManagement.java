package com.example.leadmanagementsample;

import android.app.Application;

public class LeadManagement extends Application {

    private static LeadManagement mInstance;
    private AppExecutor mAppExecutor;

    public static LeadManagement getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mAppExecutor = new AppExecutor();
    }

    public AppExecutor getAppExecutor() {
        return mAppExecutor;
    }
}
