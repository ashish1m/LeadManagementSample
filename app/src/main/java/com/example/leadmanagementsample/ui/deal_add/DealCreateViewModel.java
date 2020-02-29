package com.example.leadmanagementsample.ui.deal_add;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.leadmanagementsample.LeadManagement;
import com.example.leadmanagementsample.repository.db.entity.Deal;

public class DealCreateViewModel extends AndroidViewModel {

    public DealCreateViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(Deal deal) {
        LeadManagement.getInstance().getRepository().insert(deal);
    }
}
