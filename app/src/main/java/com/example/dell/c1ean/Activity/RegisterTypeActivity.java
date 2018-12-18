package com.example.dell.c1ean.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dell.c1ean.R;

/**
 * Created by Eskii on 2018/11/29.
 * 选择注册类型的界面
 */

public class RegisterTypeActivity extends AppCompatActivity {

    private Button user,worker,company;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
