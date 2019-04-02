package com.example.assignment6.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.activity.ShowPostActivity;
import com.example.assignment6.listener.OnFragmentInteractionListener;
import com.example.assignment6.model.Post;
import com.example.assignment6.model.User;
import com.example.assignment6.util.Constants;
import com.example.callback.UsersApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowDetailsFragment extends Fragment {
    private TextView tvId,tvName,tvUserName,tvEmail,tvDefaultMessage;
    private Button btnFetchPosts;
    private int userIdPosts;
    private RelativeLayout layoutRelative;
    public ShowDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_show_details, container, false);
        initViews(view);
        return view;
    }

    /**
     * initializing views and setting on button click listener.
     * @param view
     */
    private void initViews(View view) {
        tvDefaultMessage=view.findViewById(R.id.tvDefaultMessage);
        tvId=view.findViewById(R.id.tvId);
        tvName=view.findViewById(R.id.tvName);
        tvUserName=view.findViewById(R.id.tvUserName);
        layoutRelative=view.findViewById(R.id.layoutRelative);
        tvEmail=view.findViewById(R.id.tvEmail);
        btnFetchPosts=view.findViewById(R.id.ButtonShowDetails);
        btnFetchPosts.setVisibility(View.INVISIBLE);
        layoutRelative.setVisibility(View.INVISIBLE);
        btnFetchPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowPostActivity.class);
                intent.putExtra(Constants.USER_ID,userIdPosts);
                startActivity(intent);
            }
        });
    }

    /**
     * sets data in textviews and handles layout of this fragment.
     * @param user
     */
    public void setData(User user) {
        tvDefaultMessage.setVisibility(View.GONE);
        layoutRelative.setVisibility(View.VISIBLE);
        tvId.setText(String.valueOf(user.getUserId()));
        tvName.setText(user.getName());
        tvUserName.setText(user.getUserName());
        tvEmail.setText(user.getEmail());
        userIdPosts=user.getUserId();
        btnFetchPosts.setVisibility(View.VISIBLE);
    }
}
