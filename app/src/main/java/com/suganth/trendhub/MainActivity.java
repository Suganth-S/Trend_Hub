package com.suganth.trendhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.suganth.trendhub.adapter.RepoAdapter;
import com.suganth.trendhub.model.RepoModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RepoModel repoModel;
    private RecyclerView recyclerView;
    private List<RepoModel> repoList;
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
        repoList = new ArrayList<>();
        repoAdapter = new RepoAdapter(this, repoList);
        recyclerView.setAdapter(repoAdapter);
        repoAdapter.getAllRepos(repoList);
    }
}