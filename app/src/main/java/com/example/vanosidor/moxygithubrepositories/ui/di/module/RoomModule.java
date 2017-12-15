package com.example.vanosidor.moxygithubrepositories.ui.di.module;

import android.arch.persistence.room.Room;

import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;
import com.example.vanosidor.moxygithubrepositories.ui.database.AppDatabase;
import com.example.vanosidor.moxygithubrepositories.ui.database.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stend on 13.12.2017.
 */
@Module
public class RoomModule {

    private AppDatabase appDatabase;

    public RoomModule(GithubApp application){
        appDatabase = Room.databaseBuilder(application,AppDatabase.class,"repos.db").build();
    }

    @Singleton
    @Provides
    UserDao providesProductDao(AppDatabase demoDatabase) {
        return demoDatabase.userDao();
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

}
