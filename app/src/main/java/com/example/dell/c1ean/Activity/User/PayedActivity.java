package com.example.dell.c1ean.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;
import com.wuhenzhizao.titlebar.statusbar.StatusBarUtils;

/**
 * Created by 李雯晴 on 2018/12/18.
 */

public class PayedActivity extends AppCompatActivity {

    private OrderDao orderDao;
    private Order order;
    private CompanyDao companyDao;
    private Long order_id;
    private TextView company_name,order_activity_type,user_tel,user_location;
    private Button back;
    private UserDao userDao;
    private CompanyActivityDao companyActivityDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.payed_page);
        SystemApplication.getInstance().addActivity(this);
        StatusBarUtils.setStatusBarColor(getWindow(), getResources().getColor(R.color.colorTheme), 1);

        order_id = getIntent().getLongExtra("order_id",0);
        orderDao = ((BaseApplication) getApplication()).getOrderDao();
        companyDao = ((BaseApplication) getApplication()).getCompanyDao();
        companyActivityDao = ((BaseApplication) getApplication()).getCompanyActivityDao();
        userDao = ((BaseApplication) getApplication()).getUserDao();
        order = orderDao.queryBuilder().where(OrderDao.Properties.Id.eq(order_id)).unique();

        company_name = findViewById(R.id.company_name);
        user_tel = findViewById(R.id.user_tel);
        order_activity_type = findViewById(R.id.order_activity_type);
        user_location = findViewById(R.id.user_location);
        back = findViewById(R.id.back);

        setView();

    }

    private void setView(){

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayedActivity.this,UserMainPageActivity.class);
                startActivity(intent);
            }
        });

        String c_name = companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(order.getCompany_id())).unique().getCompany_name();

        company_name.setText(c_name+"将给您带来优质的家政服务");

        String activity_title = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(order.getActivity_id())).unique().getTitle();

        order_activity_type.setText(activity_title);

        User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(order.getUser_id())).unique();
        user_tel.setText(user.getTel());

        user_location.setText(order.getUserLocation());

    }

    /**
     * 屏蔽返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(PayedActivity.this,UserMainPageActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
