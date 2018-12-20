package com.example.dell.c1ean.Activity.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.R;

/**
 * Created by DELL on 2018/12/15.
 */

public class QuickOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemApplication.getInstance().addActivity(this);

        setContentView(R.layout.quick_order_page);
    }
}
