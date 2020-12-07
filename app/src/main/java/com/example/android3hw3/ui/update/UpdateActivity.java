package com.example.android3hw3.ui.update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android3hw3.R;
import com.example.android3hw3.models.Post;

public class UpdateActivity extends AppCompatActivity {

    UpViewModel upViewModel;
    Post post;
    private EditText upTitle;
    private EditText upContent;
    private EditText upUser;
    private EditText upGroup;
    private Button upPost;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        upViewModel = ViewModelProviders.of(this).get(UpViewModel.class);

        initView();

        if (getIntent() != null) {
            id = getIntent().getIntExtra("id", 0);
            post = (Post) getIntent().getSerializableExtra("post");
            upTitle.setText(post.getTitle());
            upUser.setText(post.getUser().toString());
            upGroup.setText(post.getGroup().toString());
            upContent.setText(post.getContent());

        }

        upPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = upTitle.getText().toString();
                String content = upContent.getText().toString();
                Integer user = Integer.parseInt(upUser.getText().toString());
                Integer group = Integer.parseInt(upGroup.getText().toString());
                Intent intent = getIntent();
                post = new Post();
                post.setTitle(title);
                post.setContent(content);
                post.setGroup(group);
                post.setUser(user);
                intent.putExtra("post", post);
                setResult(RESULT_OK, intent);
                upViewModel.updatePosts(id, title, content, user, group);
                finish();
            }
        });

    }

    private void initView() {
        upTitle = findViewById(R.id.up_title);
        upContent = findViewById(R.id.up_content);
        upUser = findViewById(R.id.up_user);
        upGroup = findViewById(R.id.up_group);
        upPost = findViewById(R.id.btn_update);
    }
}