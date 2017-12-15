package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.example.vanosidor.moxygithubrepositories.R;
import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;
import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.database.UserDao;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.utils.AuthUtils;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.AuthView;

import android.util.Base64;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Ivan on 06.11.2017.
 */

@InjectViewState
public class AuthPresenter extends BasePresenter<AuthView>{

    @Inject
    GithubService githubService;

    @Inject
    UserDao userDao;

    public AuthPresenter() {
        GithubApp.getAppComponent().inject(this);
    }

    public void attemptLogin(String email, String password){
        Integer emailError = null;
        Integer passwordError =null;

        //put validation mail here
        if(TextUtils.isEmpty(email) /*|| !isValidEmail(email)*/){
            emailError = R.string.email_error;
        }

        if(TextUtils.isEmpty(password)){
            passwordError = R.string.password_error;
        }

        if(passwordError!=null || emailError != null){
            getViewState().showAuthFieldsError(emailError,passwordError);
            return;
        }

        getViewState().showAuthProgress();

        String credentials = String.format("%s:%s",email,password);
        final String token = "Basic "+ Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);

        Disposable disposable = githubService.signIn(token)
                .doOnSuccess(
                        user->{
                            AuthUtils.setToken(token);
                            userDao.clearAll();
                            userDao.insert(user);
                        }
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    getViewState().hideAuthProgress();
                    getViewState().successLogin();
                },exception->{
                    getViewState().hideAuthProgress();
                    getViewState().failedLogin(exception.getMessage());
                });

        unsubscribeOnDestroy(disposable);
    }


    //validation mail
    /*private  boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }*/

    public void onErrorCancel() {
        getViewState().hideLoginErrorDialog();
    }
}
