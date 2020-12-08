package com.example.android3hw3.ui.users;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3hw3.data.network.PostService;
import com.example.android3hw3.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersViewModel extends ViewModel {
    MutableLiveData<List<Post>> getUser = new MutableLiveData<>();

    public void getUser () {
        PostService.getInstance().getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.e("TAG", "onResponse: " + response.body());
                getUser.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

}
