package com.example.dell.c1ean.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cf.androidpickerlibrary.AddressPicker;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;

/**
 * Created by 李雯晴 on 2018/12/18.
 */

public class LocationManageActivity extends AppCompatActivity {

    private Button update, delete, add;
    private TextView location_num, location_title;
    private EditText location_detail;
    private LinearLayout linearLayout;
    private int n; //用来标识是修改还是添加
    private Long item_id; //地址列表的item id
    private UserDao userDao;
    private Long user_id;
    private ImageView back;
    private Long activity_id;
    private String isOrderAddLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_manage);

        SystemApplication.getInstance().addActivity(this);

        n = getIntent().getIntExtra("location_num", 0);

        item_id = getIntent().getLongExtra("item_id", 0);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            activity_id = bundle.getLong("activity_id");

            isOrderAddLocation = bundle.getString("add_location", "no");
        } else {
            isOrderAddLocation = "no";
        }

//        Toast.makeText(LocationManageActivity.this, activity_id+"", Toast.LENGTH_SHORT).show();

        back = findViewById(R.id.back);
        linearLayout = findViewById(R.id.button_line);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        add = findViewById(R.id.sure);
        location_num = findViewById(R.id.location_num);
        location_num.setText("地址");
        location_title = findViewById(R.id.location_title);
        location_detail = findViewById(R.id.location_detail);

        userDao = ((BaseApplication) getApplication()).getUserDao();
        user_id = ((BaseApplication) getApplication()).getUSER_ID();

        if (n != 0 || isOrderAddLocation.equals("add_location")) {
            update.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
            add.setVisibility(View.VISIBLE);
        } else {
            User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
            if (item_id == 0) {
                String s = user.getLocation1();
                String[] locations = s.split("&");
                location_title.setText(locations[0]);
                location_detail.setText(locations[1]);
            } else if (item_id == 1) {
                String s = user.getLocation2();
                String[] locations = s.split("&");
                location_title.setText(locations[0]);
                location_detail.setText(locations[1]);
            } else {
                String s = user.getLocation3();
                String[] locations = s.split("&");
                location_title.setText(locations[0]);
                location_detail.setText(locations[1]);
            }
        }

        setView();
    }

    private void setView() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOrderAddLocation.equals("add_location")) {
                    Bundle bundle = getIntent().getExtras();
                    Intent intent = new Intent(LocationManageActivity.this, OrderActivity.class);
                    intent.putExtras(bundle);
                    setResult(99, intent);
                    finish();
                } else {
                    Intent intent = new Intent(LocationManageActivity.this, ShowLocationActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        /**
         * location_title和location_detail之间用分割符“&”连接，然后合并存入用户的地址字段
         */
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = location_title.getText().toString();
                String d = location_detail.getText().toString();

                if (t.isEmpty()) {
                    Toast.makeText(LocationManageActivity.this, "地区不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (d.isEmpty()) {
                        Toast.makeText(LocationManageActivity.this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
                        if (item_id == 0) {
                            user.setLocation1(t + "&" + d);
                        } else if (item_id == 1) {
                            user.setLocation2(t + "&" + d);
                        } else {
                            user.setLocation3(t + "&" + d);
                        }
                        userDao.update(user);

                        finish();
                        Toast.makeText(LocationManageActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LocationManageActivity.this, ShowLocationActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        /**
         *
         * 采用队列的思想，如果地址一被删，地址二作为地址一，地址三作为地址二，其他同理
         */
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
                if (item_id == 0) {
                    user.setLocation1(user.getLocation2());
                    user.setLocation2(user.getLocation3());
                    user.setLocation3(null);
                } else if (item_id == 1) {
                    user.setLocation2(user.getLocation3());
                    user.setLocation3(null);
                } else {
                    user.setLocation3(null);
                }

                userDao.update(user);
                finish();
                Toast.makeText(LocationManageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LocationManageActivity.this, ShowLocationActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t = location_title.getText().toString();
                String d = location_detail.getText().toString();

                if (!isOrderAddLocation.equals("add_location")) {
                    if (t.isEmpty()) {
                        Toast.makeText(LocationManageActivity.this, "地区不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        if (d.isEmpty()) {
                            Toast.makeText(LocationManageActivity.this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();

                            if (n == 3) {
                                Toast.makeText(LocationManageActivity.this, "3", Toast.LENGTH_SHORT).show();
                                user.setLocation1(t + "&" + d);
                            } else if (n == 2) {
                                Toast.makeText(LocationManageActivity.this, "2", Toast.LENGTH_SHORT).show();

                                user.setLocation2(t + "&" + d);
                            } else {
                                Toast.makeText(LocationManageActivity.this, "1", Toast.LENGTH_SHORT).show();

                                user.setLocation3(t + "&" + d);
                            }

                            userDao.update(user);

                            finish();
                            Toast.makeText(LocationManageActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LocationManageActivity.this, ShowLocationActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                } else {
                    //跳转回下订单界面(用户没有地址信息时)
                    if (t.isEmpty()) {
                        Toast.makeText(LocationManageActivity.this, "地区不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        if (d.isEmpty()) {
                            Toast.makeText(LocationManageActivity.this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
                            user.setLocation1(t + "&" + d);
                            userDao.update(user);
                            Toast.makeText(LocationManageActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            Bundle bundle = getIntent().getExtras();
                            bundle.putString("location", t + "\n" + d);
                            Intent intent = new Intent(LocationManageActivity.this, OrderActivity.class);
                            intent.putExtras(bundle);
                            setResult(99, intent);
                            finish();
                        }
                    }
                }

            }
        });

        //选择地区
        location_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressPicker picker = new AddressPicker(LocationManageActivity.this);

                picker.setAddressListener(new AddressPicker.OnAddressListener() {
                    @Override
                    public void onAddressSelected(String province, String city, String area) {
                        location_title.setText(province + "-" + city + "-" + area);
                    }
                });

                picker.show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isOrderAddLocation.equals("add_location")) {
                Bundle bundle = getIntent().getExtras();
                Intent intent = new Intent(LocationManageActivity.this, OrderActivity.class);
                intent.putExtras(bundle);
                setResult(99, intent);
                finish();
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
