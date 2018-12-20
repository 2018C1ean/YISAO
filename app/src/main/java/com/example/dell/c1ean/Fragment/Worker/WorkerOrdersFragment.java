package com.example.dell.c1ean.Fragment.Worker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.dell.c1ean.R;

/**
 * Created by DELL on 2018/12/5.
 */

public class WorkerOrdersFragment extends Fragment {
    private TabHost tabHost;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.worker_order_page,container,false);

        tabHost = view.findViewById(android.R.id.tabhost);
        recyclerView = view.findViewById(R.id.recyclerListView);

        initView();
        return view;
    }

    private void initView(){

        //选项卡加载
        tabHost.setup();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        inflater.inflate(R.layout.user_all_orders,tabHost.getTabContentView());
        inflater.inflate(R.layout.user_wait_pay_orders,tabHost.getTabContentView());
        inflater.inflate(R.layout.user_wait_evaluation_orders,tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("所有订单").setContent(R.id.line1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("待支付").setContent(R.id.line1));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("待评价").setContent(R.id.line1));
    }
}
