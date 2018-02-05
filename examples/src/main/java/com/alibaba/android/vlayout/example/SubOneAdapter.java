package com.alibaba.android.vlayout.example;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubOneAdapter extends DelegateAdapter.Adapter<SubOneAdapter.OneViewHolder> {

    private Context mContext;

    private LayoutHelper mLayoutHelper;


    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;


    public SubOneAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    public SubOneAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.mLayoutParams = layoutParams;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public OneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_one, parent, false));
    }

    @Override
    public void onBindViewHolder(OneViewHolder holder, int position) {
        holder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(mLayoutParams));
    }


    @Override
    protected void onBindViewHolderWithOffset(OneViewHolder holder, int position, int offsetTotal) {
        holder.tvTitle.setText("Success.Permission denied.");
        holder.tvTime.setText("2018-01-14 10:00");
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvTime)
        TextView tvTime;

        public OneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
