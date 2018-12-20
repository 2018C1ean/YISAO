package com.example.dell.c1ean.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Activity.Company.CompanyMainPageActivity;
import com.example.dell.c1ean.Activity.User.UserMainPageActivity;
import com.example.dell.c1ean.Activity.Worker.WorkerMainPageActivity;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.DAO.LoginDAO;
import com.example.dell.c1ean.R;
import com.gyf.barlibrary.ImmersionBar;
import com.wuhenzhizao.titlebar.statusbar.StatusBarUtils;

/**
 * Created by 李雯晴 on 2018/11/21.
 * 登录界面
 */

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tel, pwd;
    private ImageView back;
    private Button login;
    private Spinner spinner;
    private LoginDAO loginDAO;
    private TextView ignore_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);    //设置全屏
        ImmersionBar.with(this).init();
        SystemApplication.getInstance().addActivity(this);

        //设置状态栏为白色
        StatusBarUtils.setStatusBarColor(getWindow(), getResources().getColor(R.color.colorWhite), 1);
        initView();
    }

    private void initView() {

        tel = findViewById(R.id.t1);
        pwd = findViewById(R.id.t2);
        login = findViewById(R.id.login);
        spinner = findViewById(R.id.userType);
        back = findViewById(R.id.back);
        ignore_login = findViewById(R.id.ignore_login);

        loginDAO = new LoginDAO((BaseApplication) getApplication());    //创建loginDAO

        spinner.setSelection(0);    //设置默认选项

        ignore_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, UserMainPageActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取登录信息
                String phone = tel.getEditText().getText().toString();
                String password = pwd.getEditText().getText().toString();

                if (spinner.getSelectedItem() != null) {
                    String usertype = spinner.getSelectedItem().toString();
                    //判断手机号是否为空或者密码是否为空或者密码长度是否为6~20位
                    if (validateTel(phone)) {
                        if (validatePassword(password)) {
                            if (loginDAO.existValid(usertype, phone)) {   //判断是否存在此用户
                                if (loginDAO.passwordValid(usertype, phone, password)) {   //判断密码是否正确
                                    ((BaseApplication) getApplication()).setUSER_ID(loginDAO.getId(usertype, phone));    //设置全局变量
                                    switch (usertype) {
                                        case "用户":
                                            Intent intent = new Intent(LoginActivity.this, UserMainPageActivity.class);
                                            startActivity(intent);  //跳转到用户首页
                                            finish();
                                            break;
                                        case "家政人员":
                                            Intent intent1 = new Intent(LoginActivity.this, WorkerMainPageActivity.class);
                                            startActivity(intent1);
                                            finish();
                                            break;
                                        case "家政公司":
                                            Intent intent2 = new Intent(LoginActivity.this, CompanyMainPageActivity.class);
                                            startActivity(intent2);
                                            finish();
                                            break;
                                    }
                                } else {
                                    showError(pwd, "密码错误");
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "用户不存在，请先注册", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                } else {
                    spinner.requestFocus();
                    Toast.makeText(LoginActivity.this, "请选择用户类型！", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 显示错误提示，清空错误文本框，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setText("");
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证手机号是否为空
     *
     * @param phone
     * @return
     */
    private boolean validateTel(String phone) {
        if (phone.isEmpty()) {
            showError(tel, "手机号不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码是否符合格式
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            showError(pwd, "密码不能为空");
            return false;
        } else if (password.length() < 6 || password.length() > 20) {
            showError(pwd, "密码长度为6-20位");
            return false;
        }

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(LoginActivity.this, LoginRegisterActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
