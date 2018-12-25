package com.example.dell.c1ean.Activity.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.example.dell.c1ean.Activity.ActivityDetailPageActivity;
import com.example.dell.c1ean.Activity.LoginActivity;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.Bean.UserOutaccount;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.DAO.UserOutaccountDao;
import com.example.dell.c1ean.R;
import com.example.dell.c1ean.Util.PayPwdEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/18.
 */
public class OrderActivity extends AppCompatActivity {

    private TextView booking_time;
    private EditText room_size, clean_number;
    private TextView user_location;
    private TextView expect_money;
    private Button reserve;
    private OrderDao orderDao;
    private UserDao userDao;
    private CompanyDao companyDao;
    private Long user_id;
    private Long activity_id;
    private CompanyActivity companyActivity;
    private CompanyActivityDao companyActivityDao;
    private LinearLayout linearLayout;
    private ProgressBar progress;
    private TextView wrong_pwd;
    private TextView correct_pwd, alipay_title,money;
    private ImageView cancel, back, activity_img;
    private PayPwdEditText payPwdEditText;
    private Button confirm_pay;
    private Handler handler;
    private String type;
    private LinearLayout type1, type2;
    private TextView company_name, activity_title;
    private UserOutaccountDao userOutaccountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        SystemApplication.getInstance().addActivity(this);

        activity_id = intent.getLongExtra("activity_id", 0);
        companyActivityDao = ((BaseApplication) getApplication()).getCompanyActivityDao();
        orderDao = ((BaseApplication) getApplication()).getOrderDao();
        user_id = ((BaseApplication) getApplication()).getUSER_ID();
        userDao = ((BaseApplication) getApplication()).getUserDao();
        companyDao = ((BaseApplication) getApplication()).getCompanyDao();
        userOutaccountDao = ((BaseApplication) getApplication()).getUserOutaccountDao();

        companyActivity = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique();

        type = companyActivity.getType();
        setContentView(R.layout.order);

        /**
         * 对话框
         */
        linearLayout = (LinearLayout) LayoutInflater.from(OrderActivity.this).inflate(R.layout.pay_dialog, null);
        progress = linearLayout.findViewById(R.id.progress);

        wrong_pwd = linearLayout.findViewById(R.id.wrong_pwd);
        correct_pwd = linearLayout.findViewById(R.id.correct_pwd);
        cancel = linearLayout.findViewById(R.id.cancel);
        payPwdEditText = linearLayout.findViewById(R.id.pay_pwd);
        confirm_pay = linearLayout.findViewById(R.id.confirm_pay);
        alipay_title = linearLayout.findViewById(R.id.title);
        money = linearLayout.findViewById(R.id.money);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x11) {
                    progress.setVisibility(View.GONE);
                    Intent intent = new Intent(OrderActivity.this, PayedActivity.class);
                    startActivity(intent);
                }
            }
        };
        /**
         *
         */

        booking_time = findViewById(R.id.book_time);
        user_location = findViewById(R.id.user_location);
        expect_money = findViewById(R.id.expect_money);
        reserve = findViewById(R.id.reserve);
        type1 = findViewById(R.id.type1);
        type2 = findViewById(R.id.type2);
        room_size = findViewById(R.id.room_size);
        clean_number = findViewById(R.id.clean_number);
        back = findViewById(R.id.back);
        company_name = findViewById(R.id.company_name);
        activity_title = findViewById(R.id.activity_title);
        activity_img = findViewById(R.id.activity_img);

        setView();

    }


    private void setView() {

        //设置活动卡片内容
        Bitmap bitmap = BitmapFactory.decodeFile(companyActivity.getImg1());
        activity_img.setImageBitmap(bitmap);
        company_name.setText(companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(companyActivity.getCompany_id())).unique().getCompany_name());
        activity_title.setText(companyActivity.getTitle());

        if (type.equals("专业保洁") || type.equals("家居养护")) {
            type1.setVisibility(View.VISIBLE);
        } else {
            type2.setVisibility(View.VISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        booking_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog datePickDialog = new DatePickDialog(OrderActivity.this);
                //设置上下年分限制
                datePickDialog.setYearLimt(5);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_ALL);
                //设置消息体的显示格式，日期格式
                datePickDialog.setMessageFormat("yyyy-MM-dd-hh-mm");
                //设置选择回调
                datePickDialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        //设置时间格式
                        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日hh时mm分");
                        booking_time.setText(timeStampFormat.format(date));
                    }
                });

                datePickDialog.show();
            }
        });

        //根据输入实时变更预估价
        room_size.addTextChangedListener(textWatcher1);
        clean_number.addTextChangedListener(textWatcher1);

        //用户地址选择
        user_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();

                if (user.getLocation1() == null) {
                    android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(OrderActivity.this);
                    dialog.setMessage("您还未设置地址，请先去设置地址");
                    dialog.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(OrderActivity.this, LocationManageActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putLong("activity_id", activity_id);
                            bundle.putString("booking_time", booking_time.getText().toString());
                            bundle.putString("add_location", "add_location");
                            intent.putExtras(bundle);
                            startActivityForResult(intent, 99);
                        }
                    });
                    dialog.setNegativeButton("不了，谢谢", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                } else {

                    //获取用户的地址
                    List<String> list = new ArrayList<>();

                    String location;
                    if (user.getLocation1() != null) {
                        String[] l = user.getLocation1().split("&");
                        location = l[0] + "\n" + l[1];
                        list.add(location);
                    }
                    if (user.getLocation2() != null) {
                        String[] l = user.getLocation2().split("&");
                        location = l[0] + "\n" + l[1];
                        list.add(location);
                    }
                    if (user.getLocation3() != null) {
                        String[] l = user.getLocation3().split("&");
                        location = l[0] + "\n" + l[1];
                        list.add(location);
                    }

                    final String[] s = new String[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        s[i] = list.get(i);
                    }

                    android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(OrderActivity.this);
                    dialog.setSingleChoiceItems(s,  //装载数组信息
                            //默认选中第一项
                            0,
                            //为列表添加监听事件
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            user_location.setText(s[0]);
                                            break;
                                        case 1:
                                            user_location.setText(s[1]);
                                            break;
                                        case 2:
                                            user_location.setText(s[2]);
                                            break;
                                    }
                                    dialog.cancel();
                                }
                            });
                    dialog.show();
                }
            }
        });

        //点击付款
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }


        });
    }

    TextWatcher textWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (type.equals("专业保洁") || type.equals("家居养护")) {
                //根据房间大小预估价格发生相应变化
                String size = room_size.getText().toString();
                expect_money.setText(getExpectMoney(size));
            } else {
                String item_num = clean_number.getText().toString();
                expect_money.setText(getExpectMoney(item_num));
            }
        }
    };

    /**
     * 支付对话框
     */
    private void setDialog() {

        final Dialog dialog = new Dialog(OrderActivity.this, R.style.BottomDialog);

        money.setText(expect_money.getText().toString());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                String book_time = booking_time.getText().toString();
                String location = user_location.getText().toString();
                String m = expect_money.getText().toString();
                String money = m.substring(1, m.length());   //去掉￥
                //设置时间格式
                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
                //获取当前系统时间
                String order_time = timeStampFormat.format(new Date());

                if (type.equals("专业保洁") || type.equals("家居养护")) {

                    String size = room_size.getText().toString();
                    Order order = new Order(null, user_id, null, companyActivity.getCompany_id(), companyActivity.getType(), size, null, location, money, order_time, book_time, null, 0, null, null, 0, activity_id, 1);
                    orderDao.insert(order);
                } else {
                    String item_num = clean_number.getText().toString();
                    Order order = new Order(null, user_id, null, companyActivity.getCompany_id(), companyActivity.getType(), null, item_num, location, money, order_time, book_time, null, 0, null, null, 0, activity_id, 1);
                    orderDao.insert(order);
                }
                Toast.makeText(OrderActivity.this, "服务订单已放入待支付订单中~", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderActivity.this, ActivityDetailPageActivity.class);
                intent.putExtra("activity_item_id", activity_id);
                startActivity(intent);
            }
        });

        //密码输入结束的监听器
        payPwdEditText.setOnInputFinishListener(new PayPwdEditText.OnInputFinishListener() {
            @Override
            public void onInputFinish(String password) {
                correct_pwd.setVisibility(View.VISIBLE);
            }
        });

        confirm_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = payPwdEditText.getText().toString();
                if (pwd != null) {
                    progress.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.INVISIBLE);
                    alipay_title.setVisibility(View.INVISIBLE);
                    payPwdEditText.setVisibility(View.INVISIBLE);
                    correct_pwd.setVisibility(View.INVISIBLE);
                    wrong_pwd.setVisibility(View.INVISIBLE);
                    money.setVisibility(View.INVISIBLE);
                    /**
                     * 点击付款按钮将会把订单放在全部订单中
                     * 所以订单的状态为1（表示已支付）
                     */
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            message.what = 0x11;
                            handler.sendMessage(message);
                            //设置时间格式
                            SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
                            //获取当前系统时间
                            String order_time = timeStampFormat.format(new Date());
                            String book_time = booking_time.getText().toString();
                            String location = user_location.getText().toString();
                            String m = expect_money.getText().toString();
                            String money = m.substring(1, m.length());   //去掉￥
                            dialog.cancel();
                            if (type.equals("专业保洁") || type.equals("家居养护")) {
                                String size = room_size.getText().toString();
                                Order order = new Order(null, user_id, null, companyActivity.getCompany_id(), companyActivity.getType(), size, null, location, money, order_time, book_time, null, 1, null, null, 0, activity_id, 1);
                                orderDao.insert(order);
                                //支出插入用户支出表
                                UserOutaccount userOutaccount = new UserOutaccount(null,user_id,"支付宝",order_time,money,company_name.getText().toString());
                                userOutaccountDao.insert(userOutaccount);
                            } else {
                                String item_num = clean_number.getText().toString();
                                Order order = new Order(null, user_id, null, companyActivity.getCompany_id(), companyActivity.getType(), null, item_num, location, money, order_time, book_time, null, 1, null, null, 0, activity_id, 1);
                                orderDao.insert(order);
                                //支出插入用户支出表
                                UserOutaccount userOutaccount = new UserOutaccount(null,user_id,"支付宝",order_time,money,company_name.getText().toString());
                                userOutaccountDao.insert(userOutaccount);
                            }
                            Intent intent = new Intent(OrderActivity.this, PayedActivity.class);
                            Order order = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id),OrderDao.Properties.OrderTime.eq(order_time)).unique();
                            intent.putExtra("order_id",order.getId());
                            startActivity(intent);
                        }

                    }).start();
                } else {
                    Toast.makeText(OrderActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(linearLayout);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = getResources().getDisplayMetrics().widthPixels; // 宽度
        linearLayout.measure(0, 0);
        lp.height = linearLayout.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        window.setAttributes(lp);
        dialog.show();  //显示对话框
    }

    /**
     * 获取价格（应支付价格）
     *
     * @return
     */
    private String getExpectMoney(String s) {

        String yuan = "￥";
        Float money = companyActivity.getPrice();

        if (s.equals("")) {
            return yuan + 0.0;
        } else {
            if (type.equals("专业保洁") || type.equals("家居养护")) {

                Float size = Float.parseFloat(s);
                if (size > 0 && size <= 20) {
                    return yuan + money * 0.5;
                } else if (size > 20 && size <= 40) {
                    return yuan + money;
                } else if (size > 40 && size <= 80) {
                    return yuan + money * 1.5;
                } else if (size > 80 && size <= 100) {
                    return yuan + money * 2;
                } else if (size > 100 && size <= 120) {
                    return yuan + money * 2.5;
                } else if (size > 120 && size <= 140) {
                    return yuan + size * 3;
                } else if (size > 140 && size <= 160) {
                    return yuan + size * 4;
                } else if (size >= 160 && size <= 180) {
                    return yuan + money * 4.5;
                } else if (size > 180 && size <= 200) {
                    return yuan + money * 5;
                } else if (size > 200 && size <= 350) {
                    return yuan + money * 6;
                } else if (size > 350 && size <= 400) {
                    return yuan + money * 7;
                } else {
                    return yuan + money * 8;
                }
            } else {

                //如果是按件数来算的话直接返回单价乘以件数
                int item_num = Integer.parseInt(s);
                return yuan + item_num * money;
            }
        }

    }

    /**
     * 屏蔽返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 99 && requestCode == 99) {

            Bundle bundle = data.getExtras();
            activity_id = bundle.getLong("activity_id", 0);
            booking_time.setText(bundle.getString("booking_time"));
            user_location.setText(bundle.getString("location"));
        }

    }
}
