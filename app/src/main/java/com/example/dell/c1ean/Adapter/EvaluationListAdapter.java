package com.example.dell.c1ean.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.DAO.WorkerDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by DELL on 2018/12/16.
 */

public class EvaluationListAdapter extends BaseAdapter {

    private List<Order> orderList;
    private Context context;
    private LayoutInflater layoutInflater;
    private WorkerDao workerDao;
    private UserDao userDao;

    public EvaluationListAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
//        this.userDao = userDao;
//        this.workerDao = workerDao;

    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        if (orderList != null)
        {
            return orderList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.evaluation_item,null);
            viewHolder = new ViewHolder();

            viewHolder.user_img = convertView.findViewById(R.id.user_img);
            viewHolder.user_name = convertView.findViewById(R.id.user_name);
            viewHolder.user_evaluation = convertView.findViewById(R.id.user_evaluation);
            viewHolder.user_rating = convertView.findViewById(R.id.user_rating);
            viewHolder.worker_evaluation = convertView.findViewById(R.id.worker_evaluation);
            viewHolder.worker_img = convertView.findViewById(R.id.worker_img);
            viewHolder.worker_name = convertView.findViewById(R.id.worker_name);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Bitmap bitmap1 = BitmapFactory.decodeFile(orderList.get(position).getUser().getImg());
        viewHolder.user_img.setImageBitmap(bitmap1);
        viewHolder.user_name.setText(orderList.get(position).getUser().getName());
        viewHolder.user_evaluation.setText(orderList.get(position).getUserEvaluation());
        viewHolder.user_rating.setRating(orderList.get(position).getStar());

        if (orderList.get(position).getWorkerEvaluation() != null){
            Bitmap bitmap2 = BitmapFactory.decodeFile(orderList.get(position).getWorker().getImg());
            viewHolder.worker_img.setImageBitmap(bitmap2);
            viewHolder.worker_name.setText(orderList.get(position).getWorker().getWorker_name());
            viewHolder.worker_evaluation.setText(orderList.get(position).getWorkerEvaluation());
        }

        return convertView;
    }

    private class ViewHolder{

        RatingBar user_rating;
        ImageView user_img,worker_img;
        TextView user_name,worker_name,user_evaluation,worker_evaluation;

    }
}
