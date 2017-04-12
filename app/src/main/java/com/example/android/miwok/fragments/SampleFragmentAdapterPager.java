package com.example.android.miwok.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.miwok.helper.Constants;
import com.example.android.miwok.helper.Tabs;

/**
 * Created by mohamed nagy on 8/27/2016.
 */
public class SampleFragmentAdapterPager extends FragmentPagerAdapter {


    public SampleFragmentAdapterPager(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case Constants.NUMBERS:
                return new NumbersFragment();
            case Constants.COLORS :
                return new ColorFragment();
            case Constants.FAMILY:
                return new FamilyFragment();
            default:
                return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return Constants.TABS_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case Constants.NUMBERS :
                return Tabs.NUMBERS.m_label;
            case Constants.FAMILY :
                return Tabs.FAMILY.m_label;
            case Constants.COLORS :
                return Tabs.COLORS.m_label;
            default :
                return Tabs.PHRASES.m_label;
        }
    }

}
