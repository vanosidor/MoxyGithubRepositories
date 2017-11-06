package com.example.vanosidor.moxygithubrepositories.ui.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.vanosidor.moxygithubrepositories.R;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.SplashPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.SplashView;

public class SplashActivity extends MvpAppCompatActivity implements SplashView{

    @InjectPresenter
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        startActivity(new Intent(this,isAuthorized ? RepositoriesActivity.class : AuthActivity.class));
    }
}
