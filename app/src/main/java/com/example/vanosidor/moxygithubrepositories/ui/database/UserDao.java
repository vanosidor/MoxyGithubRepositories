package com.example.vanosidor.moxygithubrepositories.ui.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.User;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Stend on 13.12.2017.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    User getUser();

    /*@Query("SELECT * FROM user WHERE login = :userLogin")
    Single<User> getUserByLogin(String userLogin);*/

    @Query("DELETE FROM user")
    void clearAll();

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
