package com.alibaba.android.vlayout.example;

public class ComponentHolder {
    private static DemoComponent sDemoComponent;

    private ComponentHolder() {
        //hide
    }

    public static void setLiveComponent(DemoComponent demoComponent) {
        sDemoComponent = demoComponent;
    }

    public static DemoComponent getLiveComponent() {
        return sDemoComponent;
    }

}
