package com.example.dell.c1ean.Fragment.Worker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.c1ean.Fragment.TabFragment;
import com.example.dell.c1ean.R;
import com.example.dell.c1ean.Util.SlideShowView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/12/5.
 */

public class WorkerHomePageFragment extends Fragment{
    private SlideShowView slideShowView;
    private TabLayout mTab;
    private ViewPager mVp;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.worker_details,container,false);

        slideShowView = view.findViewById(R.id.slideshowview);
        mTab = view.findViewById(R.id.tab_layout);
        mVp = view.findViewById(R.id.view_pager);

        initData();
        initView();

        return view;
    }

    private void initView() {
        mTab.setTabMode(TabLayout.MODE_FIXED);
        mVp.setAdapter(new WorkerHomePageFragment.TabAdapter(getChildFragmentManager()));
        mTab.setupWithViewPager(mVp);
    }

    private void initData() {
        tabs.add("工作安排");
        tabs.add("待完成订单");
        fragments.add(new TabFragment(this,tabs.get(0)));
        fragments.add(new TabFragment(this,tabs.get(1)));
    }

    public class TabAdapter extends FragmentPagerAdapter {
        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
