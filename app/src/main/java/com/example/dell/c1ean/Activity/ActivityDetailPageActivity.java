package com.example.dell.c1ean.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.c1ean.Activity.User.AllEvaluationsActivity;
import com.example.dell.c1ean.Activity.User.OrderActivity;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;
import com.example.dell.c1ean.Util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/14.
 */

public class ActivityDetailPageActivity extends AppCompatActivity {

    private CompanyActivityDao companyActivityDao;
    private OrderDao orderDao;
    private CompanyDao companyDao;
    private long exitTime;
    private Long activity_id;
    private ImageView more_evaluation,back;
    private Banner banner;
    private TextView company_name,activity_price, activity_title, sale_count, order_user_name, evaluation, company_reference, activity_describe;
    private RelativeLayout evaluation_layout;
    private RatingBar average_star, evaluation_stars;
    private List<String> images = new ArrayList<>();    //轮播图图片路径队列
    private CompanyActivity companyActivity;    //当前界面所展示的活动
    private List<Order> orderList;
    private Button make_order;
    private Long user_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        SystemApplication.getInstance().addActivity(this);

        activity_id = getIntent().getLongExtra("activity_item_id", 0);

        user_id = ((BaseApplication) getApplication()).getUSER_ID();
        companyActivityDao = ((BaseApplication) getApplication()).getCompanyActivityDao();
        orderDao = ((BaseApplication) getApplication()).getOrderDao();
        companyDao = ((BaseApplication) getApplication()).getCompanyDao();

        companyActivity = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique();
        orderList = orderDao.queryBuilder().where(OrderDao.Properties.Activity_id.eq(activity_id)).list();

        initView();
    }

    private void initView() {

        banner = findViewById(R.id.activity_imgs);
        activity_price = findViewById(R.id.activity_price);
        activity_title = findViewById(R.id.activity_title);
        average_star = findViewById(R.id.average_star);
        sale_count = findViewById(R.id.sale_count);
        order_user_name = findViewById(R.id.order_user_name);
        evaluation = findViewById(R.id.evaluation_text);
        evaluation_stars = findViewById(R.id.evaluation_stars);
        company_reference = findViewById(R.id.company_reference);
        activity_describe = findViewById(R.id.activity_describe);
        evaluation_layout = findViewById(R.id.evaluation_layout);
        more_evaluation = findViewById(R.id.more_evaluation);
        make_order = findViewById(R.id.make_order);
        back = findViewById(R.id.back);
        company_name = findViewById(R.id.company_name);

        setData();

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(false);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        activity_price.setText(companyActivity.getPrice() + "");  //设置活动价格
        average_star.setRating(getRate());  //设置活动星级
        activity_title.setText(companyActivity.getTitle());   //设置活动类型
        sale_count.setText(orderList.size() + "");    //设置销量

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Order order = getNewEvaluationOrder();  //获取一个最新的带评价的订单
        if (order != null) {
            order_user_name.setVisibility(View.VISIBLE);
            evaluation.setVisibility(View.VISIBLE);
            evaluation_stars.setVisibility(View.VISIBLE);
            order_user_name.setText(order.getUser().getId() + "");
            evaluation.setText(order.getUserEvaluation());
            evaluation_stars.setRating(order.getStar());
        } else {
            more_evaluation.setVisibility(View.INVISIBLE);  //用户评价的箭头显示不可见
        }

        evaluation_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDetailPageActivity.this, AllEvaluationsActivity.class);
                intent.putExtra("activity_id", activity_id);
                startActivity(intent);
            }
        });
        //设置公司简介
        company_reference.setText(companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(companyActivity.getCompany_id())).unique().getIntroduction());
        company_name.setText(companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(companyActivity.getCompany_id())).unique().getCompany_name());
        //设置活动简介
        activity_describe.setText(companyActivity.getActivity_decribes());

        /**
         * 未登录的用户不能预定服务
         */
        make_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_id != null) {
                    Intent intent = new Intent(ActivityDetailPageActivity.this, OrderActivity.class);
                    intent.putExtra("activity_id", activity_id);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityDetailPageActivity.this);
                    dialog.setMessage("您还未登录，登录之后才能预定服务哦~");
                    dialog.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ActivityDetailPageActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    dialog.setNegativeButton("不了，我再逛逛", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            }
        });

    }

    private void setData() {

        //加载图片数据
        if (companyActivity.getImg1() != null) {
            images.add(companyActivity.getImg1());
        }
        if (companyActivity.getImg2() != null) {
            images.add(companyActivity.getImg2());
        }
        if (companyActivity.getImg3() != null) {
            images.add(companyActivity.getImg3());
        }

    }

    private float getRate() {

        int good = 0;
        if (orderList.size() > 0) {
            for (Order order : orderList) {
                if (order.getStar() >= 4) {  //大于等于4颗星为好评
                    good++;
                }
            }
            return (good / orderList.size());
        } else {
            return 0;
        }
    }

    private Order getNewEvaluationOrder() {

        Order order = null;
        if (orderList.size()>0) {
            for (int i = orderList.size() - 1; i > 0; i--) {
                Order o = orderList.get(i);
                if (o.getUserEvaluation() != null && o.getStar() != 0) {
                    order = o;
                }
            }
        }
        return order;
    }
}