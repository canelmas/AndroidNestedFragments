package com.cnlms.andnestedfragments.ui.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 1/14/13 7:05 PM
 *
 *  Helper class to maintain nested child
 *  fragments' back stack
 *
 */
public class FragBackStackHandler {

    /**
     *  <Fragment Tag - FragBackStack>
     */
    private Map<String, FragmentBackStack> backStackMap;


    private static FragBackStackHandler instance;

    public static FragBackStackHandler getInstance() {

        if (instance == null) {

            instance = new FragBackStackHandler();

        }

        return instance;

    }

    private FragBackStackHandler() {

        backStackMap = new HashMap<String, FragmentBackStack>();

    }

    public void clear() {

        //  todo
    }

    /**
     *  Adds a back stack entry for the fragment tag
     *  passed as parameter.
     *
     * @param initialFragmentTag first wrapper fragment tag
     * @param fm child fragment manager
     */
    public void addBackStackObserver(final String initialFragmentTag, final FragmentManager fm) {

        backStackMap.put(initialFragmentTag, new FragmentBackStack(fm));

    }

    /**
     *  Begins a transaction on the fragment with the passed tag
     *
     * @param fragmentTag fragment tag on which a transaction is requested
     * @return fragment transaction instance for the fragment passed
     * as parameter
     */
    public FragmentTransaction newTransaction(final String fragmentTag) {

        final FragmentBackStack fragBackStack = backStackMap.get(fragmentTag);

        if (fragBackStack == null) {

            throw new IllegalArgumentException("Back stack for " + fragmentTag + " not initialized yet!");

        }

        return fragBackStack.fm.beginTransaction();

    }

    /**
     * Hides current stack entry for the fragment tag
     * passed as parameter
     *
     * @param fragmentTag fragment tag to look for the stack entry
     * @param ft fragment transaction to use
     */
    public void hideCurrentFragment(final String fragmentTag, final FragmentTransaction ft) {

        final FragmentBackStack fragBackStack = backStackMap.get(fragmentTag);

        if (fragBackStack == null) {

            throw new IllegalArgumentException("Back stack for " + fragmentTag + " not initialized yet!");

        }

        if (!fragBackStack.stackEntries.isEmpty()) {

            ft.hide(fragBackStack.fm.findFragmentByTag(fragBackStack.stackEntries.peek()));

        }

    }

    /**
     * Handles the back press for the wrapper fragment passed as
     * parameter.
     *
     * @param fragmentTag fragment tag to inject back press (pop)
     * @return true if there's a fragment to pop; false otherwise
     */
    public boolean popBackStackEntry(final String fragmentTag) {

        final FragmentBackStack fragBackStack = backStackMap.get(fragmentTag);

        if (fragBackStack != null) {

            if (!fragBackStack.stackEntries.isEmpty()) {

                return fragBackStack.pop();

            }

        }

        return false;

    }

    /**
     *  Adds a new Fragment back stack entry for the
     *  fragment tag passed as parameter.
     *
     * @param firstFragmentTag fragment tag to look for its stack
     * @param fragmentToPush fragment tag to push
     */
    public void pushBackStackEntry(final String firstFragmentTag, final String fragmentToPush) {

        final FragmentBackStack fragBackStack = backStackMap.get(firstFragmentTag);

        fragBackStack.stackEntries.push(fragmentToPush);

    }

    static final class FragmentBackStack {

        public final Stack<String> stackEntries;
        public final FragmentManager fm;

        public FragmentBackStack (final FragmentManager fm) {

            this.fm         = fm;
            stackEntries    = new Stack<String>();

        }

        public boolean pop() {

            stackEntries.pop();

            return fm.popBackStackImmediate();

        }

    }

}
