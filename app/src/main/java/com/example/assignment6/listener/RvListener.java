package com.example.assignment6.listener;

/**
 * interface for recyclerView item click
 */
public interface RvListener {
    /**
     * handles viewholder click in userList recycler View.
     * @param position
     */
    void onRvItemClick(int position);
}
