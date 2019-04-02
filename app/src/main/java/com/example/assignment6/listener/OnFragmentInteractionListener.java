package com.example.assignment6.listener;

import com.example.assignment6.model.User;

public interface OnFragmentInteractionListener {
    /**
     * to communicate data between activity and fragments.
     * @param user
     */
    void communicateData(User user);
}
