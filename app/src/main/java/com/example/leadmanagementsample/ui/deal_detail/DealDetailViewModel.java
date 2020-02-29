package com.example.leadmanagementsample.ui.deal_detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.leadmanagementsample.LeadManagement;
import com.example.leadmanagementsample.repository.db.entity.Deal;

public class DealDetailViewModel extends AndroidViewModel {

    public DealDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(Deal deal) {
        LeadManagement.getInstance().getRepository().insert(deal);
    }

    public LiveData<Deal> getDeal(int dealId) {
        return LeadManagement.getInstance().getRepository().getDeal(dealId);
    }
}
