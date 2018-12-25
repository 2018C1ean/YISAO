package com.example.dell.c1ean.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Activity.ActivityDetailPageActivity;
import com.example.dell.c1ean.Activity.Company.CompanyMainPageActivity;
import com.example.dell.c1ean.Activity.Company.ManageActivityActivity;
import com.example.dell.c1ean.Activity.User.OrderActivity;
import com.example.dell.c1ean.Activity.User.PayedActivity;
import com.example.dell.c1ean.Activity.User.UserEvaluationActivity;
import com.example.dell.c1ean.Activity.User.UserMainPageActivity;
import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.Bean.UserInaccount;
import com.example.dell.c1ean.Bean.UserOutaccount;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.UserInaccountDao;
import com.example.dell.c1ean.R;
import com.example.dell.c1ean.Util.PayPwdEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/20.
 */

public class OrderListAdapter extends BaseAdapter {

    private Context context;
    private List<Order> orderList;
    private CompanyDao companyDao;
    private CompanyActivityDao companyActivityDao;
    private LayoutInflater layoutInflater;
    private OrderDao orderDao;
    private UserInaccountDao userInaccountDao;

    public OrderListAdapter(Context context, List<Order> orderList, CompanyDao companyDao, CompanyActivityDao companyActivityDao, OrderDao orderDao, UserInaccountDao userInaccountDao) {
        this.context = context;
        this.orderList = orderList;
        this.companyDao = companyDao;
        this.companyActivityDao = companyActivityDao;
        layoutInflater = LayoutInflater.from(context);
        this.orderDao = orderDao;
        this.userInaccountDao = userInaccountDao;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        if (orderList.size() > 0) {
            return orderList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.order_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.company_name = convertView.findViewById(R.id.company_name);
            viewHolder.order_state = convertView.findViewById(R.id.order_state);
            viewHolder.activity_describe = convertView.findViewById(R.id.activity_describe);
            viewHolder.booking_time = convertView.findViewById(R.id.booking_time);
            viewHolder.order_price = convertView.findViewById(R.id.order_price);
            viewHolder.order_img = convertView.findViewById(R.id.order_img);
            viewHolder.delete_order = convertView.findViewById(R.id.delete_order);
            viewHolder.evaluation_order = convertView.findViewById(R.id.evaluation_order);
            viewHolder.cancel_order = convertView.findViewById(R.id.cancel_order);
            viewHolder.pay_order = convertView.findViewById(R.id.pay_order);
            viewHolder.confirm_service = convertView.findViewById(R.id.confirm_service);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final Order order = orderList.get(position);
        viewHolder.company_name.setText(companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(order.getCompany_id())).unique().getCompany_name());
        viewHolder.activity_describe.setText(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(order.getActivity_id())).unique().getTitle());
        viewHolder.booking_time.setText(order.getBookingTime());
        viewHolder.order_price.setText("￥"+order.getMoney());
        Bitmap bitmap = BitmapFactory.decodeFile(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(order.getActivity_id())).unique().getImg1());
        viewHolder.order_img.setBackground(new BitmapDrawable(bitmap));

        //获取订单的状态
        final int state = orderList.get(position).getState();

        //订单状态：0为用户未支付；1为用户已支付；2为商家已接单；3为已结束服务；
        //订单上有五个按钮："删除订单"、"取消订单"、"评价订单"、"去支付"、"确认结束服务"
        /**
         * 用户未支付时
         * 订单状态文字设置为“待支付”
         * “去支付”按钮可见
         * “取消订单”的按钮可见
         */
        if (state == 0){    //用户未支付
            viewHolder.order_state.setText("待支付");
            viewHolder.cancel_order.setVisibility(View.VISIBLE);
            viewHolder.pay_order.setVisibility(View.VISIBLE);
            viewHolder.evaluation_order.setVisibility(View.GONE);
            viewHolder.delete_order.setVisibility(View.GONE);
            viewHolder.confirm_service.setVisibility(View.GONE);
        }
        /**
         * 当用户已支付时
         * 订单状态文字设置为“已支付”
         * “取消订单”的按钮设置为可见
         */
        else if (state == 1){
            viewHolder.order_state.setText("已支付");
            viewHolder.cancel_order.setVisibility(View.VISIBLE);
            viewHolder.pay_order.setVisibility(View.GONE);
            viewHolder.evaluation_order.setVisibility(View.GONE);
            viewHolder.delete_order.setVisibility(View.GONE);
            viewHolder.confirm_service.setVisibility(View.GONE);
        }
        /**
         *当商家已接单时
         * 订单状态文字设置为“商家已接单”
         * 用户“确认服务结束”按钮设为可见
         */
        else if (state == 2){
            viewHolder.order_state.setText("商家已接单");
            viewHolder.confirm_service.setVisibility(View.VISIBLE);
            viewHolder.pay_order.setVisibility(View.GONE);
            viewHolder.evaluation_order.setVisibility(View.GONE);
            viewHolder.delete_order.setVisibility(View.GONE);
            viewHolder.cancel_order.setVisibility(View.GONE);
        }
        /**
         * 当服务已结束（用户确认服务结束时）
         * 订单状态文字设为“服务已结束”
         * "删除订单"按钮设为可见
         * "评价订单"设为可见
         */
        else {
            viewHolder.order_state.setText("服务已结束");
            viewHolder.delete_order.setVisibility(View.VISIBLE);
            viewHolder.evaluation_order.setVisibility(View.VISIBLE);
            viewHolder.confirm_service.setVisibility(View.GONE);
            viewHolder.pay_order.setVisibility(View.GONE);
            viewHolder.cancel_order.setVisibility(View.GONE);
        }

        //给按钮设置点击事件

        viewHolder.delete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage("您确定要删除此订单吗？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Order order1 = orderList.get(position);
                        orderDao.delete(order1);    //删除选中订单
                        Toast.makeText(context, "订单删除成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, UserMainPageActivity.class);
                        context.startActivity(intent);

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        viewHolder.evaluation_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到评价页面
                Intent intent = new Intent(context, UserEvaluationActivity.class);
                intent.putExtra("order_id",orderList.get(position).getId());    //放入要评价的订单id
                context.startActivity(intent);
            }
        });

        viewHolder.cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage("您确定要删除此订单吗？"+"\n"+"已支付的订金将会退回到支付宝~");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (state == 0){

                            Order order1 = orderList.get(position);
                            orderDao.delete(order1);
                            Toast.makeText(context, "订单取消成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, UserMainPageActivity.class);
                            context.startActivity(intent);
                        }else {

                            if (state != 3){

                                //设置时间格式
                                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
                                //获取当前系统时间
                                String cancel_time = timeStampFormat.format(new Date());
                                Order order1 = orderList.get(position);
                                Company company = companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(order1.getCompany_id())).unique();
                                float money = Float.parseFloat(order1.getMoney());
                                //退回的钱放到用户的收入表
                                UserInaccount userInaccount = new UserInaccount(null,order1.getUser_id(),cancel_time,money,company.getCompany_name());
                                userInaccountDao.insert(userInaccount);
                                //订单表删除该订单
                                orderDao.delete(order1);
                                Toast.makeText(context, "订单取消成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, UserMainPageActivity.class);
                                context.startActivity(intent);
                            }
                        }

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });

        viewHolder.confirm_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order1 = orderList.get(position);
                order1.setState(3); //更新订单状态为3（用户确认服务结束）
                orderDao.update(order1);
                Toast.makeText(context, "确认成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,UserMainPageActivity.class);
                context.startActivity(intent);
            }
        });

        viewHolder.pay_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.BottomDialog);

                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pay_dialog, null);

                final TextView correct_pwd = linearLayout.findViewById(R.id.correct_pwd);
                final TextView cancel = linearLayout.findViewById(R.id.cancel);
                final PayPwdEditText payPwdEditText = linearLayout.findViewById(R.id.pay_pwd);
                Button confirm_pay = linearLayout.findViewById(R.id.confirm_pay);
                final TextView money = linearLayout.findViewById(R.id.money);

                money.setText(orderList.get(position).getMoney()+"");

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
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

                        Order order1 = orderList.get(position);
                        order1.setState(1);
                        orderDao.update(order1);
                        Toast.makeText(context, "支付成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,UserMainPageActivity.class);
                        context.startActivity(intent);
                    }
                });

                dialog.setContentView(linearLayout);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                WindowManager.LayoutParams lp = window.getAttributes(); // 获取对话框当前的参数值
                lp.x = 0; // 新位置X坐标
                lp.y = 0; // 新位置Y坐标
                lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
                linearLayout.measure(0, 0);
                lp.height = linearLayout.getMeasuredHeight();
                lp.alpha = 9f; // 透明度
                window.setAttributes(lp);
                dialog.show();  //显示对话框
            }
        });

        return convertView;
    }

    class ViewHolder {

        TextView company_name, order_state, activity_describe, booking_time, order_price;
        ImageView order_img;
        TextView delete_order, evaluation_order, cancel_order,pay_order,confirm_service;

    }
}
