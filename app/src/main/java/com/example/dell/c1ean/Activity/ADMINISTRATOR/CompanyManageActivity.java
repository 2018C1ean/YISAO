package com.example.dell.c1ean.Activity.ADMINISTRATOR;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.dell.c1ean.Adapter.CompanyListItemAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/1.
 * 管理公司界面
 */

public class CompanyManageActivity extends AppCompatActivity {

    private ListView listView;
    private Button addCompany,back;
    private CompanyListItemAdapter companyListItemAdapter;
    private List<Company> companyList = new ArrayList<>();
    private CompanyDao companyDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allcompany);
        SystemApplication.getInstance().addActivity(this);

        initView();
    }

    private void initView(){

        listView = findViewById(R.id.listView);
        addCompany = findViewById(R.id.addCompany);
        back = findViewById(R.id.back);

        BaseApplication baseApplication = (BaseApplication)getApplication();
        companyDao = baseApplication.getCompanyDao();

        companyList = companyDao.loadAll();

        companyListItemAdapter = new CompanyListItemAdapter(companyList,CompanyManageActivity.this);
        listView.setAdapter(companyListItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long companyId = companyList.get(position).getCompany_id();
                Intent intent = new Intent(CompanyManageActivity.this,CompanyItemActivity.class);
                intent.putExtra("companyId",companyId);
                startActivity(intent);
                finish();
            }
        });
        addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyManageActivity.this,AddCompanyActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyManageActivity.this,AdministratorActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
