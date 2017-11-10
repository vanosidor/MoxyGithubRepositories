package com.example.vanosidor.moxygithubrepositories.ui;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;

import java.util.List;

import javax.inject.Inject;

import retrofit2.http.GET;
import retrofit2.http.Path;
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

    public Observable <List<Repository>> getRepositories(String userName, int page, int pageSize) {
        return mGithubApi.getUsersRepositories(userName,page,pageSize);}
}
