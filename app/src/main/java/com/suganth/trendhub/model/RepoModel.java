package com.suganth.trendhub.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "repository")
public class RepoModel {

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("description")
    @ColumnInfo(name= "description")
    private String description;

    @SerializedName("language")
    @ColumnInfo(name = "language")
    private String language;

    @SerializedName("stars")
    @ColumnInfo(name = "stars")
    private long stars;

    @SerializedName("languageColor")
    @ColumnInfo(name = "languageColor")
    private String color;

    public RepoModel(String name, String description, String language, long stars, String color) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.stars = stars;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
