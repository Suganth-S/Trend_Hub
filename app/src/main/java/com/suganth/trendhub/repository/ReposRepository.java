package com.suganth.trendhub.repository;

import android.app.Application;

import com.suganth.trendhub.dao.RepoDao;
import com.suganth.trendhub.database.RepoDataBase;
import com.suganth.trendhub.model.RepoModel;

import java.util.List;
import java.util.concurrent.Future;

import androidx.lifecycle.LiveData;

public class ReposRepository {
    private RepoDao repoDao;
    private LiveData<List<RepoModel>> getAllRepos;

    public ReposRepository(Application application) {
        RepoDataBase dataBase = RepoDataBase.getInstance(application);
        repoDao = dataBase.repoDao();
        getAllRepos = repoDao.getAllRepos();
    }

   public  LiveData<List<RepoModel>> getAllRepos()
    {
        return getAllRepos;
    }

    public void insert(final List<RepoModel> repoModel){
            RepoDataBase.databaseWriteExecutor
                .execute(() -> repoDao.insert(repoModel));
    }

    public Future<RepoModel> searchByName (String name)
    {
        return RepoDataBase.databaseWriteExecutor.submit(()->repoDao.searchByName(name));
    }
}
