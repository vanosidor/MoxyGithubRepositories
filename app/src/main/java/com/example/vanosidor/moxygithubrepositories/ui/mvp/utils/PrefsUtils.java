package com.example.vanosidor.moxygithubrepositories.ui.mvp.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.vanosidor.moxygithubrepositories.ui.GithubApp;

/**
 * Created by Ivan on 06.11.2017.
 */

public class PrefsUtils {
    public static final String PREF_NAME = "github";

    public static SharedPreferences getPrefs(){
        return GithubApp.getAppComponent().getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor(){
        return getPrefs().edit();
    }
}
