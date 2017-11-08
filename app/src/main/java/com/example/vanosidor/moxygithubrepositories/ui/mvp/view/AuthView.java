package com.example.vanosidor.moxygithubrepositories.ui.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Ivan on 06.11.2017.
 */

public interface AuthView extends MvpView {

    void showAuthFieldsError(Integer emailError, Integer passwordError);

    void hideAuthFieldsError();

    void showAuthProgress();

    void hideAuthProgress();

    void successLogin();

    void failedLogin(String message);

    void hideLoginErrorDialog();
}
