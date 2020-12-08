package com.example.android3hw3.ui.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.android3hw3.R;
import com.example.android3hw3.data.adapter.OnItemOpenActivity;
import com.example.android3hw3.data.adapter.PostAdapter;
import com.example.android3hw3.data.adapter.UserAdapter;
import com.example.android3hw3.models.Post;
import com.example.android3hw3.ui.MainActivity;
import com.example.android3hw3.ui.MainViewModel;
import com.example.android3hw3.ui.users.userPost.UserPostActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity implements OnItemOpenActivity {
    UsersViewModel usersViewModel;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<Post> postList = new ArrayList<>();
    int user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        usersViewModel.getUser();
        initAdapter();

        usersViewModel.getUser.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                postList.clear();
                postList = posts;
                userAdapter.setList(posts, UsersActivity.this);
            }
        });
    }

    private void initAdapter() {
        recyclerView = findViewById(R.id.recycler_view_forUsers);
        userAdapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onLongClick(int id) {

    }

    @Override
    public void onClick(int id) {
        startActivity(new Intent(UsersActivity.this, UserPostActivity.class).putExtra("user", id));
    }
}