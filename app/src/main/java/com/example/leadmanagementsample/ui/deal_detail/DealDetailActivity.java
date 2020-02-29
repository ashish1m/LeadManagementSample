package com.example.leadmanagementsample.ui.deal_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.leadmanagementsample.R;
import com.example.leadmanagementsample.repository.db.entity.Deal;
import com.example.leadmanagementsample.util.Utils;

public class DealDetailActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public static final String DEAL_ID = "DEAL_ID";
    private TextView mDealNameTv, mCreatedOnTv, mStatusTv;
    private RadioGroup mStatusSalesRg, mStatusFinanceRg;
    private RadioButton mApproveSalesRb, mRejectSalesRb, mApproveFinanceRb, mRejectFinanceRb;
    private Button mSaveBtn;
    private DealDetailViewModel mDealDetailViewModel;
    private int dealId;
    private Deal mDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);

        dealId = getIntent().getIntExtra(DEAL_ID, -1);
        mDealDetailViewModel = new ViewModelProvider(this).get(DealDetailViewModel.class);

        initView();
    }

    private void initView() {
        mDealNameTv = findViewById(R.id.tv_dealName);
        mCreatedOnTv = findViewById(R.id.tv_createdOn);
        mStatusTv = findViewById(R.id.tv_status);
        mApproveSalesRb = findViewById(R.id.rb_approveSales);
        mRejectSalesRb = findViewById(R.id.rb_rejectSales);
        mApproveFinanceRb = findViewById(R.id.rb_approveFinance);
        mRejectFinanceRb = findViewById(R.id.rb_rejectFinance);
        mStatusSalesRg = findViewById(R.id.rg_statusSales);
        mStatusFinanceRg = findViewById(R.id.rg_statusFinance);
        mSaveBtn = findViewById(R.id.btn_save);

        mSaveBtn.setOnClickListener(this);
        mStatusSalesRg.setOnCheckedChangeListener(this);
        mStatusFinanceRg.setOnCheckedChangeListener(this);

        if (dealId != -1) {
            mDealDetailViewModel.getDeal(dealId).observe(this, new Observer<Deal>() {
                @Override
                public void onChanged(Deal deal) {
                    mDeal = deal;
                    updateUI(deal);
                }
            });
        }
    }

    private void updateUI(Deal deal) {
        mDealNameTv.setText(deal.getDealName());
        String time = getString(R.string.created_on)
                + Utils.getFormattedDate(deal.getTime());
        mCreatedOnTv.setText(time);
        updateStatus(deal.getStatus());
        updateSalesStatus(deal.getStatusSales());
        updateFinanceStatus(deal.getStatusFinance());
    }

    private void updateSalesStatus(Deal.Status saleStatus) {
        switch (saleStatus){
            case UNAPPROVED:
                break;
            case APPROVED:
                mApproveSalesRb.setChecked(true);
                break;
            case REJECTED:
                mRejectSalesRb.setChecked(true);
                break;
        }
    }

    private void updateFinanceStatus(Deal.Status financeStatus) {
        switch (financeStatus){
            case UNAPPROVED:
                break;
            case APPROVED:
                mApproveFinanceRb.setChecked(true);
                break;
            case REJECTED:
                mRejectFinanceRb.setChecked(true);
                break;
        }
    }

    private void updateStatus(Deal.Status status) {
        switch (status) {
            case UNAPPROVED:
                mStatusTv.setText(getString(R.string.pending));
                mStatusTv.setActivated(false);
                mStatusTv.setSelected(false);
                break;

            case APPROVED:
                mStatusTv.setText(getString(R.string.approved));
                mStatusTv.setActivated(true);
                mStatusTv.setSelected(true);
                break;

            case REJECTED:
                mStatusTv.setText(getString(R.string.rejected));
                mStatusTv.setActivated(true);
                mStatusTv.setSelected(false);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                mDealDetailViewModel.insert(mDeal);
                Utils.showToast("Deal updated successfully.");
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (mStatusSalesRg.getCheckedRadioButtonId() == R.id.rb_approveSales
            && mStatusFinanceRg.getCheckedRadioButtonId() == R.id.rb_approveFinance) {
            mDeal.setStatus(Deal.Status.APPROVED);
        } else if (mStatusSalesRg.getCheckedRadioButtonId() == R.id.rb_rejectSales
                || mStatusFinanceRg.getCheckedRadioButtonId() == R.id.rb_rejectFinance) {
            mDeal.setStatus(Deal.Status.REJECTED);
        } else {
            mDeal.setStatus(Deal.Status.UNAPPROVED);
        }
        updateStatus(mDeal.getStatus());
    }
}
