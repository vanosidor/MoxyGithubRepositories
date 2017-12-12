package com.example.vanosidor.moxygithubrepositories.ui.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.vanosidor.moxygithubrepositories.R;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.RepositoriesPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.presenter.SignOutPresenter;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.RepositoriesView;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.view.SignOutView;
import com.example.vanosidor.moxygithubrepositories.ui.ui.adapters.RepositoriesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivan on 06.11.2017.
 */

public class RepositoriesActivity extends MvpAppCompatActivity implements RepositoriesView,SignOutView {

    public static final String TAG = RepositoriesActivity.class.getSimpleName();

    @InjectPresenter
    SignOutPresenter mSignOutPresenter;

    @InjectPresenter
    RepositoriesPresenter mRepositoriesPresenter;

    @BindView(R.id.repositories_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.repositories_refresh_view)
    SwipeRefreshLayout mRefreshView;

    @BindView(R.id.repositories_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.no_repositories_text_view)
    TextView mNoRepositoriesTextView;

    @BindView(R.id.repositories_progress)
    ProgressBar mProgressBar;

    RepositoriesAdapter mRepositoriesAdapter;

    RecyclerView.OnScrollListener mOnScrolllistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_repositories);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRepositoriesAdapter = new RepositoriesAdapter(this);
        mRecyclerView.setAdapter(mRepositoriesAdapter);

        mRefreshView.setOnRefreshListener(() -> {
            mRepositoriesPresenter.loadRepositories(RepositoriesPresenter.State.REFRESH);
            Log.d(TAG, "Refresh:Item count = " + mRepositoriesAdapter.getItemCount());
        });

        mOnScrolllistener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(layoutManager.findLastCompletelyVisibleItemPosition() == mRepositoriesAdapter.getItemCount()-1){

                    Log.d(TAG, "onScrolled: Scroll to the end.Item count = " + mRepositoriesAdapter.getItemCount());
                    mRepositoriesPresenter.loadMoreRepositories(mRepositoriesAdapter.getItemCount());
                }
            }
        };
        mRecyclerView.addOnScrollListener(mOnScrolllistener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_repositories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_signOut:
                mSignOutPresenter.startSignOut();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void signOut() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        Log.d(TAG, "signOut");
    }

    @Override
    public void showData(List<Repository> repositories) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mNoRepositoriesTextView.setVisibility(View.GONE);
        mRepositoriesAdapter.setRepositories(repositories);
        Log.d(TAG, "showData.Item count = " + mRepositoriesAdapter.getItemCount());
    }

    @Override
    public void showMoreData(List<Repository> repositories) {
        mRepositoriesAdapter.addRepositories(repositories);
        Log.d(TAG, "showMoreData.Item count = " + mRepositoriesAdapter.getItemCount());
    }

    @Override
    public void showEmptyData() {
        Log.d(TAG, "showEmptyData");
        mNoRepositoriesTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRefreshView() {
        mRecyclerView.clearOnScrollListeners();
        mRepositoriesAdapter.clearRepositories();
        mRefreshView.setRefreshing(true);
        Log.d(TAG, "Start refreshing");
    }

    @Override
    public void hideRefreshView() {
        mRecyclerView.addOnScrollListener(mOnScrolllistener);
        Log.d(TAG, "Stop refreshing");
        mRefreshView.setRefreshing(false);
    }

    @Override
    public void showLoadingProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRefreshView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mRefreshView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadMoreProgress() {
        mRecyclerView.clearOnScrollListeners();
        mRefreshView.setEnabled(false);
        mRepositoriesAdapter.showLoadMoreAnimation();
    }

    @Override
    public void hideLoadMoreProgress() {
        mRecyclerView.addOnScrollListener(mOnScrolllistener);
        mRefreshView.setEnabled(true);
        mRepositoriesAdapter.hideLoadMoreAnimation();
    }

}
