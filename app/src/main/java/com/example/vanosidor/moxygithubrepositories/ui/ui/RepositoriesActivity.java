package com.example.vanosidor.moxygithubrepositories.ui.ui;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.vanosidor.moxygithubrepositories.R;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.RepositoriesPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.RepositoriesView;

/**
 * Created by Ivan on 06.11.2017.
 */

public class RepositoriesActivity extends MvpAppCompatActivity implements RepositoriesView {

    @InjectPresenter
    RepositoriesPresenter repositoriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
    }
}
