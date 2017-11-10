package com.example.vanosidor.moxygithubrepositories.ui;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ivan on 06.11.2017.
 */

public interface GithubApi {

    @GET("/user")
    Observable<User> signIn(@Header("Authorization") String token);

    @GET("/users/{login}/repos")
    Observable<List<Repository>> getUsersRepositories(@Path("login") String username,@Query("page") int page, @Query("per_page") int pageSize);
}
