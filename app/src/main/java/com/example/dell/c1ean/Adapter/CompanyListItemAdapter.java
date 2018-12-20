package com.example.dell.c1ean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/1.
 * 管理员模式的公司列表适配器
 */

public class CompanyListItemAdapter extends BaseAdapter {

    private List<Company> companyList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CompanyListItemAdapter(List<Company> companyList,Context context) {

        this.companyList = companyList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return companyList.size();
    }

    @Override
    public Object getItem(int i) {
        return companyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null){

            view = layoutInflater.inflate(R.layout.company_list_item,null);
            viewHolder = new ViewHolder();

            viewHolder.id = view.findViewById(R.id.companyId);
            viewHolder.companyName = view.findViewById(R.id.companyName);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.id.setText(companyList.get(i).getCompany_id()+"");
        viewHolder.companyName.setText(companyList.get(i).getCompany_name());

        return view;
    }

    private class ViewHolder{

        TextView id,companyName;
    }
}
