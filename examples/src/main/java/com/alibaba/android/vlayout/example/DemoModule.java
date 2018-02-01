package com.alibaba.android.vlayout.example;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class DemoModule {
    private final Context mContext;

    public DemoModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

}
