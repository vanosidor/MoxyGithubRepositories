package com.example.vanosidor.moxygithubrepositories.ui.mvp.data;

import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;
import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Stend on 24.11.2017.
 */

public class NetworkDataSource implements RepositoryDataSource {


    @Inject
    GithubService mGithubService;

    public NetworkDataSource() {
        GithubApp.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<Repository>> getRepositories(String name, int page, int pageSize) {
        return mGithubService.getRepositories("JakeWharton",page,pageSize);
    }
}
