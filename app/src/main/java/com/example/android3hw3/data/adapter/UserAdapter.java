package com.example.android3hw3.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3hw3.R;
import com.example.android3hw3.models.Post;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<Post> postList = new ArrayList<>();
    OnItemOpenActivity onItemOpenActivity;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.user.setText(postList.get(position).getUser().toString());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setList(List<Post> posts, OnItemOpenActivity onItemClickListener) {
        postList.addAll(posts);
        notifyDataSetChanged();
        this.onItemOpenActivity = onItemClickListener;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView user;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.userId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemOpenActivity.onClick(postList.get(getAdapterPosition()).getUser());
                }
            });
        }
    }
}
