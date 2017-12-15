package com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;
import com.example.vanosidor.moxygithubrepositories.ui.GithubService;
import com.example.vanosidor.moxygithubrepositories.ui.database.UserDao;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.data.NetworkDataSource;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.data.RepositoryDataSource;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.data.TestDataSource;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.RepositoriesView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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

    @Inject
    UserDao userDao;

    public RepositoriesPresenter() {
        GithubApp.getAppComponent().inject(this);
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

        String userName = "JakeWharton";

        //change real data to test data from local json
        RepositoryDataSource dataSource = new TestDataSource();
        //RepositoryDataSource dataSource = new NetworkDataSource();

        //Load from network
        if (mIsLoading) {
            return;
        }

        loadingStart(state);

        Observable<List<Repository>> repositoriesObservable =  dataSource.getRepositories(userName,page,PAGE_SIZE);

        Disposable disposable = repositoriesObservable
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

        unsubscribeOnDestroy(disposable);
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
        }
    }
}
