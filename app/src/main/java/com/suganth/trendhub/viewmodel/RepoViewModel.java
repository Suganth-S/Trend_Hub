package com.suganth.trendhub.viewmodel;

import android.app.Application;

import com.suganth.trendhub.model.RepoModel;
import com.suganth.trendhub.repository.ReposRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RepoViewModel extends AndroidViewModel {

    private ReposRepository reposRepository;
    private final LiveData<List<RepoModel>> mRepoModel;

    public RepoViewModel(@NonNull Application application) {
        super(application);
        reposRepository = new ReposRepository(application);
        mRepoModel = reposRepository.getAllRepos();
    }

    public LiveData<List<RepoModel>> getAllRepos() {
        return mRepoModel;
    }

    public RepoModel searchByName(String name) {
        RepoModel repoModel = null;
        try {
            repoModel = reposRepository.searchByName(name).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return repoModel;
    }

}
