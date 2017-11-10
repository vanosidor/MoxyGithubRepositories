package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;
import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.RepositoriesView;

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

    private static final int PAGE_SIZE = 50;
    private static final String TAG = RepositoriesPresenter.class.getSimpleName();

    public enum State {FIRSTLOADING,REFRESH,LOADMORE}

    @Inject
    GithubService mGithubService;

    private boolean mIsLoading;

    public RepositoriesPresenter() {
        GithubApp.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        loadRepositories(State.FIRSTLOADING);
    }

    public void loadRepositories(State state){
        loadData(state,1);
    }

    public void loadMoreRepositories(int currentPageCount){

        int page = currentPageCount / PAGE_SIZE + 1;
        loadData(State.LOADMORE,page);
    }

    private void loadData(State state, int page) {

        if (mIsLoading) {
            return;
        }

        loadingStart(state);

        Observable<List<Repository>> repositoriesObservable = mGithubService.getRepositories("JakeWharton",page,PAGE_SIZE);

        Subscription subscription = repositoriesObservable
                .subscribeOn(Schedulers.io())
                //.doOnSubscribe(this::showLoadingView)
                .delay(3000, TimeUnit.MILLISECONDS) //imitation of slow download
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositories -> {
                            //repositories=new ArrayList<>(); //test no repositories
                            loadingFinish(state);
                            //loadingError(new Throwable("test error!")); //for test error comment next string
                            loadingSuccess(repositories,state);
                        },
                        throwable -> {
                            loadingFinish(state);
                            loadingError(throwable);
                });

        unsubscribeOnDestroy(subscription);
    }

    private void loadingStart(State state) {

        switch (state){
            case FIRSTLOADING: getViewState().showLoadingProgressBar();break;
            case REFRESH:getViewState().showRefreshView();break;
            case LOADMORE:getViewState().showLoadMoreProgress();break;
        }
    }

    private void loadingFinish(State state) {

        mIsLoading = false;

        switch (state){
            case FIRSTLOADING: getViewState().hideLoadingProgressBar();break;
            case REFRESH:getViewState().hideRefreshView();break;
            case LOADMORE:getViewState().hideLoadMoreProgress();break;
        }
    }

    private void loadingError(Throwable throwable) {
        getViewState().showError(throwable);
    }

    private void loadingSuccess(List<Repository> repositories,State state) {

        if(state!= State.LOADMORE){
            if(repositories.size()==0) getViewState().showEmptyData();
            else getViewState().showData(repositories);
        }
        else {
            getViewState().showMoreData(repositories);
            //Log.d(TAG, "loadingSuccess: show more data");
        }
    }
}
