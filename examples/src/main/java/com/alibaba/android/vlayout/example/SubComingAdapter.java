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
 * 一行显示一个直播
 */
public class SubComingAdapter extends DelegateAdapter.Adapter<SubComingAdapter.ComingViewHolder> {

    private Context mContext;

    private LayoutHelper mLayoutHelper;


    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;


    public SubComingAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public SubComingAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
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
    public ComingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ComingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_coming, parent, false));
    }

    @Override
    public void onBindViewHolder(ComingViewHolder holder, int position) {
        holder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(mLayoutParams));
    }


    @Override
    protected void onBindViewHolderWithOffset(ComingViewHolder holder, int position, int offsetTotal) {
        holder.tvTitle.setText("AI系列培训-Introduction to AI & ML-分布式与并行与交流");
        holder.tvTime.setText("01-14 10:00");
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class ComingViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvTime)
        TextView tvTime;

        public ComingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
