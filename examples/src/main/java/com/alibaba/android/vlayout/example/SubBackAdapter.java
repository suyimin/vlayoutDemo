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

/**
 * 一行显示两个直播
 */
public class SubBackAdapter extends DelegateAdapter.Adapter<SubBackAdapter.BackViewHolder> {

    private Context mContext;

    private LayoutHelper mLayoutHelper;


    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;


    public SubBackAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public SubBackAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
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
    public BackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BackViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_back, parent, false));
    }

    @Override
    public void onBindViewHolder(BackViewHolder holder, int position) {
        holder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(mLayoutParams));
    }

    @Override
    protected void onBindViewHolderWithOffset(BackViewHolder holder, int position, int offsetTotal) {
        holder.tvTitle.setText("AI系列培训");
        holder.tvTime.setText("1267次播放");
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class BackViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvTime)
        TextView tvTime;

        public BackViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
