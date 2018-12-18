package com.example.dell.c1ean.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Application.BaseAppliction;
import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.DAO.RegisterDAO;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;

/**
 * Created by Eskii on 2018/11/30.
 * 用户注册界面
 */

public class UserRegisterActivity extends AppCompatActivity {

    private TextInputLayout telt,pwdt,confirmpwdt,namet;
    private ImageView back,register;
    private RegisterDAO registerDAO;
    private UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        initView();
    }

    private void initView(){
        telt = findViewById(R.id.t1);
        pwdt = findViewById(R.id.t2);
        confirmpwdt = findViewById(R.id.t3);
        namet = findViewById(R.id.t0);
        register = findViewById(R.id.register);
        back = findViewById(R.id.back);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = telt.getEditText().getText().toString();
                String pwd = pwdt.getEditText().getText().toString();
                String cpwd = confirmpwdt.getEditText().getText().toString();
                String name = namet.getEditText().getText().toString();
                registerDAO = new RegisterDAO((BaseAppliction)getApplication());
                userDao = ((BaseAppliction) getApplication()).getUserDao();

                if (validateTel(phone)&&validatePassword(pwd,cpwd)&&validateName(name)) {
                        if (registerDAO.existValid("用户", phone)) {    //检验用户是否存在
                            showError(telt, "用户已存在");
                        } else {
                            User user = new User(null,name,null,pwd,phone,null,null,null);
                            userDao.insert(user);   //将注册用户信息插入用户表
                            Toast.makeText(UserRegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(UserRegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRegisterActivity.this,RegisterTypeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /**
     * 显示错误提示，并获取焦点
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
     * @param phone
     * @return
     */
    private boolean validateTel(String phone){
        if(phone.isEmpty()){
            showError(telt,"手机号不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证用户名是否为空
     * @param name
     * @return
     */
    private boolean validateName(String name){
        if (name.isEmpty()){
            showError(namet,"用户名不能为空");
            return false;
        }
        return true;
    }
    /**
     * 验证密码是否符合格式，验证两次密码是否输入相同
     * @param password
     * @return
     */
    private boolean validatePassword(String password,String cpassword) {
        if (password.isEmpty()) {
            showError(pwdt,"密码不能为空");
            return false;
        } else if (password.length() < 6 || password.length() > 18) {
            showError(pwdt,"密码长度为6-18位");
            return false;
        }else if (!password.equals(cpassword)){
            showError(confirmpwdt,"两次密码输入不相同");
            return false;
        }

        return true;
    }
}
