package com.example.dell.c1ean.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Application.BaseAppliction;
import com.example.dell.c1ean.DAO.LoginDAO;
import com.example.dell.c1ean.R;

/**
 * Created by Eskii on 2018/11/21.
 * 登录界面
 */

public class LoginActivity extends AppCompatActivity{

    private TextInputLayout tel,pwd;
    private ImageView login,back;
    private Spinner spinner;
    private LoginDAO loginDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();
    }

    private void initView(){

        tel = findViewById(R.id.t1);
        pwd = findViewById(R.id.t2);
        login = findViewById(R.id.login);
        spinner = findViewById(R.id.userType);
        back = findViewById(R.id.back);
        loginDAO = new LoginDAO((BaseAppliction) getApplication());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = tel.getEditText().getText().toString();
                String password = pwd.getEditText().getText().toString();
                String usertype = spinner.getSelectedItem().toString();

                tel.setErrorEnabled(false);
                pwd.setErrorEnabled(false);

                if (validateTel(phone)&&validatePassword(password)){    //判断手机号是否为空或者密码是否为空或者密码长度是否为6~20位

                    if (loginDAO.existValid(usertype,phone)){   //判断是否存在此用户
                        if (loginDAO.passwordValid(usertype,phone,password)){   //判断密码是否正确
                            switch (usertype){
                                case "用户":
                                    Intent intent = new Intent(LoginActivity.this,UserHomePageActivity.class);
                                    startActivity(intent);  //跳转到用户首页
                                    finish();
                                    break;
                                case "家政人员":
                                    Intent intent1 = new Intent();

                                    break;
                                case "家政公司":
                                    Intent intent2 = new Intent();

                                    break;
                            }
                        }else {
                            showError(pwd,"密码错误");
                        }
                    }else {
                        Toast.makeText(LoginActivity.this,"用户不存在，请先注册",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 显示错误提示，清空错误文本框，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setText("");
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证手机号是否为空
     * @param account
     * @return
     */
    private boolean validateTel(String account){
        if(account.isEmpty()){
            showError(tel,"手机号不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码是否符合格式
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            showError(pwd,"密码不能为空");
            return false;
        } else if (password.length() < 6 || password.length() > 20) {
            showError(pwd,"密码长度为6-20位");
            return false;
        }

        return true;
    }

}
