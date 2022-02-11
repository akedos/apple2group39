package com.geektech.apple1group39.ui;

import android.app.Application;

import androidx.room.Room;

import com.geektech.apple1group39.ui.local_bace.AppDataBase;

public class App extends Application {
    public static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.databaseBuilder(this, AppDataBase.class, "myDataBase").allowMainThreadQueries()
                .build();
    }
    public static AppDataBase getAppDataBase(){
        return appDataBase;
    }
}
