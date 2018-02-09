package com.alibaba.android.vlayout.example;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DemoModule.class})
public interface DemoComponent {
    void inject(DemoActivity demoActivity);
    void inject(Fragment1 fragment1);
    void inject(Fragment2 fragment2);
}
