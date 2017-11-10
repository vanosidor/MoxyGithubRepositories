package com.example.vanosidor.moxygithubrepositories.ui.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;

import java.util.List;

/**
 * Created by Ivan on 06.11.2017.
 */

public interface RepositoriesView extends MvpView {

    void showData(List<Repository> repositories);

    void showEmptyData();

    void showError(Throwable t);

    void showRefreshView();

    void hideRefreshView();

    void showLoadingProgressBar();

    void hideLoadingProgressBar();

}
