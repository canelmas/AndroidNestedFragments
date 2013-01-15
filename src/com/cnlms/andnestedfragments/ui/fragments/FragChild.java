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
 * Date: 1/14/13 11:17 AM
 */
public final class FragChild extends Fragment {

    public static FragChild newInstance(int count) {

        final FragChild frag = new FragChild();

        final Bundle bundle = new Bundle();

        bundle.putInt("count", count);

        frag.setArguments(bundle);

        return frag;
    }

    private String initialText;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final Bundle bundle = getArguments();

        if (bundle != null) {

            initialText = String.valueOf(bundle.getInt("count"));
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.frag_child, container, false);

        if (initialText != null) {

            ((TextView) view.findViewById(R.id.frag_child_txt)).setText(initialText);

        }

        return view;

    }
}
