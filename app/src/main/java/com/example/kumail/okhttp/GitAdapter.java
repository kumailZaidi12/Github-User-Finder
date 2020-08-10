package com.example.kumail.okhttp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.GitUserHolder> {

    ArrayList<GithubUser> gitHubUsers;
    Context context;

    public GitAdapter(ArrayList<GithubUser> gitHubUsers, Context context) {
        this.gitHubUsers = gitHubUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public GitUserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater li=LayoutInflater.from(context);

        View viewInfalte=li.inflate(R.layout.item_row,viewGroup,false);

        return new GitUserHolder(viewInfalte);
    }

    @Override
    public void onBindViewHolder(@NonNull GitUserHolder gitUserHolder, int position) {

        GithubUser currentgitUser=gitHubUsers.get(position);

        gitUserHolder.tvLogin.setText(currentgitUser.getLogin());
        Picasso.get().load(Uri.parse(currentgitUser.getAvatarUrl())).into(gitUserHolder.img);
        gitUserHolder.tvProfile.setText(currentgitUser.getUrl());
        gitUserHolder.tvScore.setText(currentgitUser.getScore().toString());


    }

    @Override
    public int getItemCount() {
        return gitHubUsers.size();
    }

    class GitUserHolder extends RecyclerView.ViewHolder{

        TextView tvLogin,tvProfile,tvScore;
        ImageView img;

        public GitUserHolder(@NonNull View itemView) {
            super(itemView);

            tvLogin=itemView.findViewById(R.id.tvLogin);
            img=itemView.findViewById(R.id.img);
            tvProfile=itemView.findViewById(R.id.tvProfile);
            tvScore=itemView.findViewById(R.id.tvScore);

        }
    }
}


