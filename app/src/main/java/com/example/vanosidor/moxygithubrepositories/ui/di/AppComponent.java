package com.example.vanosidor.moxygithubrepositories.ui.di;

import android.content.Context;

import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.ApiModule;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.ContextModule;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.GithubModule;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ivan on 06.11.2017.
 */
@Singleton
@Component(modules = {GithubModule.class, ContextModule.class})
public interface AppComponent {
    Context getContext();
    GithubService githubService();
}
