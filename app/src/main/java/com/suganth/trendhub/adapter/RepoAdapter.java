package com.suganth.trendhub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suganth.trendhub.R;
import com.suganth.trendhub.model.RepoModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {
    private Context context;
    private List<RepoModel> repoList;

    public RepoAdapter(Context mContext, List<RepoModel> mRepoList) {
        this.context = mContext;
        this.repoList = mRepoList;
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
        holder.starsCount.setText((int) repoModel.getStars());

        Glide.with(context)
                .load(repoModel.getColor())
                .into(holder.color);
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public void getAllRepos (List<RepoModel> mRepoList) {
        this.repoList=mRepoList;
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        public TextView name, description, language, starsCount;
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
