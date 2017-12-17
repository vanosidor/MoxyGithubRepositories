package com.example.vanosidor.moxygithubrepositories.ui.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Ivan on 16.12.2017.
 */

@Dao
public interface RepositoriesDao {

    @Query("SELECT * FROM repositories")
    List<Repository> getRepositories();

    @Query("DELETE FROM repositories")
    void clearAllRepositories();

    @Insert
    void insert(List<Repository> repositories);

}
