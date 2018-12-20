package com.example.dell.c1ean.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.R;

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

    public OrderListAdapter(Context context, List<Order> orderList, CompanyDao companyDao, CompanyActivityDao companyActivityDao) {
        this.context = context;
        this.orderList = orderList;
        this.companyDao = companyDao;
        this.companyActivityDao = companyActivityDao;
        layoutInflater = LayoutInflater.from(context);

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
    public View getView(int position, View convertView, ViewGroup parent) {
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

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int state = orderList.get(position).getState();
        Order order = orderList.get(position);
        viewHolder.company_name.setText(companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(order.getCompany_id())).unique().getCompany_name());
        viewHolder.activity_describe.setText(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(order.getActivity_id())).unique().getTitle());
        viewHolder.booking_time.setText(order.getBookingTime());
        viewHolder.order_price.setText("￥"+order.getMoney());
        Bitmap bitmap = BitmapFactory.decodeFile(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(order.getActivity_id())).unique().getImg1());
//        viewHolder.order_img.setImageBitmap(bitmap);
        viewHolder.order_img.setBackground(new BitmapDrawable(bitmap));
        //订单已完成，显示“删除订单”和“评价订单”
        if (state == 3) {

            viewHolder.delete_order.setVisibility(View.VISIBLE);
            viewHolder.evaluation_order.setVisibility(View.VISIBLE);
            viewHolder.order_state.setText("已结束服务");


        } else if (state == 0 || state == 1) {
            //如果订单未付款或已付款（商家未接单），显示“取消订单”
            viewHolder.cancel_order.setVisibility(View.VISIBLE);
            viewHolder.order_state.setText("已结束服务");
            if (state == 0) {
                viewHolder.order_state.setText("待支付");
            } else {
                viewHolder.order_state.setText("已支付");
            }
        } else { //订单状态为2（商家已接单）
            viewHolder.delete_order.setVisibility(View.GONE);
            viewHolder.evaluation_order.setVisibility(View.GONE);
            viewHolder.cancel_order.setVisibility(View.GONE);
        }

        viewHolder.delete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.evaluation_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    class ViewHolder {

        TextView company_name, order_state, activity_describe, booking_time, order_price;
        ImageView order_img;
        TextView delete_order, evaluation_order, cancel_order;

    }
}
