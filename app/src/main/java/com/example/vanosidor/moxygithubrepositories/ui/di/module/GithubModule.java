package com.example.vanosidor.moxygithubrepositories.ui.di.module;

import com.example.vanosidor.moxygithubrepositories.ui.GithubApi;
import com.example.vanosidor.moxygithubrepositories.ui.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ivan on 06.11.2017.
 */

@Module(includes = {ApiModule.class})
public class GithubModule {
    @Provides
    @Singleton
    public GithubService provideGithubService(GithubApi githubApi){
           return new GithubService(githubApi);
    }
}
