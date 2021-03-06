package com.example.vanosidor.moxygithubrepositories.ui.ui.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

    @BindView(R.id.auth_form)
    View mAuthForm;

    @BindView(R.id.auth_email_et)
    AutoCompleteTextView mEmailView;

    @BindView(R.id.auth_password_et)
    EditText mPasswordView;

    @BindView(R.id.auth_progress)
    ProgressBar mProgressBar;

    @BindView(R.id.auth_button)
    Button mAuthButton;

    AlertDialog mErrorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ButterKnife.bind(this);

        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.ime_action_login || id == EditorInfo.IME_NULL) {
                tryLogin();
                return true;
            }
            return false;
        });

        mAuthButton.setOnClickListener(view -> tryLogin());
    }

    private void tryLogin() {
        mAuthPresenter.attemptLogin(mEmailView.getText().toString(),mPasswordView.getText().toString());
    }

    @Override
    public void showAuthProgress() {
        mAuthForm.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAuthProgress() {
        mAuthForm.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showAuthFieldsError(Integer emailError, Integer passwordError) {

        mEmailView.setError(emailError == null ?  null : getString(R.string.email_error));
        mPasswordView.setError(passwordError == null ?  null : getString(R.string.password_error));
    }

    @Override
    public void hideAuthFieldsError() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
    }

    @Override
    public void successLogin() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void failedLogin(String message) {
        mErrorDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setOnCancelListener(dialog -> mAuthPresenter.onErrorCancel())
                .show();
    }

    @Override
    public void hideLoginErrorDialog() {
        if(mErrorDialog!=null){
            mErrorDialog.dismiss();
        }
    }
}
