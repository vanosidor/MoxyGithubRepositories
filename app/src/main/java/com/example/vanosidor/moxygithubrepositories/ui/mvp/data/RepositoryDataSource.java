package com.example.vanosidor.moxygithubrepositories.ui.mvp.data;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Stend on 24.11.2017.
 */

public interface RepositoryDataSource {
    Observable<List<Repository>> getRepositories(String name, int page, int pageSize);
}
