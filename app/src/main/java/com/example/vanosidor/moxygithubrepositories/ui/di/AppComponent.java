package com.example.vanosidor.moxygithubrepositories.ui.di;

import android.content.Context;

import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.ContextModule;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.GithubModule;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.data.NetworkDataSource;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.data.TestDataSource;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.AuthPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.RepositoriesPresenter;
import com.google.gson.Gson;

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
    Gson gson();

    void inject(AuthPresenter authPresenter);

    void inject(TestDataSource testDataSource);
    void inject(NetworkDataSource testDataSource);
}
