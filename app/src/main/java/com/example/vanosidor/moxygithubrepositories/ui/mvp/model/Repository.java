package com.example.vanosidor.moxygithubrepositories.ui.mvp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stend on 08.11.2017.
 */

@Entity(tableName = "repositories"/*,
        foreignKeys = @ForeignKey(
            entity = User.class,
            parentColumns = "_id",
            childColumns = "ownerId"),
        indices = @Index(
                value = "ownerId",
                name = "owner_id_idx",unique = true)*/)
public class Repository implements Serializable {

    public static class Column {
        static final String ID = "_id";
        static final String NAME = "name";
        static final String FULL_NAME = "full_name";
        static final String DESC = "desc";
        static final String URL = "url";
        static final String HTML_URL = "html_url";
        static final String OWNER = "ownerId";
        static final String IS_FORK = "is_fork";
        static final String IS_PRIVATE = "is_private";
        static final String CREATED_AT = "created_at";
        static final String UPDATED_AT = "updated_at";
        static final String PUSHED_AT = "pushed_at";
        static final String GIT_URL = "git_url";
        static final String SSH_URL = "ssh_url";
        static final String CLONE_URL = "clone_url";
        static final String SVN_URL = "svn_url";
        static final String HOMEPAGE = "homepage";
        static final String SIZE = "size";
        static final String STARS_COUNT = "stars_count";
        static final String WATCHERS_COUNT = "watchers_count";
        static final String LANGUAGE = "language";
        static final String HAS_ISSUES = "has_issues";
        static final String HAS_DOWNLOADS = "has_downloads";
        static final String HAS_WIKI = "has_wiki";
        static final String FORKS_COUNT = "forks_count";
        static final String OPEN_ISSUES_COUNT = "open_issues_count";
        static final String DEFAULT_BRANCH = "default_branch";
        static final String MIRROR_URL = "mirror_url";
    }

    @PrimaryKey
    @ColumnInfo(name = Column.ID)
    private int mId;

    @ColumnInfo(name = Column.NAME)
    private String mName;

    @ColumnInfo(name = Column.FULL_NAME)
    private String mFullName;

    @SerializedName("description")
    @ColumnInfo(name = Column.DESC)
    private String mDesc;

    @ColumnInfo(name = Column.URL)
    private String mUrl;

    @ColumnInfo(name = Column.HTML_URL)
    private String mHtmlUrl;

    @ColumnInfo(name = Column.OWNER)
    private int mOwnerId;

    @SerializedName("fork")
    @ColumnInfo(name = Column.IS_FORK)
    private boolean mIsFork;

    @SerializedName("private")
    @ColumnInfo(name = Column.IS_PRIVATE)
    private boolean mIsPrivate;

    @ColumnInfo(name = Column.CREATED_AT)
    private String mCreatedAt;

    @ColumnInfo(name = Column.UPDATED_AT)
    private String mUpdatedAt;

    @ColumnInfo(name = Column.PUSHED_AT)
    private String mPushedAt;

    @ColumnInfo(name = Column.GIT_URL)
    private String mGitUrl;

    @ColumnInfo(name = Column.SSH_URL)
    private String mSshUrl;

    @ColumnInfo(name = Column.CLONE_URL)
    private String mCloneUrl;

    @ColumnInfo(name = Column.SVN_URL)
    private String mSvnUrl;

    @ColumnInfo(name = Column.HOMEPAGE)
    private String mHomepage;

    @ColumnInfo(name = Column.SIZE)
    private long mSize;

    @SerializedName("stargazers_count")
    @ColumnInfo(name = Column.STARS_COUNT)
    private int mStarsCount;

    @ColumnInfo(name = Column.WATCHERS_COUNT)
    private int mWatchersCount;

    @ColumnInfo(name = Column.LANGUAGE)
    private String mLanguage;

    @ColumnInfo(name = Column.HAS_ISSUES)
    private boolean mHasIssues;

    @ColumnInfo(name = Column.HAS_DOWNLOADS)
    private boolean mHasDownloads;

    @ColumnInfo(name = Column.HAS_WIKI)
    private boolean mHasWiki;

    @ColumnInfo(name = Column.FORKS_COUNT)
    private int mForksCount;

    @ColumnInfo(name = Column.OPEN_ISSUES_COUNT)
    private int mOpenIssuesCount;

    @ColumnInfo(name = Column.DEFAULT_BRANCH)
    private String mDefaultBranch;

    @ColumnInfo(name=Column.MIRROR_URL)
    private String mMirrorUrl;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int ownerId) {
        this.mOwnerId = ownerId;
    }

    public boolean isFork() {
        return mIsFork;
    }

    public void setIsFork(boolean isFork) {
        mIsFork = isFork;
    }

    public boolean isPrivate() {return mIsPrivate;}

    public void setIsPrivate(boolean isPrivate) {
        mIsPrivate = isPrivate;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getPushedAt() {
        return mPushedAt;
    }

    public void setPushedAt(String pushedAt) {
        mPushedAt = pushedAt;
    }

    public String getGitUrl() {
        return mGitUrl;
    }

    public void setGitUrl(String gitUrl) {
        mGitUrl = gitUrl;
    }

    public String getSshUrl() {
        return mSshUrl;
    }

    public void setSshUrl(String sshUrl) {
        mSshUrl = sshUrl;
    }

    public String getCloneUrl() {
        return mCloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        mCloneUrl = cloneUrl;
    }

    public String getSvnUrl() {
        return mSvnUrl;
    }

    public void setSvnUrl(String svnUrl) {
        mSvnUrl = svnUrl;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public long getSize() {
        return mSize;
    }

    public void setSize(long size) {
        mSize = size;
    }

    public int getStarsCount() {
        return mStarsCount;
    }

    public void setStarsCount(int starsCount) {
        mStarsCount = starsCount;
    }

    public int getWatchersCount() {
        return mWatchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        mWatchersCount = watchersCount;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public boolean isHasIssues() {
        return mHasIssues;
    }

    public void setHasIssues(boolean hasIssues) {
        mHasIssues = hasIssues;
    }

    public boolean isHasDownloads() {
        return mHasDownloads;
    }

    public void setHasDownloads(boolean hasDownloads) {
        mHasDownloads = hasDownloads;
    }

    public boolean isHasWiki() {
        return mHasWiki;
    }

    public void setHasWiki(boolean hasWiki) {
        mHasWiki = hasWiki;
    }

    public int getForksCount() {
        return mForksCount;
    }

    public void setForksCount(int forksCount) {
        mForksCount = forksCount;
    }

    public int getOpenIssuesCount() {
        return mOpenIssuesCount;
    }

    public void setOpenIssuesCount(int openIssuesCount) {
        mOpenIssuesCount = openIssuesCount;
    }

    public String getDefaultBranch() {
        return mDefaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        mDefaultBranch = defaultBranch;
    }

    public String getMirrorUrl() {return mMirrorUrl;}

    public void setMirrorUrl(String mirrorUrl) {this.mMirrorUrl = mirrorUrl;}
}

