package com.example.looking.base;

import android.app.Application;

import com.example.looking.di.components.ApplicationComponent;
import com.example.looking.di.components.DaggerApplicationComponent;

public class App extends Application {
    private static App mInstance;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static App getInstance() {
        return mInstance;
    }

    public ApplicationComponent getApplicationComponent(){
        if(applicationComponent == null){
            applicationComponent = DaggerApplicationComponent.create();
        }
        return applicationComponent;
    }
}
