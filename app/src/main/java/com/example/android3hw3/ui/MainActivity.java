package com.example.android3hw3.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android3hw3.R;
import com.example.android3hw3.data.adapter.OnItemOpenActivity;
import com.example.android3hw3.data.adapter.PostAdapter;
import com.example.android3hw3.models.Post;
import com.example.android3hw3.ui.addPost.AddPostActivity;
import com.example.android3hw3.ui.update.UpdateActivity;
import com.example.android3hw3.ui.users.UsersActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemOpenActivity {

    MainViewModel postViewModel;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    FloatingActionButton fab;
    List<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        postViewModel.getAllPosts();

        fab = findViewById(R.id.floating_action_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddPostActivity.class), 43);
            }
        });
        postViewModel.allPosts.observe(this, posts -> {
            postList.clear();
            postList = posts;
            postAdapter.setList(posts, this);
        });
        initAdapter();
    }

    private void initAdapter() {
        recyclerView = findViewById(R.id.recycler_view);
        // postAdapter = new PostAdapter(postList);
        postAdapter = new PostAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    public void onLongClick(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удаление");
        builder.setMessage("Удалить пост?");
        builder.setNegativeButton("Отмена", null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int which) {
                postViewModel.deletePost(id);
                postAdapter.removePost(id);
            }
        });
        builder.show();
    }

    @Override
    public void onClick(int id) {
        if (postList != null && postList.size() > 0) {
            startActivityForResult(new Intent(MainActivity.this, UpdateActivity.class).putExtra("id", id)
                    .putExtra("post", postList.get(id)), 44);
        }
    }

    public void UserClick(View view) {
        startActivity(new Intent(MainActivity.this, UsersActivity.class));
    }
}