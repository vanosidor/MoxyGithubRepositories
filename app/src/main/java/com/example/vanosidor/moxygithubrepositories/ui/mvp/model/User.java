package com.example.vanosidor.moxygithubrepositories.ui.mvp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ivan on 06.11.2017.
 */

@DatabaseTable
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

    @DatabaseField(columnName = Column.ID, id = true)
    private int mId;
    @DatabaseField(columnName = Column.LOGIN)
    private String mLogin;
    @DatabaseField(columnName = Column.AVATAR)
    private String mAvatarUrl;
    @DatabaseField(columnName = Column.PUBLIC_REPOS)
    private int mPublicRepos;
    @DatabaseField(columnName = Column.PUBLIC_GISTS)
    private int mPublicGists;
    @DatabaseField(columnName = Column.FOLLOWERS)
    private int mFollower;
    @DatabaseField(columnName = Column.FOLLOWING)
    private int mFollowing;

    public int getId() {
        return mId;
    }

    public int setId() {
        return mId;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String mLogin) {
        this.mLogin = mLogin;
    }
}
