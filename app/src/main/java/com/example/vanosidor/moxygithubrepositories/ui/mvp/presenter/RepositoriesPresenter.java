package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;
import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.data.NetworkDataSource;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.data.RepositoryDataSource;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.data.TestDataSource;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.RepositoriesView;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    private int mCurrentPageCount;

    private boolean mIsLoading;

    public RepositoriesPresenter() {

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        Log.d(TAG, "onFirstViewAttach");
        loadRepositories(State.FIRSTLOADING);
    }

    public void loadRepositories(State state){
        loadData(state,1);
    }

    public void loadMoreRepositories(int repositoriesCount){

        //loadingStart(State.LOADMORE);

        if(mCurrentPageCount >= PAGE_SIZE){
            int page = repositoriesCount / PAGE_SIZE + 1;
            Log.d(TAG, "loadMoreRepositories.Page number = " + page);
            loadData(State.LOADMORE,page);
        }
    }


    private void loadData(State state, int page) {

        //https://api.github.com/users/JakeWharton/repos?page=2&&per_page=50

        //change real data to test data from local json
        RepositoryDataSource dataSource = new TestDataSource();
        //RepositoryDataSource dataSource = new NetworkDataSource();

        //Load from network
        if (mIsLoading) {
            return;
        }

        loadingStart(state);

        Observable <List<Repository>> repositoriesObservable =  dataSource.getRepositories("JakeWharton",page,PAGE_SIZE);
        //Observable<List<Repository>> repositoriesObservable = mGithubService.getRepositories("JakeWharton",page,PAGE_SIZE);

        Subscription subscription = repositoriesObservable
                .subscribeOn(Schedulers.io())
                //.doOnSubscribe(this::showLoadingView)
                .delay(500, TimeUnit.MILLISECONDS) //imitation of slow download
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

        Log.d(TAG, "loadingSuccess: loaded "+repositories.size()+" items");

        mCurrentPageCount = repositories.size();

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
