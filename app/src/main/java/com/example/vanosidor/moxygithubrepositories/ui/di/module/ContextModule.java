package com.example.vanosidor.moxygithubrepositories.ui.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ivan on 06.11.2017.
 */

@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context context){
        this.mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){return mContext;}
}
