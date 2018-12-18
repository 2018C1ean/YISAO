package com.example.dell.c1ean.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.c1ean.Application.BaseAppliction;
import com.example.dell.c1ean.Bean.Worker;
import com.example.dell.c1ean.DAO.RegisterDAO;
import com.example.dell.c1ean.R;

/**
 * Created by Eskii on 2018/11/29.
 * 员工和公司注册界面
 */

public class WorkerCompanyRegisterActivity extends AppCompatActivity{

    private TextInputLayout t0,t1,t2,t3;
    private ImageView back,register;
    private RegisterDAO registerDAO;
    private String type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workercompany_register);

        type = getIntent().getStringExtra("type");

        initView();
    }

    private void initView(){

        t0 = findViewById(R.id.t0);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        back = findViewById(R.id.back);
        register = findViewById(R.id.register);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkerCompanyRegisterActivity.this,RegisterTypeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = t0.getEditText().getText().toString();
                String phone = t1.getEditText().getText().toString();
                String pwd = t2.getEditText().getText().toString();
                String cpwd = t3.getEditText().getText().toString();
                registerDAO = new RegisterDAO((BaseAppliction)getApplication());

                if (validateCode(code)&&validateTel(phone)&&validatePassword(pwd,cpwd)){
                    if (registerDAO.existValid(type,phone)){
                        if (type.equals("家政人员")){

                        }else {

                        }
                    }else {
                        Toast.makeText(WorkerCompanyRegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                    }
                }
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
     * 判定是否公司代码为空
     * @param string
     * @return
     */
    private boolean validateCode(String string){
        String format = "[0-9]{8}[-]\\p{Upper}{1}";
        if (!string.matches(format)){
            showError(t0,"公司代码格式错误");
            return false;
        }else if (string.isEmpty()){
            showError(t0,"公司代码不能为空");
            return false;
        }return true;
    }
    /**
     * 验证手机号是否为空
     * @param string
     * @return
     */
    private boolean validateTel(String string){
        if (string.isEmpty()){
            showError(t1,"手机号不能为空");
            return false;
        }return true;
    }
    /**
     * 验证密码是否符合格式，验证两次密码是否输入相同
     * @param password
     * @return
     */
    private boolean validatePassword(String password,String cpassword) {
        if (password.isEmpty()) {
            showError(t2,"密码不能为空");
            return false;
        } else if (password.length() < 6 || password.length() > 18) {
            showError(t2,"密码长度为6-18位");
            return false;
        }else if (!password.equals(cpassword)){
            showError(t3,"两次密码输入不相同");
            return false;
        }

        return true;
    }
}
