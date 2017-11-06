package com.example.vanosidor.moxygithubrepositories.ui.mvp.utils;

/**
 * Created by Ivan on 06.11.2017.
 */

public class AuthUtils {

    public static final String TOKEN ="token";

    public static String getToken(){
        return PrefsUtils.getPrefs().getString(TOKEN,"");
    }

    public static void setToken(String token){
        PrefsUtils.getEditor().putString(TOKEN,token).commit();
    }
}
