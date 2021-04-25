package com.suganth.trendhub.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "repositories")
public class RepoModel {
    @PrimaryKey(autoGenerate = true)
    private int repoId;

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
    private String stars;

    @SerializedName("languageColor")
    @ColumnInfo(name = "languageColor")
    private String color;

    public RepoModel(String name, String description, String language, String stars, String color) {
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

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    @NonNull
    @Override
    public String toString() {
        return "RepoModel{" +
                ", repoId=" + repoId +
                ", name=" + name +
                ", description=" + description+
                ", language='" + language + '\'' +
                ", star='" + stars + '\'' +
                ", color=" + color +
                '}';

    }
}
