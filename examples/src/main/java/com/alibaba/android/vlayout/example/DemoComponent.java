package com.alibaba.android.vlayout.example;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DemoModule.class})
public interface DemoComponent {
    void inject(ListActivity liveListActivity);
    void inject(ListFragment liveListFragment);
}
