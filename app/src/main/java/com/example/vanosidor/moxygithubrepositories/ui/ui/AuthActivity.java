package com.example.vanosidor.moxygithubrepositories.ui.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.vanosidor.moxygithubrepositories.R;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.AuthPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.AuthView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivan on 06.11.2017.
 */

public class AuthActivity extends MvpAppCompatActivity implements AuthView{

    @InjectPresenter
    AuthPresenter mAuthPresenter;

    @BindView(R.id.auth_email_et)
    EditText mEmailView;

    @BindView(R.id.auth_password_et)
    EditText mPasswordView;

    @BindView(R.id.auth_progress)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ButterKnife.bind(this);
    }
}
