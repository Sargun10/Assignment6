package com.example.assignment6.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.listener.RvListener;
import com.example.assignment6.model.User;

import java.util.ArrayList;

/**
 * handles recycler view of UserList Fragment to display users list fetching from api.
 */
public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<User> userArrayList;
    private RvListener mListener;

    public UserAdapter(ArrayList<User> userArrayList,RvListener mListener) {
        this.userArrayList = userArrayList;
        this.mListener=mListener;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.user_viewholder_layout,viewGroup,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        final UserViewHolder userViewHolder=(UserViewHolder) viewHolder;
        userViewHolder.tvUserId.setText(String.valueOf(userArrayList.get(position).getUserId()));
        userViewHolder.tvUserName.setText(userArrayList.get(position).getName());

        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRvItemClick(viewHolder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName,tvUserId;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserId=itemView.findViewById(R.id.tvId);
            tvUserName=itemView.findViewById(R.id.tvName);
        }
    }
}
