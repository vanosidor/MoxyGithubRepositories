package com.example.vanosidor.moxygithubrepositories.ui.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vanosidor.moxygithubrepositories.R;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.vanosidor.moxygithubrepositories.ui.ui.view.OcticonTextView.ICON_FORK;
import static com.example.vanosidor.moxygithubrepositories.ui.ui.view.OcticonTextView.ICON_MIRROR_PRIVATE;
import static com.example.vanosidor.moxygithubrepositories.ui.ui.view.OcticonTextView.ICON_MIRROR_PUBLIC;
import static com.example.vanosidor.moxygithubrepositories.ui.ui.view.OcticonTextView.ICON_PRIVATE;
import static com.example.vanosidor.moxygithubrepositories.ui.ui.view.OcticonTextView.ICON_PUBLIC;

/**
 * Created by Stend on 08.11.2017.
 */

public class RepositoriesAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {

    public static final String TAG = RepositoriesAdapter.class.getSimpleName();
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private List<Repository> mRepositories = new ArrayList<>();

    private Context mContext;

    private int mProgressBarPosition;

    public RepositoriesAdapter(Context context) {
        mContext=context;
    }

    public void setRepositories(List<Repository> repositories){
        mRepositories = new ArrayList<>(repositories);
        notifyDataSetChanged();
    }

    public void addRepositories(List<Repository> repositories) {
        mRepositories.addAll(repositories);
        notifyDataSetChanged();
    }

    public void showLoadMoreAnimation(){

        mRepositories.add(null);
        mProgressBarPosition = mRepositories.size()-1;
        Log.d(TAG, "showLoadMoreAnimation.Recycler view at position = " + mProgressBarPosition);
        notifyItemInserted(mProgressBarPosition);
    }

    public void hideLoadMoreAnimation(){
        Log.d(TAG, "Hide Load More Animation. Deleted recycler view at position = " + (mRepositories.size()-1));

        if(mRepositories.get(mProgressBarPosition) == null){
            mRepositories.remove(mProgressBarPosition);
            notifyItemRemoved(mProgressBarPosition);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mRepositories.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == VIEW_TYPE_ITEM){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_repository,parent,false);
            return new RepositoriesViewHolder(itemView);
        }

        else if (viewType == VIEW_TYPE_LOADING) {
            View loadingView = LayoutInflater.from(mContext).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(loadingView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof RepositoriesViewHolder){

            RepositoriesViewHolder repoHolder  = (RepositoriesViewHolder)holder;
            Repository repo = mRepositories.get(position);

            String description = repo.getDesc();
            String language = repo.getLanguage();

            setRepoIcon(repoHolder.repoIcon,repo);
            repoHolder.repoName.setText(repo.getName());

            if(!TextUtils.isEmpty(description)) {
                repoHolder.repoDescription.setVisibility(View.VISIBLE);
                repoHolder.repoDescription.setText(repo.getDesc());
            } else {
                repoHolder.repoDescription.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(language)) {
                repoHolder.repoLanguage.setVisibility(View.VISIBLE);
                repoHolder.repoLanguage.setText(repo.getLanguage());
            } else {
                repoHolder.repoLanguage.setVisibility(View.GONE);
            }
            repoHolder.repoWatchersCount.setText(String.valueOf(repo.getWatchersCount()));
            repoHolder.repoForksCount.setText(String.valueOf(repo.getForksCount()));
        }
        else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        if(mRepositories != null) {
            if(mRepositories.size()!=0) return mRepositories.size();
            else return 0;
        }
        else return 0;
    }

    public void clearRepositories() {
        int countRepositories = mRepositories.size();
        mRepositories = new ArrayList<>();
        notifyItemRangeRemoved(0,countRepositories);
    }

    class RepositoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_icon)
        TextView repoIcon;

        @BindView(R.id.tv_repo_name)
        TextView repoName;

        @BindView(R.id.tv_repo_description)
        TextView repoDescription;

        @BindView(R.id.tv_watchers)
        TextView repoWatchersCount;

        @BindView(R.id.tv_forks)
        TextView repoForksCount;

        @BindView(R.id.tv_repo_language)
        TextView repoLanguage;


        RepositoriesViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.loading_more_progress_bar)
        ProgressBar progressBar;

        LoadingViewHolder(View view) {
            super(view);

            ButterKnife.bind(this,itemView);
        }
    }

    private void setRepoIcon(TextView iconView, Repository repository){
        if (TextUtils.isEmpty(repository.getMirrorUrl())) {
            if (repository.isPrivate()) {
                iconView.setText(ICON_PRIVATE);
            } else if (repository.isFork()) {
                iconView.setText(ICON_FORK);
            } else {
                iconView.setText(ICON_PUBLIC);
            }
        } else {
            if (repository.isPrivate()) {
                iconView.setText(ICON_MIRROR_PRIVATE);
            } else {
                iconView.setText(ICON_MIRROR_PUBLIC);
            }
        }

    }
}



