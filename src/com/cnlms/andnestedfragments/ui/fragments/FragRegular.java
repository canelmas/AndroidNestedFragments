package com.cnlms.andnestedfragments.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cnlms.andnestedfragments.R;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 1/14/13 11:50 AM
 */
public final class FragRegular extends Fragment {

    public static final String TAG = "Regular Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.frag_regular, container, false);

        ((TextView) view.findViewById(R.id.frag_txt_view)).setText(TAG);

        return view;

    }


}
