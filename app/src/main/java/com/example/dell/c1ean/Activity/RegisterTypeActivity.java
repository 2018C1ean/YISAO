package com.example.dell.c1ean.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.R;
import com.wuhenzhizao.titlebar.statusbar.StatusBarUtils;

/**
 * Created by 李雯晴 on 2018/11/29.
 * 选择注册类型的界面
 */

public class RegisterTypeActivity extends AppCompatActivity {

    private Button user,worker,company;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemApplication.getInstance().addActivity(this);

        //设置状态栏为白色
        StatusBarUtils.setStatusBarColor(getWindow(),getResources().getColor(R.color.colorWhite),1);
        setContentView(R.layout.register_type);

        initView();
    }

    private void initView(){

        user = findViewById(R.id.user); //用户注册
        worker = findViewById(R.id.worker); //家政人员注册
        company = findViewById(R.id.company);   //家政公司注册
        back = findViewById(R.id.back);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTypeActivity.this,UserRegisterActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTypeActivity.this,WorkerCompanyRegisterActivity.class);
                intent.putExtra("type","家政人员");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTypeActivity.this,WorkerCompanyRegisterActivity.class);
                intent.putExtra("type","家政公司");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTypeActivity.this,LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(RegisterTypeActivity.this,LoginRegisterActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
