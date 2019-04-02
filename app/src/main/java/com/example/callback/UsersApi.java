package com.example.callback;

import com.example.assignment6.model.Post;
import com.example.assignment6.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * this interface is used to define base url and call functions getUser() and getPost().
 */
public interface UsersApi {

        String BASE_URL = "https://jsonplaceholder.typicode.com/";
        Retrofit retrofit = null;

        @GET("users")
        Call<ArrayList<User>> getUser();

        @GET("posts")
        Call<ArrayList<Post>> getPost(@Query("userId") int userId);
//        public static Retrofit getClient() {
//                if (retrofit==null) {
//                        retrofit = new Retrofit.Builder()
//                                .baseUrl(BASE_URL)
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build();
//                }
//                return retrofit;
//        }
}
