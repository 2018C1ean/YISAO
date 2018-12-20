package com.example.dell.c1ean.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Adapter.LocationListAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/18.
 */

public class ShowLocationActivity extends AppCompatActivity {

    private FloatingActionButton add_location;
    private ListView location_list;
    private ImageView back;
    private UserDao userDao;
    private Long user_id;
    private List<String> list = new ArrayList<>();
    private TextView no_location;
    private LocationListAdapter locationListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_user_location);
        SystemApplication.getInstance().addActivity(this);

        back = findViewById(R.id.back);
        location_list = findViewById(R.id.user_location_list);
        add_location = findViewById(R.id.add_location);
        no_location = findViewById(R.id.no_location);

        user_id = ((BaseApplication) getApplication()).getUSER_ID();
        userDao = ((BaseApplication) getApplication()).getUserDao();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //查询当前的user
        User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();

        if (user.getLocation1() != null) {
            list.add(user.getLocation1());
        }
        if (user.getLocation2() != null) {
            list.add(user.getLocation2());
        }
        if (user.getLocation3() != null) {
            list.add(user.getLocation3());
        }

        Toast.makeText(this, user.getLocation1()+"\n"+user.getLocation2()+"\n"+user.getLocation3(), Toast.LENGTH_SHORT).show();
        if (list.size() > 0) {
            locationListAdapter = new LocationListAdapter(list, ShowLocationActivity.this, userDao, user_id);
            location_list.setAdapter(locationListAdapter);
        } else {
            no_location.setVisibility(View.VISIBLE);
        }

        location_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowLocationActivity.this, LocationManageActivity.class);
                intent.putExtra("item_id", id);
                startActivity(intent);
            }
        });

        if (list.size() < 3) {
            add_location.setVisibility(View.VISIBLE);
            add_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShowLocationActivity.this, LocationManageActivity.class);
                    int n = (3 - list.size());
//                    Toast.makeText(ShowLocationActivity.this, n+"", Toast.LENGTH_SHORT).show();
                    intent.putExtra("location_num", n);
                    startActivity(intent);
                }
            });
        } else {
            add_location.setVisibility(View.INVISIBLE);
        }
    }
}
