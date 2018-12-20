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

/**
 * Created by 李雯晴 on 2018/12/2.
 * 公司详情界面
 */

public class CompanyItemActivity extends AppCompatActivity {

    private TextInputLayout code,tel,name,location,introduction,pwd;
    private Button upadteCompany,deleteCompany,back;
    private CompanyDao companyDao;
    private Long companyId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_item);
        SystemApplication.getInstance().addActivity(this);

        companyId = getIntent().getLongExtra("companyId",0);    //获取公司列表界面（CompanyItemActivity）传过来的公司id

        initView();
    }

    private void initView(){
        code = findViewById(R.id.code);
        tel = findViewById(R.id.phone);
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        introduction = findViewById(R.id.introduction);
        upadteCompany = findViewById(R.id.updateCompany);
        deleteCompany = findViewById(R.id.deleteCompany);
        pwd = findViewById(R.id.pwd);
        back = findViewById(R.id.back);
        BaseApplication baseApplication = (BaseApplication)getApplication();
        companyDao = baseApplication.getCompanyDao();

        Company company = companyDao.load(companyId);

        code.getEditText().setText(company.getCompany_code()+"");
        tel.getEditText().setText(company.getCompany_tel());
        name.getEditText().setText(company.getCompany_name());
        location.getEditText().setText(company.getCompany_location());
        introduction.getEditText().setText(company.getIntroduction());
        pwd.getEditText().setText(company.getPassword());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyItemActivity.this,CompanyManageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        upadteCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ccode = code.getEditText().getText().toString();
                String ctel = tel.getEditText().getText().toString();
                String cname = name.getEditText().getText().toString();
                String clocation = location.getEditText().getText().toString();
                String cintroduction = introduction.getEditText().getText().toString();

                companyDao.update(new Company(companyId,null,cname,ccode,clocation,ctel,null,cintroduction));
                Toast.makeText(CompanyItemActivity.this,"更新成功",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CompanyItemActivity.this,CompanyManageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deleteCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyDao.deleteByKey(companyId);
                Toast.makeText(CompanyItemActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CompanyItemActivity.this,CompanyManageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
