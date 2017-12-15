package com.example.vanosidor.moxygithubrepositories.ui;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Ivan on 06.11.2017.
 */

public class GithubService {


    private GithubApi mGithubApi;


    public GithubService(GithubApi githubApi) {
        this.mGithubApi = githubApi;
    }

    public Single<User> signIn(String token) {
        return mGithubApi.signIn(token);
    }

    public Observable <List<Repository>> getRepositories(String userName, int page, int pageSize) {
        return mGithubApi.getUsersRepositories(userName,page,pageSize);}
}
