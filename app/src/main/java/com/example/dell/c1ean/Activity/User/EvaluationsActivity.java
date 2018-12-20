package com.example.dell.c1ean.Activity.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dell.c1ean.Adapter.EvaluationListAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/16.
 * 查看用户的评价
 */

public class EvaluationsActivity extends AppCompatActivity {

    private ImageView back;
    private ListView listView;
    private Long activity_id;
    private List<Order> orderList;
    private OrderDao orderDao;
    private EvaluationListAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluations);
        SystemApplication.getInstance().addActivity(this);

        back = findViewById(R.id.back);
        listView = findViewById(R.id.evaluation_list);

        orderDao = ((BaseApplication) getApplication()).getOrderDao();

        activity_id = getIntent().getLongExtra("activity_id",0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setData();
    }

    private void setData(){

        orderList = orderDao.queryBuilder().where(OrderDao.Properties.Activity_id.eq(activity_id)).list();
    }
}
