package com.example.dell.c1ean.Activity.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;
import com.wuhenzhizao.titlebar.statusbar.StatusBarUtils;

/**
 * Created by 李雯晴 on 2018/12/18.
 */

public class PayedActivity extends AppCompatActivity {

    private OrderDao orderDao;
    private Order order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemApplication.getInstance().addActivity(this);
        StatusBarUtils.setStatusBarColor(getWindow(), getResources().getColor(R.color.colorTheme), 1);



    }

    /**
     * 屏蔽返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
