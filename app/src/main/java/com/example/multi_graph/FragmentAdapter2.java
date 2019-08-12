package com.example.multi_graph;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/6/26 14:48
 *
 * Fragment适配2
 */

public class FragmentAdapter2 extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public FragmentAdapter2(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
