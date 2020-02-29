package com.example.leadmanagementsample.ui.deal_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leadmanagementsample.R;
import com.example.leadmanagementsample.util.Utils;
import com.example.leadmanagementsample.repository.db.entity.Deal;

import java.util.List;

public class DealListAdapter extends RecyclerView.Adapter<DealListAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Deal> mDealList;
    private OnItemClickListener mListener;

    public DealListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void updateList(List<Deal> dealList) {
        mDealList = dealList;
        notifyDataSetChanged();
    }

    public void addOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.deal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Deal deal = mDealList.get(position);
        holder.mDealNameTv.setText(deal.getDealName());
        String time = mInflater.getContext().getString(R.string.created_on)
                + Utils.getFormattedDate(deal.getTime());
        holder.mCreatedOnTv.setText(time);

        switch (deal.getStatus()) {
            case UNAPPROVED:
                holder.mStatusTv.setText(mInflater.getContext().getString(R.string.pending));
                holder.mStatusTv.setActivated(false);
                holder.mStatusTv.setSelected(false);
                break;

            case APPROVED:
                holder.mStatusTv.setText(mInflater.getContext().getString(R.string.approved));
                holder.mStatusTv.setActivated(true);
                holder.mStatusTv.setSelected(true);
                break;

            case REJECTED:
                holder.mStatusTv.setText(mInflater.getContext().getString(R.string.rejected));
                holder.mStatusTv.setActivated(true);
                holder.mStatusTv.setSelected(false);
                break;
        }

        holder.mMainLayoutCv.setTag(deal.getId());
    }

    @Override
    public int getItemCount() {
        if (mDealList != null) {
            return mDealList.size();
        }
        return 0;
    }

    interface OnItemClickListener {
        void onItemClick(int c);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mDealNameTv;
        private TextView mCreatedOnTv;
        private TextView mStatusTv;
        private CardView mMainLayoutCv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDealNameTv = itemView.findViewById(R.id.tv_dealName);
            mCreatedOnTv = itemView.findViewById(R.id.tv_createdOn);
            mStatusTv = itemView.findViewById(R.id.tv_status);
            mMainLayoutCv = itemView.findViewById(R.id.main_layout);
            mMainLayoutCv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_layout:
                    if (mListener != null) {
                        mListener.onItemClick((int) v.getTag());
                    }
                    break;
            }
        }
    }
}
