package com.example.dell.c1ean.Fragment.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.c1ean.Activity.ActivityDetailPageActivity;
import com.example.dell.c1ean.Adapter.RecyclerViewStaggeredAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/4.
 */

public class UserActivityFragment extends Fragment implements RecyclerViewStaggeredAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewStaggeredAdapter recyclerViewStaggeredAdapter;
    private CompanyDao companyDao;
    private OrderDao orderDao;
    private CompanyActivityDao companyActivityDao;
    private List<CompanyActivity> companyActivityList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_activity, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        companyDao = ((BaseApplication) getActivity().getApplication()).getCompanyDao();
        orderDao = ((BaseApplication) getActivity().getApplication()).getOrderDao();
        companyActivityDao = ((BaseApplication) getActivity().getApplication()).getCompanyActivityDao();

        setData();
        initView();

        return view;
    }

    private void setData() {

        companyActivityList = companyActivityDao.queryBuilder().orderDesc(CompanyActivityDao.Properties.Id).list();
    }

    private void initView() {

        recyclerViewStaggeredAdapter = new RecyclerViewStaggeredAdapter(getContext(), companyActivityList, companyDao, orderDao);
        recyclerView.setAdapter(recyclerViewStaggeredAdapter);
        recyclerView.setNestedScrollingEnabled(false);  //设置为不可滚动
        //布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setReverseLayout(false);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewStaggeredAdapter.setItemClickListener(this);    //设置监听器

    }


    @Override
    public void onItemClick(Long item_id) {
        Intent intent = new Intent(getActivity(), ActivityDetailPageActivity.class);
        intent.putExtra("activity_item_id",item_id);
        startActivity(intent);
    }
}
