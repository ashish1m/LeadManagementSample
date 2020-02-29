package com.example.leadmanagementsample.repository;

import androidx.lifecycle.LiveData;

import com.example.leadmanagementsample.LeadManagement;
import com.example.leadmanagementsample.repository.db.DealRoomDatabase;
import com.example.leadmanagementsample.repository.db.dao.DealDao;
import com.example.leadmanagementsample.repository.db.entity.Deal;

import java.util.List;

public class DealRepository {

    private static DealRepository mInstance;
    private final LiveData<List<Deal>> mAllDeals;
    private DealDao mDealDao;

    private DealRepository(DealRoomDatabase db) {
        mDealDao = db.dealDao();
        mAllDeals = mDealDao.getAllDeals();
    }

    public static DealRepository getInstance(DealRoomDatabase db) {
        if (mInstance == null) {
            synchronized (DealRepository.class) {
                if (mInstance == null) {
                    mInstance = new DealRepository(db);
                }
            }
        }
        return mInstance;
    }

    public LiveData<Deal> getDeal(int dealId) {
        return mDealDao.getDeal(dealId);
    }

    public LiveData<List<Deal>> getAllDeals() {
        return mAllDeals;
    }

    public void insert(final Deal deal) {
        LeadManagement.getInstance()
                .getAppExecutor()
                .diskIO()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        mDealDao.insert(deal);
                    }
                });
    }
}
