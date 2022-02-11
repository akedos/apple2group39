package com.geektech.apple1group39.ui.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class News implements Serializable {
    private String title;
    private String createdAt;

    @PrimaryKey(autoGenerate = true)
    private long id ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public News(String title, String createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
