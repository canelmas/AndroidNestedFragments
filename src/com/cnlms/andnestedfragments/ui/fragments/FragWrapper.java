package com.cnlms.andnestedfragments.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cnlms.andnestedfragments.R;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 1/14/13 11:50 AM
 */
public final class FragWrapper extends Fragment {

    /**
     *
     *  The Wrapper Fragment that host nested child fragments.
     *
     *  First child fragment is added in onActivityCreated() callback
     *
     *  More child fragments can be added at runtime by clicking 'Go Nesty!'
     *  button.
     *
     */

    public static final String TAG = "FragWrapper";

    /**
     *  Child Fragment Manager
     */
    private FragmentManager fm;

    /**
     *  Fragment Tags
     */
    private int fragCount = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_wrapper, container, false);

        view.findViewById(R.id.btn_go_deep).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /**
                 *  Adds nested child fragments
                 */
                fragCount+=1;

                addChildFragment(
                        FragChild.newInstance(fragCount),
                        String.valueOf(fragCount),
                        true
                );
            }

        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        /**
         *  Add Initial Child Fragment
         */
        addChildFragment(
                FragChild.newInstance(fragCount),
                TAG,
                false
        );

    }

    private String firstFragmentTag;

    private void addChildFragment(final Fragment fragment, final String fragmentTag, final boolean addToBackStack) {

        /**
         *  initialize child fragment manager
         */
        if (fm == null) {

            fm = getChildFragmentManager();

            firstFragmentTag = fragmentTag;

            //   this statement should be called only for the first fragment in theory
            FragBackStackHandler.getInstance().addBackStackObserver(firstFragmentTag, fm);

        }


        /**
         *  Starts a new transaction
         */
        final FragmentTransaction ft  = FragBackStackHandler.getInstance().newTransaction(firstFragmentTag);


        /**
         *  todo : add custom transition animation here..
         */


        /**
         *  Hide current fragment for the this wrapper's back stack
         */
        FragBackStackHandler.getInstance().hideCurrentFragment(firstFragmentTag, ft);


        /**
         *  Add new fragment
         */
        ft.add(R.id.frag_container, fragment, fragmentTag );


        /**
         *  Add to back stack
         */
        if (addToBackStack) {

            ft.addToBackStack(null);

        }

        /**
         *  Commit transaction
         */
        ft.commit();


        /**
         * Save fragment tag as a new back stack entry for this wrapper
         */
        FragBackStackHandler.getInstance().pushBackStackEntry(firstFragmentTag, fragmentTag);

    }

}
