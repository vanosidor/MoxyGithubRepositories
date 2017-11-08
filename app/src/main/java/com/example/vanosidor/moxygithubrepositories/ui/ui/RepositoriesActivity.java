package com.example.vanosidor.moxygithubrepositories.ui.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivan on 06.11.2017.
 */

public class RepositoriesActivity extends MvpAppCompatActivity implements RepositoriesView,SignOutView {

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

    RepositoriesAdapter mRepositoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        List<Repository> mData = new ArrayList<>();
        mData.add(new Repository("adadadsad","sdadasdasd"));
        mData.add(new Repository("sdadasd","sdadsad"));
        mData.add(new Repository("asdsad","sdada"));

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRepositoriesAdapter = new RepositoriesAdapter(this,mData);
        mRecyclerView.setAdapter(mRepositoriesAdapter);
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
    }
}
