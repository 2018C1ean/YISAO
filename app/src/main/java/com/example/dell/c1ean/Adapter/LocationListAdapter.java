package com.example.dell.c1ean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by DELL on 2018/12/18.
 */

public class LocationListAdapter extends BaseAdapter {

    private List<String> locationList;
    private Context context;
    private LayoutInflater layoutInflater;
    private UserDao userDao;
    private Long user_id;

    public LocationListAdapter(List<String> locationList, Context context, UserDao userDao, Long useer_id) {
        this.locationList = locationList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.userDao = userDao;
        this.user_id = useer_id;
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Object getItem(int position) {
        if (locationList != null) {
            return locationList.get(position);
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

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.location_item, null);
            viewHolder = new ViewHolder();

            viewHolder.id = convertView.findViewById(R.id.location_num);
            viewHolder.location_title = convertView.findViewById(R.id.location_title);
            viewHolder.location_detail = convertView.findViewById(R.id.location_detail);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.id.setText("地址" + (position + 1));

        /**
         * 因为地址分两段（第一段和第二段用“&”分隔），先取除一整串，再进行分割，然后分别放入
         */
        String s = locationList.get(position);
        String[] locations = s.split("&");
        viewHolder.location_title.setText(locations[0]);
        viewHolder.location_detail.setText(locations[1]);
        viewHolder.location_detail.setEnabled(false);
        viewHolder.location_detail.setFocusable(false);
        viewHolder.location_detail.setKeepScreenOn(false);

        return convertView;
    }

    class ViewHolder {

        TextView id, location_title;
        EditText location_detail;
    }
}
