package com.example.assignment6.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @SerializedName("username")
    private String userName;
    @SerializedName("id")
    private int userId;
    private String name;
    private String email;

    public User(String userName, int userId, String name, String email) {
        this.userName = userName;
        this.userId = userId;
        this.name=name;
        this.email=email;
    }

    protected User(Parcel in) {
        userName = in.readString();
        userId = in.readInt();
        name = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeInt(userId);
        dest.writeString(name);
        dest.writeString(email);
    }
}
