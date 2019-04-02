package com.example.assignment6.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.listener.RvListener;
import com.example.assignment6.model.Post;

import java.util.ArrayList;

/**
 * handles recycler view of posts in show post activity showing posts fetched from api.
 */
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Post> postArrayList;
    public PostAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.post_viewholder_layout, viewGroup, false);
        return new PostAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        PostAdapter.PostViewHolder postViewHolder = (PostAdapter.PostViewHolder) viewHolder;
        postViewHolder.tvPostId.setText(String.valueOf(postArrayList.get(i).getId()));
        postViewHolder.tvPostTitle.setText(postArrayList.get(i).getTitle());
        postViewHolder.tvPostBody.setText(postArrayList.get(i).getBody());

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvPostId,tvPostTitle,tvPostBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPostId = itemView.findViewById(R.id.tvPostId);
            tvPostTitle = itemView.findViewById(R.id.tvPostTitle);
            tvPostBody = itemView.findViewById(R.id.tvPostBody);
        }
    }


}

