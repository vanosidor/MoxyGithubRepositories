package com.example.vanosidor.moxygithubrepositories.ui.di.module;

import com.example.vanosidor.moxygithubrepositories.ui.GithubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Ivan on 06.11.2017.
 */

@Module(includes = {RetrofitModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public GithubApi provideGithubApi(Retrofit retrofit){
        return retrofit.create(GithubApi.class);
    }
}
