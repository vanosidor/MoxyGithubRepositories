package com.example.vanosidor.moxygithubrepositories.ui.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;

import java.util.List;

/**
 * Created by Ivan on 06.11.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface RepositoriesView extends MvpView {

    void showData(List<Repository> repositories);

    @StateStrategyType(AddToEndStrategy.class)
    void showMoreData(List<Repository> repositories);

    void showEmptyData();

    void showError(Throwable t);

    void showRefreshView();

    void hideRefreshView();

    void showLoadingProgressBar();

    void hideLoadingProgressBar();

    void showLoadMoreProgress();

    void hideLoadMoreProgress();
}
