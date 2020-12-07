package com.example.android3hw3.ui.update;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.android3hw3.data.network.PostService;
import com.example.android3hw3.models.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpViewModel extends ViewModel {

    public void updatePosts(int id, String title, String content, Integer group, Integer user) {
        PostService.getInstance().updatePost(id, title, content, user, group).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.e("TAG", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
