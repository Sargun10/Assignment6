package com.example.assignment6.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.assignment6.R;
import com.example.assignment6.model.User;
import com.example.assignment6.util.CheckConnectivity;
import com.example.callback.UsersApi;

import java.util.ArrayList;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    private final static long SPLASH_TIME = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startTimer();
    }

    /**
     * start timer for delay in the current screen
     */
    private void startTimer() {
        boolean checkConnection = new CheckConnectivity().checkConnection(this);
        if (checkConnection) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNextScreenAndFinish();
                }
            }, SPLASH_TIME);
        }
        else{
            Toast.makeText(this, getString(R.string.noInternetConnection),Toast.LENGTH_LONG).show();
        }

    }

    /**
     * show the respective next screen and finish the current one
     */
    private void showNextScreenAndFinish() {

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

    }

}
