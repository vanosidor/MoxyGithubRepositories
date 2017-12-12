package com.example.vanosidor.moxygithubrepositories.ui.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
            RepositoriesViewHolder repositoriesViewHolder  = (RepositoriesViewHolder)holder;
            repositoriesViewHolder.textView.setText(mRepositories.get(position).getName());
            repositoriesViewHolder.textView2.setText(String.valueOf(position));
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

        @BindView(R.id.textView)
        TextView textView;

        @BindView(R.id.tv2)
        TextView textView2;


        public RepositoriesViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.loading_more_progress_bar)
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);

            ButterKnife.bind(this,itemView);
        }
    }
}



