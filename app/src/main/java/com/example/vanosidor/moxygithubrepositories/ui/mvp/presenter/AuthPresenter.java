package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.AuthView;

import javax.inject.Inject;

/**
 * Created by Ivan on 06.11.2017.
 */

@InjectViewState
public class AuthPresenter extends BasePresenter<AuthView>{

    @Inject
    GithubService githubService;


}
