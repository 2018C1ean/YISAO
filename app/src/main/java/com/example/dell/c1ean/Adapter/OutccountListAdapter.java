package com.example.dell.c1ean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.c1ean.Bean.UserOutaccount;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/21.
 */

public class OutccountListAdapter extends BaseAdapter {

    private List<UserOutaccount> userOutaccountList;
    private Context context;
    private LayoutInflater layoutInflater;

    public OutccountListAdapter(List<UserOutaccount> userOutaccountList, Context context) {
        this.userOutaccountList = userOutaccountList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return userOutaccountList.size();
    }

    @Override
    public Object getItem(int position) {
        if (userOutaccountList != null){
            return  userOutaccountList.get(position);
        }else {
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

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.out_or_inaccount_listitem,null);
            viewHolder = new ViewHolder();

            viewHolder.company_name = convertView.findViewById(R.id.payer_name);
            viewHolder.time = convertView.findViewById(R.id.time);
            viewHolder.money = convertView.findViewById(R.id.money);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        UserOutaccount userOutaccount = userOutaccountList.get(position);
        if (userOutaccount != null) {
            viewHolder.company_name.setText(userOutaccount.getPayee());
            viewHolder.time.setText(userOutaccount.getTime());
            viewHolder.money.setText("-￥" + userOutaccount.getMoney());
        }
        return convertView;
    }

    class ViewHolder{

        TextView company_name,time,money;
    }
}
