package com.example.dell.c1ean.Activity.Company;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.R;
import com.gyf.barlibrary.ImmersionBar;
import com.wuhenzhizao.titlebar.statusbar.StatusBarUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 李雯晴 on 2018/12/7.
 */

public class ManageActivityActivity extends AppCompatActivity {

    private ImageView img1, img2, img3;
    private ImageView back;
    private TextView activity_type, price, star_time, end_time, title;
    private RadioGroup user_times;
    private EditText describe;
    private Button post, update, delete;
    private RadioButton single, nolimit;
    private LinearLayout manage_line;
    public static final int PHOTO_REQUEST_CAMERA = 11;// 拍照
    public static final int IMAGE_REQUEST_CODE = 21;
    public static final int CAMERA_CROP_PHOTO = 31;
    public static final int ALBUM_CROP_PHOTO = 41;
    public static final int PHOTO_REQUEST_CAMERA_2 = 12;// 拍照
    public static final int IMAGE_REQUEST_CODE_2 = 22;
    public static final int CAMERA_CROP_PHOTO_2 = 32;
    public static final int ALBUM_CROP_PHOTO_2 = 42;
    public static final int PHOTO_REQUEST_CAMERA_3 = 13;// 拍照
    public static final int IMAGE_REQUEST_CODE_3 = 23;
    public static final int CAMERA_CROP_PHOTO_3 = 33;
    public static final int ALBUM_CROP_PHOTO_3 = 43;
    private static Uri camera_img_uri; //拍完照照片的uri
    private File tempFile;  //使用相机拍照后保存的地址
    public Uri album_img_uri;   //相册中选取照片的uri
    private static int current_api_version;   //手机的api版本
    private Long company_id;
    private CompanyActivityDao companyActivityDao;
    private String img_file1, img_file2, img_file3;
    private String[] typeArray = {"专业保洁", "家电清洗", "家居养护", "洗护服务"};
    private String[] titleArray1 = {"门框窗框","木地板清洗保养","厨卫台表面擦拭","室内玻璃擦拭"};
    private String[] titleArray2 = {"油烟机清洁","空调清洁","电冰箱清洁","洗衣机清洁"};
    private String[] titleArray3 = {"家具除尘","沙发地毯除尘","家用电器表面清洁","卫浴设施保养"};
    private String[] titleArray4 = {"衣物干洗","鞋子清洁"};
    private Long activity_id;
    private TextView unit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.company_post_events);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);    //设置全屏
        ImmersionBar.with(this).init();
        company_id = ((BaseApplication) getApplication()).getUSER_ID();  //获取当前的用户id
        companyActivityDao = ((BaseApplication) getApplication()).getCompanyActivityDao();
        StatusBarUtils.setStatusBarColor(getWindow(), getResources().getColor(R.color.colorWhite), 1);

        activity_id = getIntent().getLongExtra("activity_item_id", 0);

        SystemApplication.getInstance().addActivity(this);
        initView();
    }

    private void initView() {

        title = findViewById(R.id.activity_title);
        back = findViewById(R.id.back);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        single = findViewById(R.id.single);
        nolimit = findViewById(R.id.nolimit);
        activity_type = findViewById(R.id.activity_type);
        price = findViewById(R.id.activity_price);
        star_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);
        user_times = findViewById(R.id.user_times);
        describe = findViewById(R.id.activity_describe);
        post = findViewById(R.id.post);
        manage_line = findViewById(R.id.manage_line);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        unit = findViewById(R.id.unit);

        if (activity_id != 0) {

            CompanyActivity companyActivity = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique();
            title.setText(companyActivity.getTitle());
            activity_type.setText(companyActivity.getType());
            price.setText(companyActivity.getPrice() + "");
            star_time.setText(companyActivity.getStart_time());
            end_time.setText(companyActivity.getEnd_time());
            if (companyActivity.getUses() == "单次") {
                single.setChecked(true);
            } else {
                nolimit.setChecked(true);
            }
            describe.setText(companyActivity.getActivity_decribes());
            manage_line.setVisibility(View.VISIBLE);
            post.setVisibility(View.INVISIBLE);

            Bitmap bitmap = BitmapFactory.decodeFile(companyActivity.getImg1());
            img1.setImageBitmap(bitmap);
            img_file1 = companyActivity.getImg1();
            if (companyActivity.getImg2() != null) {
                Bitmap bitmap1 = BitmapFactory.decodeFile(companyActivity.getImg2());
                img2.setImageBitmap(bitmap1);
                img_file2 = companyActivity.getImg2();
                img2.setVisibility(View.VISIBLE);
                if (companyActivity.getImg3() != null) {
                    Bitmap bitmap2 = BitmapFactory.decodeFile(companyActivity.getImg3());
                    img3.setImageBitmap(bitmap2);
                    img_file3 = companyActivity.getImg3();
                    img3.setVisibility(View.VISIBLE);
                }else {
                    img3.setVisibility(View.VISIBLE);
                }
            }else {
                img2.setVisibility(View.VISIBLE);
            }


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ManageActivityActivity.this);
                    dialog.setMessage("您确定要删除此活动吗？");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CompanyActivity companyActivity1 = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique();
                            companyActivityDao.delete(companyActivity1);
                            dialog.cancel();
                            Toast.makeText(ManageActivityActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ManageActivityActivity.this, CompanyMainPageActivity.class);
                            startActivity(intent);

                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ManageActivityActivity.this);
                    dialog.setMessage("您确定要修改此活动吗？");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String type = activity_type.getText().toString();
                            String activity_price = price.getText().toString();
                            String start = star_time.getText().toString();
                            String end = end_time.getText().toString();
                            String activity_describe = describe.getText().toString();
                            String activity_title = title.getText().toString();
                            String activity_unit = unit.getText().toString();

                            if (type.isEmpty()) {
                                showText("活动的类型不能为空！");
                            } else {
                                if (activity_title.isEmpty()) {
                                    showText("活动标题不能为空！");
                                } else {
                                    if (!parseToFloat(activity_price)) {
                                        showText("价格的格式错误！");
                                    } else {
                                        if (isGetRadioBtn(user_times.getCheckedRadioButtonId())) {
                                            String times = ((RadioButton) findViewById(user_times.getCheckedRadioButtonId())).getText().toString();
                                            Float price = Float.parseFloat(activity_price); //价格
                                            if (activity_describe.isEmpty()) {
                                                showText("活动描述不能为空！");
                                            } else {
                                                if (img_file1 == null) {
                                                    if (img_file2 == null) {
                                                        if (img_file3 == null) {
                                                            Toast.makeText(ManageActivityActivity.this, "活动照片不能为空", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            img_file1 = img_file3;
                                                            img_file3 = null;
                                                            //创建一个companyActivity实体
                                                            CompanyActivity companyActivity = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique();
                                                            //更新
                                                            companyActivity.setTitle(activity_title);
                                                            companyActivity.setType(type);
                                                            companyActivity.setImg1(img_file1);
                                                            companyActivity.setImg2(img_file2);
                                                            companyActivity.setImg3(img_file3);
                                                            companyActivity.setStart_time(start);
                                                            companyActivity.setEnd_time(end);
                                                            companyActivity.setPrice(price);
                                                            companyActivity.setUses(times);
                                                            companyActivity.setUnit(activity_unit);
                                                            companyActivity.setActivity_decribes(activity_describe);
                                                            companyActivityDao.update(companyActivity);
                                                            Toast.makeText(ManageActivityActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                                            //操作成功后跳转主页面
                                                            Intent intent = new Intent(ManageActivityActivity.this, CompanyMainPageActivity.class);
                                                            startActivity(intent);
//                                                    setResult(10);
//                                                    finish();
                                                        }
                                                    } else {
                                                        img_file1 = img_file2;
                                                        img_file2 = null;
                                                        //创建一个companyActivity实体
                                                        CompanyActivity companyActivity = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique();
                                                        //更新
                                                        companyActivity.setTitle(activity_title);
                                                        companyActivity.setType(type);
                                                        companyActivity.setImg1(img_file1);
                                                        companyActivity.setImg2(img_file2);
                                                        companyActivity.setImg3(img_file3);
                                                        companyActivity.setStart_time(start);
                                                        companyActivity.setEnd_time(end);
                                                        companyActivity.setPrice(price);
                                                        companyActivity.setUses(times);
                                                        companyActivity.setUnit(activity_unit);
                                                        companyActivity.setActivity_decribes(activity_describe);
                                                        companyActivityDao.update(companyActivity);
                                                        Toast.makeText(ManageActivityActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                                        //操作成功后跳转主页面
                                                        Intent intent = new Intent(ManageActivityActivity.this, CompanyMainPageActivity.class);
                                                        startActivity(intent);
//                                                setResult(10);
//                                                finish();
                                                    }

                                                } else {
                                                    //创建一个companyActivity实体
                                                    CompanyActivity companyActivity = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique();
                                                    //更新
                                                    companyActivity.setTitle(activity_title);
                                                    companyActivity.setType(type);
                                                    companyActivity.setImg1(img_file1);
                                                    companyActivity.setImg2(img_file2);
                                                    companyActivity.setImg3(img_file3);
                                                    companyActivity.setStart_time(start);
                                                    companyActivity.setEnd_time(end);
                                                    companyActivity.setPrice(price);
                                                    companyActivity.setUses(times);
                                                    companyActivity.setUnit(activity_unit);
                                                    companyActivity.setActivity_decribes(activity_describe);
                                                    companyActivityDao.update(companyActivity);
                                                    Toast.makeText(ManageActivityActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                                    //操作成功后跳转主页面
                                                    Intent intent = new Intent(ManageActivityActivity.this, CompanyMainPageActivity.class);
                                                    startActivity(intent);
                                                }

                                            }
                                        } else {
                                            showText("请选择用户次数！");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });
        } else {
            post.setVisibility(View.VISIBLE);
            //发布活动
            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = activity_type.getText().toString();
                    String activity_price = price.getText().toString();
                    String start = star_time.getText().toString();
                    String end = end_time.getText().toString();
                    String activity_describe = describe.getText().toString();
                    String activity_title = title.getText().toString();
                    String activity_unit = unit.getText().toString();

                    if (type.isEmpty()) {
                        showText("活动的类型不能为空！");
                    } else {
                        if (activity_title.isEmpty()) {
                            showText("活动标题不能为空！");
                        } else {
                            if (!parseToFloat(activity_price)) {
                                showText("价格的格式错误！");
                            } else {
                                if (isGetRadioBtn(user_times.getCheckedRadioButtonId())) {
                                    String times = ((RadioButton) findViewById(user_times.getCheckedRadioButtonId())).getText().toString();
                                    Float price = Float.parseFloat(activity_price); //价格
                                    if (activity_describe.isEmpty()) {
                                        showText("活动描述不能为空！");
                                    } else {
                                        if (img_file1 == null) {
                                            if (img_file2 == null) {
                                                if (img_file3 == null) {
                                                    Toast.makeText(ManageActivityActivity.this, "活动照片不能为空", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    img_file1 = img_file3;
                                                    img_file3 = null;
                                                    //创建一个companyActivity实体
                                                    CompanyActivity companyActivity = new CompanyActivity(null, activity_title, type, img_file1, img_file2, img_file3, activity_unit, start, end, price, company_id, times, activity_describe);
                                                    //向数据库插入实体
                                                    companyActivityDao.insert(companyActivity);
                                                    //操作成功后跳转主页面
                                                    Toast.makeText(ManageActivityActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ManageActivityActivity.this, CompanyMainPageActivity.class);
                                                    startActivity(intent);
//                                                    setResult(10);
//                                                    finish();
                                                }
                                            } else {
                                                img_file1 = img_file2;
                                                img_file2 = null;
                                                //创建一个companyActivity实体
                                                CompanyActivity companyActivity = new CompanyActivity(null, activity_title, type, img_file1, img_file2, img_file3, activity_unit, start, end, price, company_id, times, activity_describe);
                                                //向数据库插入实体
                                                companyActivityDao.insert(companyActivity);
                                                //操作成功后跳转主页面
                                                Toast.makeText(ManageActivityActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ManageActivityActivity.this, CompanyMainPageActivity.class);
                                                startActivity(intent);
//                                                setResult(10);
//                                                finish();
                                            }

                                        } else {
                                            //创建一个companyActivity实体
                                            CompanyActivity companyActivity = new CompanyActivity(null, activity_title, type, img_file1, img_file2, img_file3, activity_unit, start, end, price, company_id, times, activity_describe);
                                            //向数据库插入实体
                                            companyActivityDao.insert(companyActivity);
                                            //操作成功后跳转主页面
                                            Toast.makeText(ManageActivityActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ManageActivityActivity.this, CompanyMainPageActivity.class);
                                            startActivity(intent);
                                        }

                                    }
                                } else {
                                    showText("请选择用户次数！");
                                }
                            }
                        }
                    }
                }
            });
        }
        //点击进行图片设置或更换照片
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(1);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(2);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(3);
            }
        });
        activity_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("");
                AlertDialog.Builder b = new AlertDialog.Builder(ManageActivityActivity.this);
                b.setTitle("活动类型")
                        .setSingleChoiceItems(typeArray,  //装载数组信息
                                //默认选中第一项
                                0,
                                //为列表添加监听事件
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                activity_type.setText(typeArray[0]);
                                                unit.setText("\\小时");
                                                break;
                                            case 1:
                                                activity_type.setText(typeArray[1]);
                                                unit.setText("\\台");
                                                break;
                                            case 2:
                                                activity_type.setText(typeArray[2]);
                                                unit.setText("\\小时");
                                                break;
                                            case 3:
                                                activity_type.setText(typeArray[3]);
                                                unit.setText("\\件");
                                                break;
                                        }
                                        price.setText("");
                                        describe.setText("");
                                        dialog.cancel();  //用户选择后，关闭对话框
                                    }
                                })
                        .create()
                        .show();
            }
        });

        star_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog datePickDialog = new DatePickDialog(ManageActivityActivity.this);
                //设置上下年分限制
                datePickDialog.setYearLimt(5);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                datePickDialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                datePickDialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        //设置时间格式
                        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        star_time.setText(timeStampFormat.format(date));
                    }
                });

                datePickDialog.show();
            }
        });

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog datePickDialog = new DatePickDialog(ManageActivityActivity.this);
                //设置上下年分限制
                datePickDialog.setYearLimt(5);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                datePickDialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                datePickDialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        //设置时间格式
                        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        end_time.setText(timeStampFormat.format(date));
                    }
                });

                datePickDialog.show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(ManageActivityActivity.this);
                if (activity_type.getText().toString().equals("专业保洁")) {
                    b.setSingleChoiceItems(titleArray1,  //装载数组信息
                            //默认选中第一项
                            0,
                            //为列表添加监听事件
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            title.setText(titleArray1[0]);
                                            break;
                                        case 1:
                                            title.setText(titleArray1[1]);
                                            break;
                                        case 2:
                                            title.setText(titleArray1[2]);
                                            break;
                                        case 3:
                                            title.setText(titleArray1[3]);
                                            break;
                                        case 4:
                                            title.setText(titleArray1[4]);
                                            break;
                                    }
                                    dialog.cancel();  //用户选择后，关闭对话框
                                }
                            });
                }else if (activity_type.getText().toString().equals("家电清洗")){
                    b.setSingleChoiceItems(titleArray2,  //装载数组信息
                            //默认选中第一项
                            0,
                            //为列表添加监听事件
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            title.setText(titleArray2[0]);
                                            break;
                                        case 1:
                                            title.setText(titleArray2[1]);
                                            break;
                                        case 2:
                                            title.setText(titleArray2[2]);
                                            break;
                                        case 3:
                                            title.setText(titleArray2[3]);
                                            break;
                                    }
                                    dialog.cancel();  //用户选择后，关闭对话框
                                }
                            });

                }else if(activity_type.getText().toString().equals("家居养护")){
                    b.setSingleChoiceItems(titleArray3,  //装载数组信息
                            //默认选中第一项
                            0,
                            //为列表添加监听事件
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            title.setText(titleArray3[0]);
                                            break;
                                        case 1:
                                            title.setText(titleArray3[1]);
                                            break;
                                        case 2:
                                            title.setText(titleArray3[2]);
                                            break;
                                        case 3:
                                            title.setText(titleArray3[3]);
                                            break;
                                    }
                                    dialog.cancel();  //用户选择后，关闭对话框
                                }
                            });
                }else {
                    b.setSingleChoiceItems(titleArray4,  //装载数组信息
                            //默认选中第一项
                            0,
                            //为列表添加监听事件
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            title.setText(titleArray4[0]);
                                            break;
                                        case 1:
                                            title.setText(titleArray4[1]);
                                            break;
                                    }
                                    dialog.cancel();  //用户选择后，关闭对话框
                                }
                            });
                }
                b.create();
                b.show();
            }
        });

    }

    private void setDialog(final int i) {

        final Dialog dialog = new Dialog(this, R.style.BottomDialog);  //设置对话框的style为下弹下单（自定义的）
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.add_image_dialog, null); //加载对话框布局
        dialog.setContentView(layout);  //设置布局

        //跳转相机拍照
        layout.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取系統sdk版本
                current_api_version = android.os.Build.VERSION.SDK_INT;

                // 激活相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    //设置时间格式
                    SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                    //获取当前系统时间
                    String filename = timeStampFormat.format(new Date());
                    //设置照片的文件名为 时间.jpg
                    tempFile = new File(Environment.getExternalStorageDirectory() + File.separator + "DCIM", filename + ".jpg");

                    if (current_api_version < 24) {   // 如果系统的sdk从文件中创建uri

                        camera_img_uri = Uri.fromFile(tempFile);

                        //指定输出地址
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
                        Toast.makeText(ManageActivityActivity.this, "保存在" + camera_img_uri, Toast.LENGTH_LONG).show();
                    } else {

                        //Android 7.0之后调用相机的方式不允许以file://的方式调用，需要以共享文件的方式
                        ContentValues contentValues = new ContentValues(1);

                        /**
                         * 检查是否有存储权限，以免崩溃
                         * ContextCompat 一个检查app是否有某种权限的工具
                         * ContentResolver就是按照一定规则访问内容提供者（ContentProvider）的数据
                         */
                        if (ContextCompat.checkSelfPermission(ManageActivityActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {

                            Toast.makeText(ManageActivityActivity.this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                            return; //如果没有开启存储权限，退出此方法

                        }

                        //MediaStore.Images.Media.EXTERNAL_CONTENT_URI 设备照片存储的uri地址
                        camera_img_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                        //指定拍照的输出地址
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
                    }

                    if (i == 1) {
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
                    } else if (i == 2) {
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA_2);
                    } else {
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA_3);
                    }
                } else {
                    Toast.makeText(ManageActivityActivity.this, "存储卡不可用", Toast.LENGTH_LONG).show();
                }
            }
        });

        //跳转系统相册选取照片
        layout.findViewById(R.id.album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用意图直接调用手机相册
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (i == 1) {

                    startActivityForResult(intent, IMAGE_REQUEST_CODE);
                } else if (i == 2) {
                    startActivityForResult(intent, IMAGE_REQUEST_CODE_2);

                } else {
                    startActivityForResult(intent, IMAGE_REQUEST_CODE_3);

                }
            }
        });

        //取消
        layout.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = getResources().getDisplayMetrics().widthPixels; // 宽度
        layout.measure(0, 0);
        lp.height = layout.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        window.setAttributes(lp);
        dialog.show();  //显示对话框
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PHOTO_REQUEST_CAMERA:  //拍照

                if (resultCode == RESULT_OK) {  //如果拍好了照
                    Intent intent = getCameraIntent();
                    startActivityForResult(intent, CAMERA_CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case PHOTO_REQUEST_CAMERA_2:
                if (resultCode == RESULT_OK) {  //如果拍好了照
                    Intent intent = getCameraIntent();
                    startActivityForResult(intent, CAMERA_CROP_PHOTO_2); // 启动裁剪程序
                }
                break;
            case PHOTO_REQUEST_CAMERA_3:
                if (resultCode == RESULT_OK) {  //如果拍好了照
                    Intent intent = getCameraIntent();
                    startActivityForResult(intent, CAMERA_CROP_PHOTO_3); // 启动裁剪程序
                }
                break;
            case CAMERA_CROP_PHOTO: //拍完照后的剪裁
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(camera_img_uri));
                        img_file1 = savePicture(bitmap);
                        img1.setImageBitmap(bitmap);
                        img2.setVisibility(View.VISIBLE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CAMERA_CROP_PHOTO_2:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(camera_img_uri));
                        img_file2 = savePicture(bitmap);
                        img2.setImageBitmap(bitmap);
                        img3.setVisibility(View.VISIBLE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CAMERA_CROP_PHOTO_3:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(camera_img_uri));
                        img_file3 = savePicture(bitmap);
                        img3.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case IMAGE_REQUEST_CODE:    //从相册选取照片
                if (resultCode == RESULT_OK) {
                    Intent intent = getImageIntent();
                    intent.setDataAndType(data.getData(), "image/*"); //设置路径和文件类型为图片
                    album_img_uri = data.getData();
                    startActivityForResult(intent, ALBUM_CROP_PHOTO); // 启动裁剪程序
                }
                break;

            case IMAGE_REQUEST_CODE_2:
                if (resultCode == RESULT_OK) {
                    Intent intent = getImageIntent();
                    intent.setDataAndType(data.getData(), "image/*"); //设置路径和文件类型为图片
                    album_img_uri = data.getData();
                    startActivityForResult(intent, ALBUM_CROP_PHOTO_2); // 启动裁剪程序
                }
                break;
            case IMAGE_REQUEST_CODE_3:
                if (resultCode == RESULT_OK) {
                    Intent intent = getImageIntent();
                    intent.setDataAndType(data.getData(), "image/*"); //设置路径和文件类型为图片
                    album_img_uri = data.getData();
                    startActivityForResult(intent, ALBUM_CROP_PHOTO_3); // 启动裁剪程序
                }
                break;
            case ALBUM_CROP_PHOTO:  //从照片剪裁出来的
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    img_file1 = savePicture(bitmap);
                    img1.setImageBitmap(bitmap);
                    img2.setVisibility(View.VISIBLE);
                }
                break;
            case ALBUM_CROP_PHOTO_2:
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    img_file2 = savePicture(bitmap);
                    img2.setImageBitmap(bitmap);
                    img3.setVisibility(View.VISIBLE);
                }
                break;
            case ALBUM_CROP_PHOTO_3:
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    img_file3 = savePicture(bitmap);
                    img3.setImageBitmap(bitmap);
                }
                break;
        }
    }

    /**
     * 判断sdcard是否被挂载
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static String savePicture(Bitmap bitmap) {
        //设置时间格式
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        //获取当前系统时间
        String filename = timeStampFormat.format(new Date());
        String fileName = Environment.getExternalStorageDirectory().toString()
                + File.separator
                + "DCIM"
                + File.separator
                + filename + ".jpg";
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();//创建文件夹
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);//向缓冲区压缩图片
            bos.flush();
            bos.close();
            return fileName;
        } catch (Exception e) {
            return null;
        }
    }

    //提示
    private void showText(String text) {
        Toast.makeText(ManageActivityActivity.this, text, Toast.LENGTH_LONG).show();
    }

    private boolean parseToFloat(String string) {
        try {
            float n = Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Intent getCameraIntent() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(camera_img_uri, "image/*"); //设置路径和文件类型为图片
        intent.putExtra("crop", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
        return intent;
    }

    public static Intent getImageIntent() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.putExtra("crop", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.putExtra("return-data", true);
        return intent;
    }

    private boolean isGetRadioBtn(int btn_id) {
        RadioButton radioButton = findViewById(btn_id);
        if (radioButton != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

}