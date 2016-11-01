package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
            case 1 :
                return new NumbersFragment();
            case 2 :
                return new ColorFragment();
            case 3:
                return new FamilyFragment();
            default:
                return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}
