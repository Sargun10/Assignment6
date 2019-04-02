package com.example.assignment6.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.adapter.PostAdapter;
import com.example.assignment6.listener.RvListener;
import com.example.assignment6.model.Post;
import com.example.assignment6.util.Constants;
import com.example.callback.UsersApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPostActivity extends AppCompatActivity{
    private RecyclerView rvPostList;
    private ArrayList<Post> postArrayList;
    private int userId;
    private TextView tvUserIdPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        postArrayList= new ArrayList<>();
        userId=getIntent().getIntExtra(Constants.USER_ID,-1);
        rvPostList = findViewById(R.id.rvPosts);
        tvUserIdPosts=findViewById(R.id.tvUserIdPosts);
        tvUserIdPosts.setText(String.valueOf(userId));
        rvPostList.setLayoutManager(new LinearLayoutManager(this));
        postApiCall(userId);
    }

    /**
     * hitting api to fetch posts of selected user by comparing userId.
     * @param userId
     */
    private void postApiCall(int userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersApi usersApi= retrofit.create(UsersApi.class);
        Call<ArrayList<Post>> call = usersApi.getPost(userId);

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                    postArrayList = response.body();
                rvPostList.setAdapter(new PostAdapter(postArrayList));
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }
        });

    }

}
