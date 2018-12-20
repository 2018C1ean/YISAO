package com.example.dell.c1ean.Activity.ADMINISTRATOR;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.c1ean.Activity.LoginRegisterActivity;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.R;

/**
 * Created by 李雯晴 on 2018/12/1.
 * 管理员界面
 */

public class AdministratorActivity extends AppCompatActivity {
    private Button companyManage, systemData,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrator_page);
        SystemApplication.getInstance().addActivity(this);

        companyManage = findViewById(R.id.insert);
        systemData = findViewById(R.id.data);
        quit = findViewById(R.id.quit);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        companyManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministratorActivity.this,CompanyManageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        systemData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdministratorActivity.this, "暂未开启此功能", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
