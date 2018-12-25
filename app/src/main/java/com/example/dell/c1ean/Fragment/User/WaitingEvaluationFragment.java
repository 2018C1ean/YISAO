package com.example.dell.c1ean.Fragment.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Adapter.OrderListAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.UserInaccountDao;
import com.example.dell.c1ean.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/11.
 */

public class WaitingEvaluationFragment extends Fragment{

    private List<Order> orderList = new ArrayList<>();
    private CompanyActivityDao companyActivityDao;
    private CompanyDao companyDao;
    private OrderDao orderDao;
    private Long user_id;
    private TextView textView;
    private ListView listView;
    private OrderListAdapter orderListAdapter;
    private UserInaccountDao userInaccountDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_wait_evaluation_orders,container,false);

        listView = view.findViewById(R.id.listView);
        textView = view.findViewById(R.id.valuable);
        user_id = ((BaseApplication) getActivity().getApplication()).getUSER_ID();
        companyActivityDao = ((BaseApplication) getActivity().getApplication()).getCompanyActivityDao();
        companyDao = ((BaseApplication) getActivity().getApplication()).getCompanyDao();
        orderDao = ((BaseApplication) getActivity().getApplication()).getOrderDao();
        userInaccountDao = ((BaseApplication) getActivity().getApplication()).getUserInaccountDao();

        setData();
        initView();

        return view;
    }

    private void setData(){
        if (user_id  != null) {
            orderList = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.State.eq(3)).list();
        }
        }

    private void initView(){
        if (orderList.size() >= 1) {
            textView.setVisibility(View.INVISIBLE);
            orderListAdapter = new OrderListAdapter(getContext(), orderList, companyDao, companyActivityDao,orderDao,userInaccountDao);
            listView.setAdapter(orderListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(), orderList.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            textView.setVisibility(View.VISIBLE);

        }
    }
}
