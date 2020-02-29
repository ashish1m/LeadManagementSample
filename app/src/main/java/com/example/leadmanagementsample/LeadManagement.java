package com.example.leadmanagementsample;

import android.app.Application;

import com.example.leadmanagementsample.repository.db.DealRoomDatabase;

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

    public DealRoomDatabase getDatabase() {
        return DealRoomDatabase.getDatabase(this);
    }

    public AppExecutor getAppExecutor() {
        return mAppExecutor;
    }
}
