package com.example.leadmanagementsample.ui.deal_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leadmanagementsample.R;
import com.example.leadmanagementsample.repository.db.entity.Deal;
import com.example.leadmanagementsample.ui.deal_add.DealCreateActivity;
import com.example.leadmanagementsample.ui.deal_detail.DealDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DealListActivity extends AppCompatActivity implements DealListAdapter.OnItemClickListener, View.OnClickListener {

    private RecyclerView mDealsRv;
    private DealListAdapter mAdapter;
    private DealListViewModel mDealListViewModel;
    private FloatingActionButton mFabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_list);
        mDealListViewModel = new ViewModelProvider(this).get(DealListViewModel.class);
        mAdapter = new DealListAdapter(this);
        mAdapter.addOnItemClickListener(this);
        initView();
    }

    private void initView() {
        mDealsRv = findViewById(R.id.rv_deals);
        mFabAdd = findViewById(R.id.fab_addNote);
        mDealsRv.setLayoutManager(new LinearLayoutManager(this));
        mDealsRv.setHasFixedSize(true);
        mDealsRv.setAdapter(mAdapter);
        mFabAdd.setOnClickListener(this);

        mDealListViewModel.getAllNotes().observe(this, new Observer<List<Deal>>() {
            @Override
            public void onChanged(List<Deal> deals) {
                mAdapter.updateList(deals);
            }
        });
    }

    private void navigateToAddDealActivity() {
        Intent intent = new Intent(DealListActivity.this, DealCreateActivity.class);
        startActivity(intent);
    }

    private void navigateToDealDetailActivity(int dealId) {
        Intent intent = new Intent(DealListActivity.this, DealDetailActivity.class);
        intent.putExtra(DealDetailActivity.DEAL_ID, dealId);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_addNote:
                navigateToAddDealActivity();
                break;
        }
    }

    @Override
    public void onItemClick(int dealId) {
        navigateToDealDetailActivity(dealId);
    }
}
