package com.alibaba.android.vlayout.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> mLists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentHolder.getLiveComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        mLists = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            String bean = new String();
            mLists.add(bean);
        }

        int quotient = mLists.size() / 3;
        int remainder = mLists.size() % 3;


        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        layoutManager.setRecycleOffset(300);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        for (int i = 0; i < quotient; i++) {
            GridLayoutHelper helper1 = new GridLayoutHelper(1, 1);
            adapters.add(new SubOneAdapter(getActivity(), helper1, 1) {
                @Override
                public void onBindViewHolder(SubOneAdapter.OneViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.leftMargin = 36;
                    layoutParams.rightMargin = 36;
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
            GridLayoutHelper helper2 = new GridLayoutHelper(2, 2);
            adapters.add(new SubTwoAdapter(getActivity(), helper2, 2) {
                @Override
                public void onBindViewHolder(SubTwoAdapter.TwoViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    if (position % 2 == 0) {
                        layoutParams.leftMargin = 36;
                        layoutParams.rightMargin = 18;
                    } else {
                        layoutParams.leftMargin = 18;
                        layoutParams.rightMargin = 36;
                    }
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (remainder == 1) {
            GridLayoutHelper helper3 = new GridLayoutHelper(1, 1);
            adapters.add(new SubOneAdapter(getActivity(), helper3, 1) {
                @Override
                public void onBindViewHolder(SubOneAdapter.OneViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.leftMargin = 36;
                    layoutParams.rightMargin = 36;
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        } else if (remainder == 2) {
            if (quotient == 0) {
                GridLayoutHelper helper4 = new GridLayoutHelper(1, 1);
                adapters.add(new SubOneAdapter(getActivity(), helper4, 2) {
                    @Override
                    public void onBindViewHolder(SubOneAdapter.OneViewHolder holder, int position) {
                        super.onBindViewHolder(holder, position);
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.leftMargin = 36;
                        layoutParams.rightMargin = 36;
                        holder.itemView.setLayoutParams(layoutParams);
                    }
                });
            } else {
                GridLayoutHelper helper5 = new GridLayoutHelper(2, 2);
                adapters.add(new SubTwoAdapter(getActivity(), helper5, 2) {
                    @Override
                    public void onBindViewHolder(SubTwoAdapter.TwoViewHolder holder, int position) {
                        super.onBindViewHolder(holder, position);
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        if (position % 2 == 0) {
                            layoutParams.leftMargin = 36;
                            layoutParams.rightMargin = 18;
                        } else {
                            layoutParams.leftMargin = 18;
                            layoutParams.rightMargin = 36;
                        }
                        holder.itemView.setLayoutParams(layoutParams);
                    }
                });
            }
        }

        delegateAdapter.setAdapters(adapters);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
