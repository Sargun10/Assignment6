package com.example.assignment6.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.assignment6.R;
import com.example.assignment6.adapter.UserAdapter;
import com.example.assignment6.fragment.ShowDetailsFragment;
import com.example.assignment6.fragment.ShowListFragment;
import com.example.assignment6.listener.OnFragmentInteractionListener;
import com.example.assignment6.listener.RvListener;
import com.example.assignment6.model.User;
import com.example.assignment6.util.Constants;
import com.example.callback.UsersApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener ,RvListener{
    private User user;
    private ShowDetailsFragment showDetailsFragment;
    private RecyclerView rvUsers;
    private RvListener mRvListener;
    private ProgressBar progressBar;
    private ArrayList<User> userArrayList;
    private OnFragmentInteractionListener mFragmentListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvListener=this;
        mFragmentListener=this;
        initViews();
        if(savedInstanceState!=null){
            user=savedInstanceState.getParcelable(Constants.USER);
            if(user!=null){
                communicateData(user);
            }
        }
        listApiCall();

    }

    /**
     * initializing views and progress bar and recycler view of users.
     */
    private void initViews() {
        userArrayList=new ArrayList<>();
        rvUsers=findViewById(R.id.rvUsers);
        progressBar=findViewById(R.id.progressBar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.drawableHotspotChanged(10,10);
        }
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.USER,user);
    }

    @Override
    public void communicateData(User user) {
            addDataInFragment(user);
    }

    /**
     * to set data in showdetails fragment selected user is communicated there.
     * @param user
     */
    private void addDataInFragment(User user){
        showDetailsFragment = (ShowDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.showDetailsFragment);
        if(showDetailsFragment!=null){
            showDetailsFragment.setData(user);
        }
        this.user = user;
    }

    /**
     * hitting api to fetch list of users
     */
    private void listApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final UsersApi usersApi= retrofit.create(UsersApi.class);

        Call<ArrayList<User>> call = usersApi.getUser();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
              userArrayList = response.body();
                rvUsers.setAdapter(new UserAdapter(userArrayList,mRvListener));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onRvItemClick(int position) {
            mFragmentListener.communicateData(userArrayList.get(position));
        }
}
