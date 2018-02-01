package com.alibaba.android.vlayout.example;

import android.app.Application;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DemoComponent demoComponent = DaggerDemoComponent.builder().demoModule(new DemoModule(this)).build();
        ComponentHolder.setLiveComponent(demoComponent);
    }
}
