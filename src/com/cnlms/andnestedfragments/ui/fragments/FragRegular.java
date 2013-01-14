package com.cnlms.andnestedfragments.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cnlms.andnestedfragments.R;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 1/14/13 11:50 AM
 */
public final class FragRegular extends BaseFragment {

    public static final String TAG = "Regular Fragment";

    private static FragRegular instance;

    public static FragRegular getInstance() {

        if (instance == null) instance = new FragRegular();

        return instance;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.frag_regular, container, false);

        final TextView txtView = (TextView) view.findViewById(R.id.frag_txt_view);

        txtView.setText(TAG);

        return view;

    }


}
