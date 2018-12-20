package com.example.dell.c1ean.Fragment.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.dell.c1ean.R;

/**
 * Created by DELL on 2018/12/4.
 */

public class UserOrdersFragment extends Fragment implements View.OnClickListener{

    private AllOrderFragment allOrderFragment;
    private WaitingPayFragment waitingPayFragment;
    private WaitingEvaluationFragment waitingEvaluationFragment;
    private TextView all_order,wait_pay,wait_evaluate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_order_page,container,false);

        all_order = view.findViewById(R.id.all_order);
        wait_pay = view.findViewById(R.id.wait_pay);
        wait_evaluate = view.findViewById(R.id.wait_evaluate);

        all_order.setOnClickListener(this);
        wait_pay.setOnClickListener(this);
        wait_evaluate.setOnClickListener(this);
        //第一次初始化首页默认显示第一个fragment
        initFragment1();

        return view;
    }

    //显示第一个fragment
    private void initFragment1(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(allOrderFragment == null){
            allOrderFragment = new AllOrderFragment();
            transaction.add(R.id.order_page, allOrderFragment);
            all_order.setTextColor(getResources().getColor(R.color.colorOrange));
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(allOrderFragment);

        //提交事务
        transaction.commit();
    }

    //显示第二个fragment
    private void initFragment2(){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if(waitingPayFragment == null){
            waitingPayFragment = new WaitingPayFragment();
            transaction.add(R.id.order_page,waitingPayFragment);
        }
        hideFragment(transaction);
        transaction.show(waitingPayFragment);

        transaction.commit();
    }

    //显示第三个fragment
    private void initFragment3(){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if(waitingEvaluationFragment == null){
            waitingEvaluationFragment = new WaitingEvaluationFragment();
            transaction.add(R.id.order_page,waitingEvaluationFragment);
        }
        hideFragment(transaction);
        transaction.show(waitingEvaluationFragment);
        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(allOrderFragment != null){
            transaction.hide(allOrderFragment);
        }
        if(waitingPayFragment != null){
            transaction.hide(waitingPayFragment);
        }
        if(waitingEvaluationFragment != null){
            transaction.hide(waitingEvaluationFragment);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == all_order) {
            all_order.setTextColor(getResources().getColor(R.color.colorOrange));
            wait_pay.setTextColor(getResources().getColor(R.color.color_order_text));
            wait_evaluate.setTextColor(getResources().getColor(R.color.color_order_text));
            initFragment1();
        } else if (v == wait_pay) {
            all_order.setTextColor(getResources().getColor(R.color.color_order_text));
            wait_pay.setTextColor(getResources().getColor(R.color.colorOrange));
            wait_evaluate.setTextColor(getResources().getColor(R.color.color_order_text));
            initFragment2();
        } else if (v == wait_evaluate) {
            all_order.setTextColor(getResources().getColor(R.color.color_order_text));
            wait_pay.setTextColor(getResources().getColor(R.color.color_order_text));
            wait_evaluate.setTextColor(getResources().getColor(R.color.colorOrange));
            initFragment3();
        }

    }
}
