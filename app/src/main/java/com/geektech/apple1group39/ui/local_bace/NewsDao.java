package com.geektech.apple1group39.ui.local_bace;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.geektech.apple1group39.ui.models.News;

import java.util.List;

@Dao
public interface NewsDao {


    @Query("SELECT * FROM news")
    List<News>getAll();

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Query("SELECT * FROM news order by title")
    List<News>sortAll();
}
