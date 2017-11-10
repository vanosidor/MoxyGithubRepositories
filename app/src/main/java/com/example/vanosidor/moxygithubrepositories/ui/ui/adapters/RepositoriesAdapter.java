package com.example.vanosidor.moxygithubrepositories.ui.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class RepositoriesAdapter extends RecyclerView.Adapter <RepositoriesAdapter.RepositoriesViewHolder> {

    private List<Repository> mRepositories = new ArrayList<>();

    private Context mContext;

    public RepositoriesAdapter(Context context) {
        mContext=context;
    }

    public void setRepositoriesToAdapter(List<Repository> repositories){
        mRepositories.clear();
        mRepositories.addAll(repositories);
        notifyDataSetChanged();
    }

    @Override
    public RepositoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.repository_item,parent,false);
        return new RepositoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepositoriesViewHolder holder, int position) {
        holder.textView.setText(mRepositories.get(position).getName());
//        holder.textView2.setText(mRepositories.get(position).getId());
    }

    @Override
    public int getItemCount() {
        if(mRepositories != null) {
            if(mRepositories.size()!=0) return mRepositories.size();
            else return 0;
        }
        else return 0;
    }


    class RepositoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView textView;

        @BindView(R.id.textView2)
        TextView textView2;


        public RepositoriesViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}



