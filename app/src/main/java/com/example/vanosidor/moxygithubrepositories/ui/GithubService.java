package com.example.vanosidor.moxygithubrepositories.ui;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Ivan on 06.11.2017.
 */

public class GithubService {


    private GithubApi mGithubApi;


    public GithubService(GithubApi githubApi) {
        this.mGithubApi = githubApi;
    }

    public Observable<User> signIn(String token) {
        return mGithubApi.signIn(token);
    }
}
