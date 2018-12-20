package com.example.dell.c1ean.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.c1ean.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/14.
 */

public class NoticeAdapter extends BaseAdapter {

    public static class Item {
        public String user_tel;
        public String activity_type;

        public Item(String user_tel, String activity_type) {
            this.user_tel = user_tel;
            this.activity_type = activity_type;
        }
    }

    private List<Item> list;

    public NoticeAdapter() {
        this.list = new ArrayList<>();
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Item getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_page, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Item item = getItem(position);
        holder.user_tel.setText(item.user_tel);
        holder.activity_type.setText(item.activity_type);
        return convertView;
    }

    private static class ViewHolder {
        private TextView user_tel;
        private TextView activity_type;

        public ViewHolder(View view) {
            this.user_tel = view.findViewById(R.id.user_tel);
            this.activity_type = view.findViewById(R.id.activity_type);
        }
    }
}
