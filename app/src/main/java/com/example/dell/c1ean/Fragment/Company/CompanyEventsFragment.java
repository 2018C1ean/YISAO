package com.example.dell.c1ean.Fragment.Company;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dell.c1ean.Activity.Company.CompanyMainPageActivity;
import com.example.dell.c1ean.Activity.Company.ManageActivityActivity;
import com.example.dell.c1ean.Adapter.RecyclerViewStaggeredAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/5.
 */

public class CompanyEventsFragment extends Fragment implements RecyclerViewStaggeredAdapter.OnItemClickListener{

    private TextView valuable;
    private FloatingActionButton fab;   //浮动添加活动按钮
    private RecyclerView recyclerView;  //瀑布流视图
    private List<CompanyActivity> companyActivityList;  //公司所发布过的活动列表
    private CompanyActivityDao companyActivityDao;
    private CompanyDao companyDao;
    private OrderDao orderDao;
    private Long user_id;
    private RecyclerViewStaggeredAdapter recyclerViewStaggeredAdapter;
    private TextView title;
    private ScrollView scrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.company_events, container, false);   //设置布局

        valuable = view.findViewById(R.id.valuable);
        fab = view.findViewById(R.id.addActivity);
        recyclerView = view.findViewById(R.id.recyclerView);
        title = view.findViewById(R.id.title_bar);
        scrollView = view.findViewById(R.id.scrollView);

        companyActivityDao = ((BaseApplication) getActivity().getApplication()).getCompanyActivityDao();
        companyDao = ((BaseApplication) getActivity().getApplication()).getCompanyDao();
        orderDao = ((BaseApplication) getActivity().getApplication()).getOrderDao();
        user_id = ((BaseApplication) getActivity().getApplication()).getUSER_ID();

        initView();
        setTitle();

        return view;
    }

    private void initView() {

        //给浮动按钮添加动画
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        ObjectAnimator go = ObjectAnimator.ofFloat(fab, "translationY", 0, fab.getHeight() + layoutParams.bottomMargin);
        go.setDuration(500);
        go.setInterpolator(new AccelerateInterpolator());
        go.start();
        go.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
                ObjectAnimator back = ObjectAnimator.ofFloat(fab, "translationY", fab.getHeight() + layoutParams.bottomMargin, 0);
                back.setDuration(500);
                back.setInterpolator(new DecelerateInterpolator());
                back.start();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManageActivityActivity.class);
                startActivity(intent);
            }
        });

        companyActivityList = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Company_id.eq(user_id)).list(); //获取id为user_id的companyActivity队列
        if (companyActivityList.size() < 1) {
            valuable.setVisibility(View.VISIBLE);
        }else {
            recyclerViewStaggeredAdapter = new RecyclerViewStaggeredAdapter(getContext(), companyActivityList, companyDao, orderDao);
            recyclerViewStaggeredAdapter.setItemClickListener(this);
            recyclerView.setAdapter(recyclerViewStaggeredAdapter);
            //布局管理器
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            staggeredGridLayoutManager.setReverseLayout(false);
            //设置布局管理器 参数GridManager 可以使RecyclerView实现和GridView一样的效果
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
        }
    }

    private void setTitle() {

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < 800) {
                    float alpha = 1 - ((float) scrollX) / 800;
                    title.setAlpha(alpha);
                    if (alpha == 0) {
                        title.setClickable(false);
                    } else {
                        title.setClickable(true);
                    }
                } else {
                    //下滑显示标题栏
                    if (oldScrollY > scrollY) {
                        title.setAlpha(1);
                        title.setClickable(true);
                    } else {
                        title.setAlpha(0);
                        title.setClickable(false);
                    }
                }
            }
        });
    }
    @Override
    public void onItemClick(Long item_id) {
        Intent intent = new Intent(getActivity(), ManageActivityActivity.class);
        intent.putExtra("activity_item_id",item_id);
        startActivity(intent);
    }

}
