package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import android.text.TextUtils;

import com.arellomobile.mvp.MvpPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.utils.AuthUtils;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.SplashView;

/**
 * Created by Ivan on 06.11.2017.
 */

public class SplashPresenter extends MvpPresenter <SplashView> {
    @Override
    public void attachView(SplashView view) {
        super.attachView(view);

        view.setAuthorized(!TextUtils.isEmpty(AuthUtils.getToken()));
        //logout();
        //for test
        //view.setAuthorized(false);
        //view.setAuthorized(true);
    }

    //logout
    public void logout(){
        AuthUtils.setToken(null);
    }

}
