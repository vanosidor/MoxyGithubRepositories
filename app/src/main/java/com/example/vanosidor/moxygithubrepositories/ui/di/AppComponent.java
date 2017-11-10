package com.example.vanosidor.moxygithubrepositories.ui.di;

import android.content.Context;

import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.ApiModule;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.ContextModule;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.GithubModule;
import com.example.vanosidor.moxygithubrepositories.ui.di.module.RetrofitModule;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.AuthPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.RepositoriesPresenter;

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

    void inject(AuthPresenter authPresenter);
    void inject(RepositoriesPresenter repositoriesPresenter);
}
