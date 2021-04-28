package com.suganth.trendhub.dao;

import com.suganth.trendhub.model.RepoModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * RepoDao - A Dao class which handles method in database
 * getAllRepos() - to fetch locally stored data
 * deleteAll() - to delete all stored datas
 * searchByname() - to filter repos according to name
 */
@Dao
public interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RepoModel> repoList);

    @Query("SELECT * FROM repositories")
    LiveData<List<RepoModel>> getAllRepos();

    @Query("DELETE FROM repositories")
    void deleteAll();

    @Query("SELECT * FROM repositories WHERE name  == :name")
    RepoModel searchByName (String name);
}
