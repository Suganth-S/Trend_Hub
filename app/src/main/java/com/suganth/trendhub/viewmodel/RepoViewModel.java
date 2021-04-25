package com.suganth.trendhub.viewmodel;

import android.app.Application;

import com.suganth.trendhub.model.RepoModel;
import com.suganth.trendhub.repository.ReposRepository;

import java.util.List;

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

   public LiveData<List<RepoModel>> getAllRepos()
    {
        return mRepoModel;
    }

}
