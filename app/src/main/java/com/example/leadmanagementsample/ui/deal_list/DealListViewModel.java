package com.example.leadmanagementsample.ui.deal_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.leadmanagementsample.LeadManagement;
import com.example.leadmanagementsample.repository.db.entity.Deal;

import java.util.List;

public class DealListViewModel extends AndroidViewModel {

    private LiveData<List<Deal>> mAllDeals;

    public DealListViewModel(@NonNull Application application) {
        super(application);
        mAllDeals = LeadManagement.getInstance().getRepository().getAllDeals();
    }

    public LiveData<List<Deal>> getAllNotes() {
        return mAllDeals;
    }
}
