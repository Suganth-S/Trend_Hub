package com.suganth.trendhub.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.SearchView;
import android.widget.Toast;

import com.suganth.trendhub.R;
import com.suganth.trendhub.adapter.RepoAdapter;
import com.suganth.trendhub.model.RepoModel;
import com.suganth.trendhub.network.Network;
import com.suganth.trendhub.repository.ReposRepository;
import com.suganth.trendhub.viewmodel.RepoViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * HomeActivity - an activity which displays the list of datas from an API
 * @swipeRefreshLayout - used to pull and refresh to fetch api
 * networkRequest() - method used to handle request and response.
 * onCrateOptionsMenu() - a menu item with search filter to sort according to out search condition.
*/

public class HomeActivity extends AppCompatActivity {

    private RepoViewModel repoViewModel;
    private RecyclerView recyclerView;
    private List<RepoModel> repoList;
    private ReposRepository reposRepository;
    private RepoAdapter repoAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_title_layout);
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
        swipeRefreshLayout = findViewById(R.id.simpleSwipeRefreshLayout);
        repoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RepoViewModel.class);
        networkRequest();
        repoViewModel.getAllRepos().observe(this, new Observer<List<RepoModel>>() {
            @Override
            public void onChanged(List<RepoModel> repoList) {
                repoAdapter.setRepos(repoList);
                recyclerView.setAdapter(repoAdapter);
                repoAdapter.getAllRepos(repoList);
                Log.d("main", "onChanged: " + repoList);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                networkRequest();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void networkRequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Repos");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Network network = new Network();
        Call<List<RepoModel>> call = network.api.getAllRepos();
        call.enqueue(new Callback<List<RepoModel>>() {
            @Override
            public void onResponse(Call<List<RepoModel>> call, Response<List<RepoModel>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    reposRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RepoModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, "Check your Network Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean containsName(List<RepoModel> repoModel, String name) {
        for (RepoModel repo : repoModel) {
            if (repo.getName().toUpperCase().contains(name))
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (containsName(RepoAdapter.allRepos, s.toUpperCase())) {
                    repoAdapter.getFilter().filter(s);
                } else {
                    Toast.makeText(getBaseContext(),
                            "Search Results not found",
                            Toast.LENGTH_LONG)
                            .show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                repoAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}