package com.example.dell.c1ean.Fragment.Company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;
import com.example.dell.c1ean.Util.GlideImageLoader;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

/**
 * Created by DELL on 2018/12/5.
 */

public class CompanyHomePageFragment extends Fragment{

    private Banner banner;
    private TextView title;
    private ScrollView myScrollView;
    private Long user_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.company_homepage,container,false);

        user_id = ((BaseApplication)getActivity().getApplication()).getUSER_ID();
        banner = view.findViewById(R.id.banner);
        title = view.findViewById(R.id.title_bar);
        myScrollView = view.findViewById(R.id.scrollView);

        return view;
    }
}
