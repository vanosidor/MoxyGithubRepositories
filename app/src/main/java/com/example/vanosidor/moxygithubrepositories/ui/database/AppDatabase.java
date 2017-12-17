package com.example.vanosidor.moxygithubrepositories.ui.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;

/**
 * Created by Stend on 13.12.2017.
 */
@Database(entities = {User.class, Repository.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RepositoriesDao repositoriesDao();
}
