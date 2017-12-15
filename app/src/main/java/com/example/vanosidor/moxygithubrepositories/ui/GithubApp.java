package com.example.vanosidor.moxygithubrepositories.ui;

import android.app.Application;
import android.content.Context;

import com.example.vanosidor.moxygithubrepositories.ui.di.AppComponent;


import com.example.vanosidor.moxygithubrepositories.ui.di.DaggerAppComponent;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.ContextModule;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.RoomModule;

/**
 * Created by Ivan on 06.11.2017.
 */

public class GithubApp extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }

    public static AppComponent getAppComponent(){
        return sAppComponent;
    }
}
