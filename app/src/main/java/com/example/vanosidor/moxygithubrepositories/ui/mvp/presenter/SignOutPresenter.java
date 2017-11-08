package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.utils.AuthUtils;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.SignOutView;

/**
 * Created by Stend on 08.11.2017.
 */

@InjectViewState
public class SignOutPresenter extends MvpPresenter <SignOutView> {
    public void startSignOut(){
        AuthUtils.setToken(null);
        getViewState().signOut();
    }
}
