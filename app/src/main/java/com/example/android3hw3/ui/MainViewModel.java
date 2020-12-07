package com.example.android3hw3.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.data.adapter.PostAdapter;
import com.example.android3hw3.data.network.PostService;
import com.example.android3hw3.models.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    MutableLiveData<List<Post>> allPosts = new MutableLiveData<>();

    public void getAllPosts() {
        PostService.getInstance().getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                allPosts.setValue(response.body());
                Log.e("TAG", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public void deletePost(int id) {
        PostService.getInstance().deletePost(id).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.e("TAG", "onResponse: Successful " + response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
