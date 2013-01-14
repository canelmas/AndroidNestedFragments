package com.cnlms.andnestedfragments.ui.fragments;

import android.support.v4.app.Fragment;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 1/14/13 12:14 PM
 */
public abstract class BaseFragment extends Fragment {

    public boolean backPressed(){
        return false;
    }

}
