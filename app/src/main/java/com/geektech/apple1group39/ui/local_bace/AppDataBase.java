package com.geektech.apple1group39.ui.local_bace;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geektech.apple1group39.ui.models.News;

@Database(entities = {News.class},version = 1)
public abstract class AppDataBase extends RoomDatabase   {
    public abstract NewsDao newsDao();
}


