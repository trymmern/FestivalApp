package com.example.trymtodalshaug.festivalapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.trymtodalshaug.festivalapp.fragments.ArtistsFragment;
import com.example.trymtodalshaug.festivalapp.fragments.FavouritesFragment;
import com.example.trymtodalshaug.festivalapp.fragments.ScheduleFragment;

/**
 * Created by Trym Todalshaug on 06/11/2017.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
    private int tabCount;

    public TabsPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position) {
            case 0:
                ScheduleFragment tab1 = new ScheduleFragment();
                return tab1;
            case 1:
                ArtistsFragment tab2 = new ArtistsFragment();
                return tab2;
            case 2:
                FavouritesFragment tab3 = new FavouritesFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}