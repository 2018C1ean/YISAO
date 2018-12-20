package com.example.dell.c1ean.Fragment.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Activity.ActivityDetailPageActivity;
import com.example.dell.c1ean.Activity.User.QuickOrderActivity;
import com.example.dell.c1ean.Adapter.NoticeAdapter;
import com.example.dell.c1ean.Adapter.RecyclerViewStaggeredAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;
import com.example.dell.c1ean.Util.GlideImageLoader;
import com.example.dell.c1ean.Util.VerticalScrollLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/4.
 */

public class UserHomePageFragment extends Fragment implements RecyclerViewStaggeredAdapter.OnItemClickListener {

    private FloatingActionButton quick_order;
    private Banner banner;  //轮播图控件
    private List<String> images = new ArrayList<>();    //轮播图图片路径队列
    private List<String> titles = new ArrayList<>();    //轮播图图片对应的标题
    private CompanyActivityDao companyActivityDao;
    private OrderDao orderDao;
    private CompanyDao companyDao;
    private UserDao userDao;
    private Long user_id;   //当前用户id
    private ImageView zybj, jdqx, jjyh, xhfw;
    private RecyclerView recyclerView;
    private RecyclerViewStaggeredAdapter recyclerViewStaggeredAdapter;
    private List<CompanyActivity> companyActivityList;
    private List<CompanyActivity> allCompanyActivityList;
    private VerticalScrollLayout verticalScrollLayout;
    private List<NoticeAdapter.Item> itemList = new ArrayList<>();
    private NoticeAdapter noticeAdapter;
    private List<Order> orderList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_homepage, container, false);

        userDao = ((BaseApplication) getActivity().getApplication()).getUserDao();
        companyActivityDao = ((BaseApplication) getActivity().getApplication()).getCompanyActivityDao();
        orderDao = ((BaseApplication) getActivity().getApplication()).getOrderDao();
        companyDao = ((BaseApplication) getActivity().getApplication()).getCompanyDao();
        user_id = ((BaseApplication) getActivity().getApplication()).getUSER_ID();

        quick_order = view.findViewById(R.id.quick_order);
        verticalScrollLayout = view.findViewById(R.id.buys_scroll);
        zybj = view.findViewById(R.id.type1);
        jdqx = view.findViewById(R.id.type2);
        jjyh = view.findViewById(R.id.type3);
        xhfw = view.findViewById(R.id.type4);
        recyclerView = view.findViewById(R.id.recyclerView);
        banner = view.findViewById(R.id.banner);

        noticeAdapter = new NoticeAdapter();

        setData();  //设置轮播图数据
        setBanner();    //初始化轮播图控件
        setView();
        return view;
    }

    private void setView() {

        //服务类型图标的点击事件
        zybj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        jdqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        jjyh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        xhfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        quick_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuickOrderActivity.class);
                startActivity(intent);
            }
        });

        recyclerViewStaggeredAdapter = new RecyclerViewStaggeredAdapter(getContext(), companyActivityList, companyDao, orderDao);
        recyclerViewStaggeredAdapter.setItemClickListener(this);    //设置监听器
        recyclerView.setAdapter(recyclerViewStaggeredAdapter);
        recyclerView.setNestedScrollingEnabled(false);  //设置为不可滚动
        //布局管理器所需参数，上下文
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    /**
     * 设置轮播图控件参数
     */
    private void setBanner() {

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(),ActivityDetailPageActivity.class);
                intent.putExtra("activity_item_id",companyActivityList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void setData() {

        //加载10条最新活动数据
        allCompanyActivityList = companyActivityDao.queryBuilder().orderDesc(CompanyActivityDao.Properties.Id).limit(20).list();

        /**
         * 设置用户首页轮播图的数据
         * 先从用户下的订单中得出用户使用最频繁的服务
         * 如果用户没有下过任何一个类型的订单，则轮播任意类型最新发出的五个活动，
         * 否则轮播用户使用最频繁的服务类型的最新发出的五个活动
         */
        String favourite = getFavourite();  //获取用户最喜爱的服务类型

        if (favourite != null) {
            companyActivityList = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Type.eq(favourite))
                    .orderDesc(CompanyActivityDao.Properties.Id).limit(5).list();
            if (companyActivityList.size() < 5){
                for (int i = 0; i < allCompanyActivityList.size(); i++){
                    if (companyActivityList.size() >= 5){
                        break;
                    }else {
                        companyActivityList.add(allCompanyActivityList.get(i));
                    }
                }
            }
        } else {
            companyActivityList = companyActivityDao.queryBuilder().orderDesc(CompanyActivityDao.Properties.Id).limit(5).list();
        }
        for (int i = 0; i < companyActivityList.size(); i++) {
            images.add(companyActivityList.get(i).getImg1());    //放入服务的照片
            titles.add(companyActivityList.get(i).getTitle());    //放入服务的描述
        }

        //把最新发布的活动加入用户可能喜爱的活动
        companyActivityList.addAll(allCompanyActivityList);

        /**
         * 加载购买用户的滚动数据列表
         */
        //加载最新购买的前20名用户
        orderList = orderDao.queryBuilder().orderDesc(OrderDao.Properties.Id).limit(20).list();

        if (orderList.size() > 0){
            //加载用户电话
            for (Order order:orderList){
                String tel = userDao.queryBuilder().where(UserDao.Properties.Id.eq(order.getUser_id())).unique().getTel();
                String user_tel = tel.substring(0,4)+"****"+tel.substring(8,11);    //给用户的电话打码
                NoticeAdapter.Item item = new NoticeAdapter.Item(user_tel,order.getType());
                itemList.add(item);
            }
        }else {
            itemList.add(new NoticeAdapter.Item("1306xxxx726","专业保洁"));
            itemList.add(new NoticeAdapter.Item("1306xxxx726","家电清洗"));
            itemList.add(new NoticeAdapter.Item("1306xxxx726","家居养护"));
        }

        noticeAdapter.setList(itemList);
        verticalScrollLayout.setAdapter(noticeAdapter);
    }

    //"专业保洁","家电清洗","家居养护","洗护服务"
    private String getFavourite() {

        if (user_id != null) {
            //查找该用户在每种类型下下订单的数量
            Long zyby = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.Type.eq("专业保洁")).count();
            Long jdqx = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.Type.eq("家电清洗")).count();
            Long jjyh = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.Type.eq("家居养护")).count();
            Long xhfw = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.Type.eq("洗护服务")).count();

            Long n[] = {zyby, jdqx, jjyh, xhfw};
            Long max = zyby;
            //获取数量最多的
            for (int i = 1; i < n.length; i++) {
                if (max < n[i]) {
                    max = n[i];
                }
            }

            //根据数量返回相应的服务类型
            if (max != 0) {
                if (max.equals(zyby)) {
                    return "专业保洁";
                } else if (max.equals(jdqx)) {
                    return "家电清洗";
                } else if (max.equals(jjyh)) {
                    return "家居养护";
                } else {
                    return "洗护服务";
                }
            } else {
                return null;
            }
        }else {
            return null;
        }
    }

    //跳转服务详情
    @Override
    public void onItemClick(Long item_id) {
        Intent intent = new Intent(getActivity(), ActivityDetailPageActivity.class);
        intent.putExtra("activity_item_id",item_id);
        startActivity(intent);
    }
}
