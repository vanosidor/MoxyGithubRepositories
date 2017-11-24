package com.example.vanosidor.moxygithubrepositories.ui.mvp.data;

import com.example.vanosidor.moxygithubrepositories.R;
import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;
import com.example.vanosidor.moxygithubrepositories.ui.mvp.model.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Stend on 24.11.2017.
 */

public class TestDataSource implements RepositoryDataSource {

    @Inject
    Gson mGson;

    public TestDataSource() {
        GithubApp.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<Repository>> getRepositories(String name, int page, int pageSize) {

        String jsonString = getGsonFromLocalRawResource(R.raw.github_repo_example);

        List<Repository> repositoriesFromJson = mGson.fromJson(jsonString, new TypeToken<List<Repository>>(){}.getType());

        int countRepos = repositoriesFromJson.size();

        List <Repository> resultRepositories;

        if(page*pageSize<=countRepos){
            resultRepositories = repositoriesFromJson.subList((page-1)*pageSize,page*pageSize);
        }
        else if(countRepos>(page-1)*pageSize && countRepos<page*pageSize){
            resultRepositories = repositoriesFromJson.subList((page-1)*pageSize,countRepos-1);
        }
        else resultRepositories = new ArrayList<>();

        return Observable.just(resultRepositories);
    }

    private String getGsonFromLocalRawResource(int rawResourceId){
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = GithubApp.getAppComponent().getContext().getResources().openRawResource(R.raw.github_repo_example)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return writer.toString();
    }
}
