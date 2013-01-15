package com.cnlms.andnestedfragments.ui.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.cnlms.andnestedfragments.R;
import com.cnlms.andnestedfragments.adapter.MainPagerAdapter;

public final class ActHome extends FragmentActivity implements ViewPager.OnPageChangeListener, ActionBar.TabListener {

    /**
     *  Action bar.
     *  Not the backward compatible one.
     */
    private ActionBar actionBar;

    /**
     *  Fragment Pager Adapter
     */
    private MainPagerAdapter adapter;

    /**
     *  Support library View Pager
     */
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        adapter = new MainPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.mainPager);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);

        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (int i = 0; i < adapter.getCount(); i++) {

            actionBar.addTab(actionBar
                    .newTab()
                    .setText(adapter.getPageTitle(i))
                    .setTabListener(this));
        }

    }

    @Override
    public void onPageSelected(int position) {
        actionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onBackPressed() {

        /**
         *  Propagate back press to the fragments first
         */
        if (!adapter.onBackPressed(viewPager.getCurrentItem())) {

            super.onBackPressed();

        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
