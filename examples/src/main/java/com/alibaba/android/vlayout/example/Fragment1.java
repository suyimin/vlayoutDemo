package com.alibaba.android.vlayout.example;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Fragment1 extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private static List<String> mLists;
    private static List<String> mLivingLists;
    private static List<String> mComingLists;
    private static List<String> mBackLists;

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
        mLivingLists = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            String bean = new String();
            mLivingLists.add(bean);
        }

        mComingLists = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            String bean = new String();
            mComingLists.add(bean);
        }

        mBackLists = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            String bean = new String();
            mBackLists.add(bean);
        }

        mLists.addAll(mLivingLists);//直播中
        mLists.add(new String());//即将直播标题
        if (mComingLists.size() > 4) {//最多显示4个
            mLists.addAll(mComingLists.subList(0, 4));
        } else {
            mLists.addAll(mComingLists);
        }
        mLists.add(new String());
        mLists.addAll(mBackLists);

        int quotient = mLivingLists.size() / 3;
        int remainder = mLivingLists.size() % 3;


        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        layoutManager.setRecycleOffset(30);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(1, 10);
        viewPool.setMaxRecycledViews(2, 10);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        for (int i = 0; i < quotient; i++) {
            GridLayoutHelper helper1 = new GridLayoutHelper(1, 1);
            adapters.add(new SubAdapter(getActivity(), helper1, 1) {

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.leftMargin = 36;
                    layoutParams.rightMargin = 36;
                    holder.itemView.setLayoutParams(layoutParams);
                }

                @Override
                protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                    ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText(String.valueOf(position));
                }
            });
            GridLayoutHelper helper2 = new GridLayoutHelper(2, 2);
            adapters.add(new SubAdapter(getActivity(), helper2, 2) {

                @Override
                public int getItemViewType(int position) {
                    return 2;
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_two, parent, false));
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
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

                @Override
                protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                    ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText(String.valueOf(position));
                }
            });
        }

        if (remainder == 1) {
            GridLayoutHelper helper3 = new GridLayoutHelper(1, 1);
            adapters.add(new SubAdapter(getActivity(), helper3, 1) {

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.leftMargin = 36;
                    layoutParams.rightMargin = 36;
                    holder.itemView.setLayoutParams(layoutParams);
                }

                @Override
                protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                    ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText(String.valueOf(position));
                }
            });
        } else if (remainder == 2) {
            if (quotient == 0) {
                GridLayoutHelper helper4 = new GridLayoutHelper(1, 1);
                adapters.add(new SubAdapter(getActivity(), helper4, 2) {

                    @Override
                    public int getItemViewType(int position) {
                        return 1;
                    }

                    @Override
                    public void onBindViewHolder(MainViewHolder holder, int position) {
                        super.onBindViewHolder(holder, position);
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.leftMargin = 36;
                        layoutParams.rightMargin = 36;
                        holder.itemView.setLayoutParams(layoutParams);
                    }

                    @Override
                    protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                        ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText(String.valueOf(position));
                    }
                });
            } else {
                GridLayoutHelper helper5 = new GridLayoutHelper(2, 2);
                adapters.add(new SubAdapter(getActivity(), helper5, 2) {

                    @Override
                    public int getItemViewType(int position) {
                        return 1;
                    }

                    @Override
                    public void onBindViewHolder(MainViewHolder holder, int position) {
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

                    @Override
                    protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                        ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText(String.valueOf(position));
                    }
                });
            }
        }
        //即将直播
        GridLayoutHelper layoutHelper = new GridLayoutHelper(1, 1);
        layoutHelper.setMargin(36, 110, 36, 20);
        adapters.add(new SubAdapter(getActivity(), layoutHelper, 1) {

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public void onBindViewHolder(final MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }

            @Override
            protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText("即将直播");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toFragment2();
                    }
                });
            }
        });
        GridLayoutHelper helperUpcoming = new GridLayoutHelper(1, mComingLists.size() > 4 ? 4 : mComingLists.size());
        adapters.add(new SubAdapter(getActivity(), helperUpcoming, mComingLists.size() > 4 ? 4 : mComingLists.size()) {

            @Override
            public int getItemViewType(int position) {
                return 3;
            }

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = 36;
                layoutParams.rightMargin = 36;
                layoutParams.topMargin = 12;
                layoutParams.bottomMargin = 12;
                holder.itemView.setLayoutParams(layoutParams);
            }

            @Override
            protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText(String.valueOf(position));
            }
        });

        //精彩回看
        GridLayoutHelper backHelper = new GridLayoutHelper(1, 1);
        backHelper.setMargin(36, 110, 36, 20);
        adapters.add(new SubAdapter(getActivity(), backHelper, 1) {

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public void onBindViewHolder(final MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }

            @Override
            protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText("精彩回看");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toFragment2();
                    }
                });
            }

        });

        GridLayoutHelper helperBack = new GridLayoutHelper(2, mBackLists.size());
        adapters.add(new SubAdapter(getActivity(), helperBack, mBackLists.size()) {

            @Override
            public int getItemViewType(int position) {
                return 4;
            }

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = 36;
                layoutParams.rightMargin = 36;
                holder.itemView.setLayoutParams(layoutParams);
            }

            @Override
            protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
                ((TextView) holder.itemView.findViewById(R.id.tvTitle)).setText(String.valueOf(position));
            }
        });
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

    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;

        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
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
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (1 == viewType) {
                return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_one, parent, false));
            } else if (2 == viewType) {
                return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_two, parent, false));
            } else if (3 == viewType) {
                return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_coming, parent, false));
            } else if (4 == viewType) {
                return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_back, parent, false));
            } else {
                return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_title, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }

    }

    private void toFragment2() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment = new Fragment2();
        fm.beginTransaction().add(R.id.llContainer, fragment).hide(this).addToBackStack("x").commit();
    }

}
