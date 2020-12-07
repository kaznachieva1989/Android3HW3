package com.example.android3hw3.data.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3hw3.R;
import com.example.android3hw3.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<Post> postList = new ArrayList<>();
    onItemClickListener onItemClickListener;


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.onBind(postList.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setList(List<Post> posts, onItemClickListener onItemClickListener) {
        postList.addAll(posts);
        this.onItemClickListener = onItemClickListener;
        notifyDataSetChanged();
    }

    public void removePost(int id) {
        postList.remove(id);
        notifyDataSetChanged();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titlePosts, contentPosts, userPosts, idPosts, groupPosts;

        TextView post_all;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titlePosts = itemView.findViewById(R.id.titlePosts);
            contentPosts = itemView.findViewById(R.id.contentPosts);
            userPosts = itemView.findViewById(R.id.userPosts);
            idPosts = itemView.findViewById(R.id.idPosts);
            groupPosts = itemView.findViewById(R.id.groupPosts);
        }

        @SuppressLint("SetTextI18n")
        public void onBind(Post post, PostAdapter.onItemClickListener onItemClickListener) {
            titlePosts.setText(post.getTitle());
            idPosts.setText(post.getId().toString());
            contentPosts.setText(post.getContent());
            userPosts.setText(String.valueOf(post.getUser().toString()));
            groupPosts.setText(String.valueOf(post.getGroup().toString()));

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(getAdapterPosition());
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });

        }
    }

    public interface onItemClickListener {
        void onLongClick(int id);

        void onClick(int id);
    }
}
