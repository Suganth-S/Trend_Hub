package com.suganth.trendhub.network;

import com.suganth.trendhub.model.RepoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("repositories")
    Call<List<RepoModel>> getAllRepos();
}
