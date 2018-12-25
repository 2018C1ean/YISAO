package com.example.dell.c1ean.Activity.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Activity.ActivityDetailPageActivity;
import com.example.dell.c1ean.Activity.LoginActivity;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;

/**
 * Created by 李雯晴 on 2018/12/21.
 * 用户评价订单界面
 */

public class UserEvaluationActivity extends AppCompatActivity {

    private Button post_evaluation;
    private TextView company_name, order_activity_title;
    private ImageView order_activity_img, back;
    private RatingBar give_stars;
    private EditText evaluation;
    private Long order_id;
    private OrderDao orderDao;
    private CompanyDao companyDao;
    private CompanyActivityDao companyActivityDao;
    private Order order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_evaluation_order);
        order_id = getIntent().getLongExtra("order_id", 0);
        SystemApplication.getInstance().addActivity(this);

        order = orderDao.queryBuilder().where(OrderDao.Properties.Id.eq(order_id)).unique();

        companyActivityDao = ((BaseApplication) getApplication()).getCompanyActivityDao();
        orderDao = ((BaseApplication) getApplication()).getOrderDao();
        companyDao = ((BaseApplication) getApplication()).getCompanyDao();

        back = findViewById(R.id.back);
        company_name = findViewById(R.id.company_name);
        order_activity_title = findViewById(R.id.order_activity_title);
        order_activity_img = findViewById(R.id.order_activity_img);
        give_stars = findViewById(R.id.evaluation_stars);
        evaluation = findViewById(R.id.evaluation);
        post_evaluation = findViewById(R.id.post_evaluation);

        setView();
    }

    private void setView() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Company company = companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(order.getCompany_id())).unique();

        company_name.setText(company.getCompany_name());

        CompanyActivity companyActivity = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(order.getActivity_id())).unique();
        order_activity_title.setText(companyActivity.getTitle());

        Bitmap bitmap = BitmapFactory.decodeFile(companyActivity.getImg1());
        order_activity_img.setBackground(new BitmapDrawable(bitmap));

        post_evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order_evaluation = evaluation.getText().toString();
                int stars = give_stars.getNumStars();

                if (order_evaluation.isEmpty()) {
                    Toast.makeText(UserEvaluationActivity.this, "您还没有填写评价哦~", Toast.LENGTH_SHORT).show();
                } else {
                    if (stars == 0) {
                        Toast.makeText(UserEvaluationActivity.this, "您还没有给本次服务打分哦~", Toast.LENGTH_SHORT).show();
                    } else {
                        order.setState(stars);
                        order.setUserEvaluation(order_evaluation);
                        orderDao.update(order);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(UserEvaluationActivity.this);
                        dialog.setMessage("感谢您的评价，您的评价将反馈给公司，以提升服务的质量");
                        dialog.setPositiveButton("返回主页", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(UserEvaluationActivity.this, UserMainPageActivity.class);
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                    }
                }
            }
        });

    }


}
