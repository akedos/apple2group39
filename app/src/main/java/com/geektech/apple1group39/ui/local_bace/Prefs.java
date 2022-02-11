package com.geektech.apple1group39.ui.local_bace;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context){
        preferences= context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
    }


    public void saveName(String name){
        preferences.edit().putString("name",name).apply();

    }
    public String getName(){
        return preferences.getString("name","");
    }

    public void saveImg(String img){
        preferences.edit().putString("img",img).apply();

    }
    public String getImg(){
        return preferences.getString("img","");
    }
}
