package com.example.vanosidor.moxygithubrepositories.ui.mvp.model;

/**
 * Created by Stend on 08.11.2017.
 */


public class Repository {

    private String name;
    private String data;

    public Repository(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

