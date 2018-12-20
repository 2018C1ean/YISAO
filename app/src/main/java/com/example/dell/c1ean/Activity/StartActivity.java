package com.example.dell.c1ean.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.R;

/**
 * Create by 李雯晴 on 2018/11/28
 * 启动页
 */
public class StartActivity extends AppCompatActivity {

private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    //设置全屏
         setContentView(R.layout.start);
        SystemApplication.getInstance().addActivity(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoLoginRegister();
            }
        }, 800);    //启动页显示800毫秒后跳转到注册登录界面
    }

    /**
     * 前往注册、登录主页
     */
    private void gotoLoginRegister() {
        Intent intent = new Intent(StartActivity.this, LoginRegisterActivity.class);
        startActivity(intent);
        finish();
        //取消界面跳转时的动画，使启动页的logo图片与注册、登录主页的logo图片完美衔接
        overridePendingTransition(0, 0);
    }

    /**
     * 屏蔽物理返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

}
