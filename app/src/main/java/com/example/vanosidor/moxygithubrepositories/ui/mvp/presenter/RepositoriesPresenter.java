package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;
import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.RepositoriesView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ivan on 06.11.2017.
 */

@InjectViewState
public class RepositoriesPresenter extends BasePresenter<RepositoriesView> {

    private static final String TAG = RepositoriesPresenter.class.getSimpleName();

    @Inject
    GithubService mGithubService;

    private boolean mIsLoading;

    public RepositoriesPresenter() {
        GithubApp.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        loadRepositories(false);
    }

    public void loadRepositories(boolean isRefresh) {

        if (mIsLoading) {
            return;
        }

        loadingStart(isRefresh);

        Observable<List<Repository>> repositoriesObservable = mGithubService.getRepositories("JakeWharton");

        Subscription subscription = repositoriesObservable
                .subscribeOn(Schedulers.io())
                //.doOnSubscribe(this::showLoadingView)
                .delay(3000, TimeUnit.MILLISECONDS) //imitation of slow download
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositories -> {
                            //repositories=new ArrayList<>(); //test no repositories
                            loadingFinish(isRefresh);
                            //loadingError(new Throwable("test error!")); //for test error comment next string
                            loadingSuccess(repositories);
                        },
                        throwable -> {
                            loadingFinish(isRefresh);
                            loadingError(throwable);
                });

        unsubscribeOnDestroy(subscription);
    }

    private void loadingStart(boolean isRefreshing) {
        if(isRefreshing) getViewState().showRefreshView();
        else getViewState().showLoadingProgressBar();
    }

    private void loadingError(Throwable throwable) {
        getViewState().showError(throwable);
    }

    private void loadingSuccess(List<Repository> repositories) {

        if(repositories.size()==0) getViewState().showEmptyData();
        else getViewState().showData(repositories);
    }

    private void loadingFinish(boolean isRefreshing) {

        mIsLoading = false;

        if(isRefreshing) getViewState().hideRefreshView();
        else getViewState().hideLoadingProgressBar();
    }
}
