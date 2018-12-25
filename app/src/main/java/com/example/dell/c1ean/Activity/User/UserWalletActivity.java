package com.example.dell.c1ean.Activity.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Adapter.InaccountListAdapter;
import com.example.dell.c1ean.Adapter.OutccountListAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.UserInaccount;
import com.example.dell.c1ean.Bean.UserOutaccount;
import com.example.dell.c1ean.Bean.UserWallet;
import com.example.dell.c1ean.DAO.UserInaccountDao;
import com.example.dell.c1ean.DAO.UserOutaccountDao;
import com.example.dell.c1ean.DAO.UserWalletDao;
import com.example.dell.c1ean.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/21.
 * 用户钱包
 */

public class UserWalletActivity extends AppCompatActivity {

    private ListView inaccount_listView, outaccount_listView;
    private List<UserInaccount> userInaccountList = new ArrayList<>();
    private List<UserOutaccount> userOutaccountList = new ArrayList<>();
    private TextView balance, recharge;
    private UserWalletDao userWalletDao;
    private UserInaccountDao userInaccountDao;
    private UserOutaccountDao userOutaccountDao;
    private Long user_id;
    private ImageView back;
    private OutccountListAdapter outccountListAdapter;
    private InaccountListAdapter inaccountListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_wallet_page);

        user_id = ((BaseApplication) getApplication()).getUSER_ID();
        userWalletDao = ((BaseApplication) getApplication()).getUserWalletDao();
        userInaccountDao = ((BaseApplication) getApplication()).getUserInaccountDao();
        userOutaccountDao = ((BaseApplication) getApplication()).getUserOutaccountDao();

        back = findViewById(R.id.back);
        inaccount_listView = findViewById(R.id.inaccount_list);
        outaccount_listView = findViewById(R.id.outaccount_list);
        balance = findViewById(R.id.balance);
        recharge = findViewById(R.id.recharge);

        setData();
        setView();


    }

    private void setView() {

//        UserWallet userWallet = userWalletDao.queryBuilder().where(UserWalletDao.Properties.User_id.eq(user_id),UserWalletDao.Properties.User_type.eq("用户")).unique();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        balance.setText("￥0.0");    //钱包暂时不弄
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserWalletActivity.this, "钱包服务暂时不支持哦~", Toast.LENGTH_SHORT).show();
            }
        });

        if (userOutaccountList != null) {
            outccountListAdapter = new OutccountListAdapter(userOutaccountList, UserWalletActivity.this);
            outaccount_listView.setAdapter(outccountListAdapter);
        }
        if (userInaccountList != null) {
            inaccountListAdapter = new InaccountListAdapter(userInaccountList, UserWalletActivity.this);
            inaccount_listView.setAdapter(inaccountListAdapter);
        }

        Toast.makeText(this, userOutaccountList.size()+"", Toast.LENGTH_SHORT).show();
    }

    private void setData() {

        userInaccountList = userInaccountDao.queryBuilder().where(UserInaccountDao.Properties.User_id.eq(user_id)).list();
        userOutaccountList = userOutaccountDao.queryBuilder().where(UserOutaccountDao.Properties.User_id.eq(user_id)).list();
    }
}
