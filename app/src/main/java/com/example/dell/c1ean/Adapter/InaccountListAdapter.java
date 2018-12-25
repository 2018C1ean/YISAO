package com.example.dell.c1ean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.c1ean.Bean.UserInaccount;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by DELL on 2018/12/21.
 */

public class InaccountListAdapter extends BaseAdapter {

    private List<UserInaccount> userInaccountList;
    private Context context;
    private LayoutInflater layoutInflater;

    public InaccountListAdapter(List<UserInaccount> userInaccountList, Context context) {
        this.userInaccountList = userInaccountList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userInaccountList.size();
    }

    @Override
    public Object getItem(int position) {
        if (userInaccountList != null){
            return  userInaccountList.get(position);
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

            viewHolder.payer_name = convertView.findViewById(R.id.payer_name);
            viewHolder.time = convertView.findViewById(R.id.time);
            viewHolder.money = convertView.findViewById(R.id.money);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        UserInaccount userinaccount = userInaccountList.get(position);
        if (userinaccount != null) {
            viewHolder.payer_name.setText(userinaccount.getPayer());
            viewHolder.time.setText(userinaccount.getTime());
            viewHolder.money.setText("+￥" + userinaccount.getMoney());
        }
        return convertView;
    }

    class ViewHolder{

        TextView payer_name,time,money;
    }
}
