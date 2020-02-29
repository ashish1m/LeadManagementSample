package com.example.leadmanagementsample.ui.deal_add;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.leadmanagementsample.R;
import com.example.leadmanagementsample.repository.db.entity.Deal;
import com.example.leadmanagementsample.util.Utils;

public class DealCreateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mDealNameEt;
    private Button mCreateBtn;
    private DealCreateViewModel mDealCreateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_add);
        mDealCreateViewModel = new ViewModelProvider(this).get(DealCreateViewModel.class);

        initView();
    }

    private void initView() {
        mDealNameEt = findViewById(R.id.et_dealName);
        mCreateBtn = findViewById(R.id.btn_create);

        mCreateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                createDeal();
                break;
        }
    }

    private void createDeal() {
        Deal deal = new Deal(mDealNameEt.getText().toString());
        mDealCreateViewModel.insert(deal);
        Utils.showToast("Deal created successfully.");
        finish();
    }
}
