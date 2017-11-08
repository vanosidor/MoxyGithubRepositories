package com.example.vanosidor.moxygithubrepositories.ui;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;



import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by Ivan on 06.11.2017.
 */

public interface GithubApi {

    @GET("/user")
    Observable <User> signIn(@Header("Authorization") String token);
}
