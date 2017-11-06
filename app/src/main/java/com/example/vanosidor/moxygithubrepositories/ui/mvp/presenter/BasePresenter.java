package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ivan on 06.11.2017.
 */

public class BasePresenter <View extends MvpView> extends MvpPresenter <View> {

    private CompositeSubscription mCompositeSubscription;

    protected void unsubscribeOnDestroy(@NonNull Subscription subscription){
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.clear();
    }
}
