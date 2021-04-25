package com.suganth.trendhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.suganth.trendhub.adapter.RepoAdapter;
import com.suganth.trendhub.model.RepoModel;
import com.suganth.trendhub.network.Network;
import com.suganth.trendhub.repository.ReposRepository;
import com.suganth.trendhub.viewmodel.RepoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RepoViewModel repoViewModel;
    private RecyclerView recyclerView;
    private List<RepoModel> repoList;
    private ReposRepository reposRepository;
    private RepoAdapter repoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        reposRepository = new ReposRepository(getApplication());
        repoList = new ArrayList<>();
        repoAdapter = new RepoAdapter(this, repoList);

        repoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RepoViewModel.class);
        networkRequest();
        repoViewModel.getAllRepos().observe(this, new Observer<List<RepoModel>>() {
            @Override
            public void onChanged(List<RepoModel> repoList) {
                recyclerView.setAdapter(repoAdapter);
                repoAdapter.getAllRepos(repoList);
                Log.d("main", "onChanged: "+repoList);
            }
        });
    }

    private void networkRequest() {
        Network network = new Network();
        Call<List<RepoModel>> call = network.api.getAllRepos();
        call.enqueue(new Callback<List<RepoModel>>() {
            @Override
            public void onResponse(Call<List<RepoModel>> call, Response<List<RepoModel>> response) {
                if (response.isSuccessful()) {
                    reposRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RepoModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}