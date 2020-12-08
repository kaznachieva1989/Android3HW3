package com.example.android3hw3.ui.users.userPost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.android3hw3.R;
import com.example.android3hw3.data.adapter.OnItemOpenActivity;
import com.example.android3hw3.data.adapter.PostAdapter;
import com.example.android3hw3.data.adapter.UserPostAdapter;
import com.example.android3hw3.models.Post;
import com.example.android3hw3.ui.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UserPostActivity extends AppCompatActivity implements OnItemOpenActivity {
    UserPostViewModel userPostViewModel;
    RecyclerView recyclerView;
    UserPostAdapter userPostAdapter;
    List<Post> postList = new ArrayList<>();
    int user = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);
        recyclerView = findViewById(R.id.user_post_rv);
        userPostViewModel = ViewModelProviders.of(this).get(UserPostViewModel.class);
        Intent intent = getIntent();
        user = intent.getIntExtra("user", 0);
        if (user != 0) {
            userPostViewModel.getUserPost(user);
        }
        userPostAdapter = new UserPostAdapter();
        recyclerView.setAdapter(userPostAdapter);

        userPostViewModel.getUser.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                userPostAdapter.setList(posts, UserPostActivity.this);
            }
        });
    }

    @Override
    public void onLongClick(int id) {

    }

    @Override
    public void onClick(int id) {
        this.user = id;
    }
}