package com.example.android3hw3.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.android3hw3.R;
import com.example.android3hw3.data.adapter.PostAdapter;
import com.example.android3hw3.data.network.PostService;
import com.example.android3hw3.models.Post;
import com.example.android3hw3.ui.addPost.AddPostActivity;
import com.example.android3hw3.ui.update.UpdateActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PostAdapter.onItemClickListener {
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
                //  StartAct(new Intent(MainActivity.this, AddPostActivity.class));
                startActivityForResult(new Intent(MainActivity.this, AddPostActivity.class), 43);
            }
        });

        initAdapter();

    }

    @Override
    protected void onResume() {
        super.onResume();
        postViewModel.allPosts.observe(this, posts -> {
            postList = posts;
            postAdapter.setList(posts, (PostAdapter.onItemClickListener) this);
        });
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
        startActivityForResult(new Intent(MainActivity.this, UpdateActivity.class).putExtra("id", id)
                .putExtra("post", postList.get(id)), 44);
    }
}