package com.cnlms.andnestedfragments.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cnlms.andnestedfragments.R;

import java.util.Stack;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 1/14/13 11:50 AM
 */
public final class FragWrapper extends BaseFragment {

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

    /**
     *  Holds back stacked fragment tags
     */
    private Stack<String> backStack;

    /**
     *  Child Fragment Manager
     */
    private FragmentManager fm;

    /**
     *  Fragment Tags
     */
    private int fragCount = 1;


    private static FragWrapper instance;

    public static FragWrapper getInstance() {

        if (instance == null) {

            instance = new FragWrapper();

        }

        return instance;

    }

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
                String.valueOf(fragCount),
                false
        );

    }

    private void addChildFragment(final Fragment fragment, final String fragmentTag, final boolean addToBackStack) {

        /**
         *  initialize child fragment manager
         */
        if (fm == null) fm = getChildFragmentManager();


        /**
         *  Starts a new transaction
         */
        FragmentTransaction ft = fm.beginTransaction();


        /**
         *  Hide lastly added fragment
         */
        if (backStack != null  && !backStack.isEmpty()) {

            ft.hide(fm.findFragmentByTag(backStack.peek()));

        }

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
         * Save fragment tag
         */
        if (backStack == null) backStack = new Stack<String>();

        backStack.push(fragmentTag);

    }

    public boolean popFragment() {

        /**
         *  Allow this fragment to consume the back button click
         */

        if (backStack != null   && !backStack.isEmpty()) {

            backStack.pop();

            fragCount-=1;

        }

        return fm != null && fm.popBackStackImmediate();

    }

    @Override
    public boolean backPressed() {

        return popFragment();

    }
}
