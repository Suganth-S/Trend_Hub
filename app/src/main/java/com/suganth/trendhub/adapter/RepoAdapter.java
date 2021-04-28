package com.suganth.trendhub.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suganth.trendhub.R;
import com.suganth.trendhub.model.RepoModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * repoAdapter - An adapter class for recyclerView
 * Returning params like
 * @name,
 * @description,
 * @language,
 * @stars
 * @languageColor
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {
    private Context context;
    private List<RepoModel> repoList;
    public static List<RepoModel> allRepos;

    public RepoAdapter(Context mContext, List<RepoModel> mRepoList) {
        this.context = mContext;
        this.repoList = mRepoList;
    }

    public void setRepos(List<RepoModel> repos) {
        this.repoList = repos;
        allRepos = repos;
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                if (charSequence == null || charSequence.length() == 0) {
                    results.count = allRepos.size();
                    results.values = allRepos;
                } else {
                    String searchStr = charSequence.toString().toUpperCase();
                    List<RepoModel> resultsData = new ArrayList<>();
                    for (RepoModel repo : allRepos) {
                        if (repo.getName().toUpperCase().contains(searchStr))
                            resultsData.add(repo);
                    }
                    results.count = resultsData.size();
                    results.values = resultsData;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                repoList = (List<RepoModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public RepoAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepoAdapter.RepoViewHolder holder, int position) {
        RepoModel repoModel = repoList.get(position);
        holder.name.setText(repoModel.getName());
        holder.description.setText(repoModel.getDescription());
        holder.language.setText(repoModel.getLanguage());
        holder.starsCount.setText(repoModel.getStars());

        Glide.with(context)
                .load(repoModel.getLanguageColor())
                .into(holder.color);
    }


    public void getAllRepos(List<RepoModel> mRepoList) {
        this.repoList = mRepoList;
    }

    @Override
    public int getItemCount() {
        if(repoList != null)
            return repoList.size();
        else return 0;
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        public TextView name, description, language, starsCount;
        //public String color;
      public ImageView color;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.repoName);
            description = itemView.findViewById(R.id.repoDescription);
            language = itemView.findViewById(R.id.repoLanguage);
            starsCount = itemView.findViewById(R.id.repoStars);
            color = itemView.findViewById(R.id.languageColor);
        }
    }
}
