package com.example.android3hw3.ui.addPost;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.data.network.PostService;
import com.example.android3hw3.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddViewModel extends ViewModel {

    public void addPosts(Post post) {
        PostService.getInstance().addPost(post).enqueue(new Callback<Post>() {
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
