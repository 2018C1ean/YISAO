package com.example.dell.c1ean.Activity.ADMINISTRATOR;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 李雯晴 on 2018/12/1.
 * 添加公司信息界面
 */

public class AddCompanyActivity extends AppCompatActivity {

    private TextInputLayout code, tel, name, location, introduction;
    private Button addCompany, back;
    private CompanyDao companyDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcompany);
        SystemApplication.getInstance().addActivity(this);

        initView();
    }

    private void initView() {

        code = findViewById(R.id.code);
        tel = findViewById(R.id.phone);
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        introduction = findViewById(R.id.introduction);
        addCompany = findViewById(R.id.addCompany);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCompanyActivity.this, CompanyManageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ccode = code.getEditText().getText().toString();
                String ctel = tel.getEditText().getText().toString();
                String cname = name.getEditText().getText().toString();
                String clocation = location.getEditText().getText().toString();
                String cintroduction = introduction.getEditText().getText().toString();

                BaseApplication baseApplication = (BaseApplication) getApplication();
                companyDao = baseApplication.getCompanyDao();

                if (ccode.isEmpty() && ctel.isEmpty() && cname.isEmpty() && clocation.isEmpty() && cintroduction.isEmpty()) {
                    Toast.makeText(AddCompanyActivity.this, "所填信息不能为空", Toast.LENGTH_LONG).show();
                } else {
                    if (validateCode(ccode)) {
                        if (validateTel(ctel)) {
                            Company company = new Company(null, null, cname, ccode, clocation, ctel, null, cintroduction);
                            companyDao.insert(company);
                            Toast.makeText(AddCompanyActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AddCompanyActivity.this, CompanyManageActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        });
    }

    /**
     * 判定是否公司代码为空
     *
     * @param string
     * @return
     */
    public boolean validateCode(String string) {
        String format = "[0-9]{8}[-]\\p{Upper}{1}";
        if (!string.matches(format)) {
            showError(code, "公司代码格式错误");
            return false;
        } else if (string.isEmpty()) {
            showError(code, "公司代码不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证手机号是否为空
     *
     * @param phone
     * @return
     */
    public boolean validateTel(String phone) {
        if (phone.isEmpty()) {
            showError(tel, "手机号不能为空");
            return false;
        } else {
            Pattern pattern = Pattern.compile("^1[3,5]\\d{9}||18[6,8,9]\\d{8}$");
            Matcher matcher = pattern.matcher(phone);
            if (phone.length() < 11 && !matcher.matches()) {
                showError(tel, "手机号格式错误");
                return false;
            } else {
                return true;
            }

        }
    }

    /**
     * 显示错误提示，并获取焦点
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
}
