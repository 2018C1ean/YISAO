package com.example.dell.c1ean.Fragment.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.c1ean.Adapter.OrderListAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/11.
 */

public class AllOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListView listView;
    private List<Order> orderList = new ArrayList<>();
    private CompanyActivityDao companyActivityDao;
    private CompanyDao companyDao;
    private OrderDao orderDao;
    private Long user_id;
    private TextView textView;
    private OrderListAdapter orderListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_all_orders, container, false);

//        recyclerView = view.findViewById(R.id.recyclerListView);
        textView = view.findViewById(R.id.valuable);
        listView = view.findViewById(R.id.order_list);

        user_id = ((BaseApplication) getActivity().getApplication()).getUSER_ID();
        companyActivityDao = ((BaseApplication) getActivity().getApplication()).getCompanyActivityDao();
        companyDao = ((BaseApplication) getActivity().getApplication()).getCompanyDao();
        orderDao = ((BaseApplication) getActivity().getApplication()).getOrderDao();

        setData();

        if (orderList.size() > 0) {
            textView.setVisibility(View.INVISIBLE);
//            listViewAdapter = new RecyclerViewListViewAdapter(getContext(), orderList, companyDao, companyActivityDao);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setAdapter(listViewAdapter);
//            //ListView效果的 LinearLayoutManager
//            RecyclerView.LayoutManager mgr = new RecyclerView.LayoutManager(getActivity());
////VERTICAL纵向，类似ListView，HORIZONTAL<span style="font-family: Arial, Helvetica, sans-serif;">横向，类似Gallery</span>
//            mgr.setOrientation(LinearLayoutManager.VERTICAL);
//            recyclerView.setLayoutManager(mgr);
            orderListAdapter = new OrderListAdapter(getContext(),orderList,companyDao,companyActivityDao);
            listView.setAdapter(orderListAdapter);
        } else {
            textView.setVisibility(View.VISIBLE);
        }

        return view;
    }


    private void setData() {

        if (user_id != null) {
            //加载当前用户的所有订单（应使用分页，待优化）
            orderList = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id)).list();
        }
    }

}
