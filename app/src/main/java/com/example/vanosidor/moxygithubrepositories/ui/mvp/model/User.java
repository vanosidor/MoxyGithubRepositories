package com.example.vanosidor.moxygithubrepositories.ui.mvp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ivan on 06.11.2017.
 */

@Entity(tableName = "user")
public class User {

    public static class Column {
        static final String ID = "_id";
        static final String LOGIN = "login";
        static final String AVATAR = "avatar";
        static final String PUBLIC_REPOS = "public_repos";
        static final String PUBLIC_GISTS = "public_gists";
        static final String FOLLOWERS = "followers";
        static final String FOLLOWING = "following";
    }

    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = Column.ID)
    private int mId;

    @ColumnInfo(name = Column.LOGIN)
    private String mLogin;

    @ColumnInfo(name = Column.AVATAR)
    private String mAvatarUrl;

    @ColumnInfo(name = Column.PUBLIC_REPOS)
    private int mPublicRepos;

    @ColumnInfo(name = Column.PUBLIC_GISTS)
    private int mPublicGists;

    @ColumnInfo(name = Column.FOLLOWERS)
    private int mFollower;

    @ColumnInfo(name = Column.FOLLOWING)
    private int mFollowing;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String mLogin) {
        this.mLogin = mLogin;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public int getPublicRepos() {
        return mPublicRepos;
    }

    public int getPublicGists() {
        return mPublicGists;
    }

    public int getFollower() {
        return mFollower;
    }

    public int getFollowing() {
        return mFollowing;
    }

    public void setAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }

    public void setPublicRepos(int mPublicRepos) {
        this.mPublicRepos = mPublicRepos;
    }

    public void setPublicGists(int mPublicGists) {
        this.mPublicGists = mPublicGists;
    }

    public void setFollower(int mFollower) {
        this.mFollower = mFollower;
    }

    public void setFollowing(int mFollowing) {
        this.mFollowing = mFollowing;
    }
}
