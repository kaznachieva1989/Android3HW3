package com.example.android3hw3.ui.addPost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android3hw3.R;
import com.example.android3hw3.models.Post;

public class AddPostActivity extends AppCompatActivity {

    AddViewModel addViewModel;
    Post post;
    private EditText addTitle;
    private EditText addContent;
    private EditText addUser;
    private EditText addGroup;
    private Button addPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);

        initView();

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = new Post();
                post.setTitle(addTitle.getText().toString());
                post.setContent(addContent.getText().toString());
                post.setGroup(Integer.parseInt(addGroup.getText().toString()));
                post.setUser(Integer.parseInt(addUser.getText().toString()));

                addViewModel.addPosts(post);
                finish();
            }
        });


    }

    private void initView() {
        addTitle = findViewById(R.id.add_title);
        addContent = findViewById(R.id.add_content);
        addUser = findViewById(R.id.add_user);
        addGroup = findViewById(R.id.add_group);
        addPost = findViewById(R.id.btn_add);
    }
}