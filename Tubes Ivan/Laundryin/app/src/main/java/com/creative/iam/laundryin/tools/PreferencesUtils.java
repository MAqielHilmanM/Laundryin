package com.creative.iam.laundryin.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesUtils {
    private static String SETTING_NAME= "laundryin";
    private SharedPreferences sp;

    public PreferencesUtils(Context context) {
//        sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp = context.getSharedPreferences(SETTING_NAME,Context.MODE_PRIVATE);
    }

    public void set(String key, String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public void clear(){
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public String get(String key, String def){
        return sp.getString(key,def);
    }
}
