package com.example.dell.c1ean.Fragment.User;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Activity.LoginActivity;
import com.example.dell.c1ean.Activity.RegisterTypeActivity;
import com.example.dell.c1ean.Activity.User.ShowLocationActivity;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.example.dell.c1ean.Activity.Company.ManageActivityActivity.hasSdcard;
import static com.example.dell.c1ean.Activity.Company.ManageActivityActivity.savePicture;

/**
 * Created by DELL on 2018/12/4.
 */

public class UserPageFragment extends Fragment {

    public static final int PHOTO_REQUEST_CAMERA = 11;// 拍照
    public static final int IMAGE_REQUEST_CODE = 21;
    public static final int CAMERA_CROP_PHOTO = 31;
    public static final int ALBUM_CROP_PHOTO = 41;
    public static final int PHOTO_REQUEST_CAMERA_2 = 12;// 拍照
    public static final int IMAGE_REQUEST_CODE_2 = 22;
    public static final int CAMERA_CROP_PHOTO_2 = 32;
    public static final int ALBUM_CROP_PHOTO_2 = 42;
    private static int current_api_version;   //手机的api版本
    public Uri album_img_uri;   //相册中选取照片的uri
    private Uri camera_img_uri; //拍完照照片的uri
    private File tempFile;  //使用相机拍照后保存的地址
    private Long user_id;
    private LinearLayout login_register;
    private RelativeLayout login, register;
    private String img_file1, img_file2;    //照片地址
    private Button change_account, out_account;
    private ImageView user_img, user_back;
    private TextView user_name;
    private UserDao userDao;
    private User user;
    private RelativeLayout user_location, account_manage, feedback, help, share, about, check_for_update, give_a_paise, contact_us;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_page, container, false);

        user_id = ((BaseApplication) getActivity().getApplication()).getUSER_ID();

        userDao = ((BaseApplication) getActivity().getApplication()).getUserDao();
        if (user_id != null) {
            user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
        }
        login_register = view.findViewById(R.id.login_register);
        login = view.findViewById(R.id.login);
        register = view.findViewById(R.id.register);
        user_back = view.findViewById(R.id.user_back);
        change_account = view.findViewById(R.id.change_account);
        out_account = view.findViewById(R.id.out_account);
        user_img = view.findViewById(R.id.user_img);
        user_name = view.findViewById(R.id.user_name);
        user_location = view.findViewById(R.id.location_line);
        account_manage = view.findViewById(R.id.account_manage);
        feedback = view.findViewById(R.id.feedback);
        help = view.findViewById(R.id.help);
        share = view.findViewById(R.id.share);
        about = view.findViewById(R.id.about_c1ean);
        check_for_update = view.findViewById(R.id.check_for_update);
        give_a_paise = view.findViewById(R.id.give_a_praise);
        contact_us = view.findViewById(R.id.contact_us);

        setView();
        return view;
    }

    private void setView() {
        /**
         * 根据账号判定个人主页显示
         */
        //如果获取到的用户id不为空
        if (user_id != null) {

            user_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDialog(2);   //2表示是用户头像设置

                }
            });

            user_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDialog(1);   //1表示是用户背景图片设置
                }
            });

            user_name.setText(user.getName());  //设置用户名
            //设置用户的背景图
            if (user.getBackground_img() != null) {
                Bitmap bitmap1 = BitmapFactory.decodeFile(user.getBackground_img());
                user_back.setImageBitmap(bitmap1);
            }
            //设置用户的头像
            if (user.getImg() != null) {
                Bitmap bitmap2 = BitmapFactory.decodeFile(user.getImg());
                user_img.setImageBitmap(bitmap2);
            }

            user_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ShowLocationActivity.class);
                    startActivity(intent);
                }
            });

        } else {
            login_register.setVisibility(View.VISIBLE);
            change_account.setVisibility(View.INVISIBLE);
            out_account.setVisibility(View.INVISIBLE);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RegisterTypeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void setDialog(final int i) {

        final Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);  //设置对话框的style为下弹下单（自定义的）
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.add_image_dialog, null); //加载对话框布局
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
                        Toast.makeText(getActivity(), "保存在" + camera_img_uri, Toast.LENGTH_LONG).show();
                    } else {

                        //Android 7.0之后调用相机的方式不允许以file://的方式调用，需要以共享文件的方式
                        ContentValues contentValues = new ContentValues(1);

                        /**
                         * 检查是否有存储权限，以免崩溃
                         * ContextCompat 一个检查app是否有某种权限的工具
                         * ContentResolver就是按照一定规则访问内容提供者（ContentProvider）的数据
                         */
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {

                            Toast.makeText(getActivity(), "请开启存储权限", Toast.LENGTH_SHORT).show();
                            return; //如果没有开启存储权限，退出此方法

                        }

                        //MediaStore.Images.Media.EXTERNAL_CONTENT_URI 设备照片存储的uri地址
                        camera_img_uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                        //指定拍照的输出地址
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
                    }

                    if (i == 1) {
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
                    } else {
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA_2);
                    }
                } else {
                    Toast.makeText(getActivity(), "存储卡不可用", Toast.LENGTH_LONG).show();
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
                } else {
                    startActivityForResult(intent, IMAGE_REQUEST_CODE_2);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            //拍照
            case PHOTO_REQUEST_CAMERA:

                if (resultCode == RESULT_OK) {  //如果拍好了照
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(camera_img_uri, "image/*"); //设置路径和文件类型为图片
                    intent.putExtra("crop", true);
                    // aspectX aspectY 是宽高的比例
                    intent.putExtra("aspectX", 16);
                    intent.putExtra("aspectY", 9);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
                    startActivityForResult(intent, CAMERA_CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case PHOTO_REQUEST_CAMERA_2:
                if (resultCode == RESULT_OK) {  //如果拍好了照
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(camera_img_uri, "image/*"); //设置路径和文件类型为图片
                    intent.putExtra("crop", true);
                    // aspectX aspectY 是宽高的比例
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
                    startActivityForResult(intent, CAMERA_CROP_PHOTO_2); // 启动裁剪程序
                }
                break;

            //拍完照后的剪裁
            case CAMERA_CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(camera_img_uri));
                        img_file1 = savePicture(bitmap);
                        user_back.setImageBitmap(bitmap);
                        User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
                        user.setBackground_img(img_file1);
                        userDao.update(user);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CAMERA_CROP_PHOTO_2:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(camera_img_uri));
                        img_file2 = savePicture(bitmap);
                        user_img.setImageBitmap(bitmap);
                        //存入头像
                        User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
                        user.setImg(img_file2);
                        userDao.update(user);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            //从相册选取照片
            case IMAGE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.putExtra("crop", true);
                    // aspectX aspectY 是宽高的比例
                    intent.putExtra("aspectX", 4);
                    intent.putExtra("aspectY", 3);
                    intent.putExtra("return-data", true);
                    intent.setDataAndType(data.getData(), "image/*"); //设置路径和文件类型为图片
                    album_img_uri = data.getData();
                    startActivityForResult(intent, ALBUM_CROP_PHOTO); // 启动裁剪程序
                }
                break;

            case IMAGE_REQUEST_CODE_2:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.putExtra("crop", true);
                    // aspectX aspectY 是宽高的比例
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("return-data", true);
                    intent.setDataAndType(data.getData(), "image/*"); //设置路径和文件类型为图片
                    album_img_uri = data.getData();
                    startActivityForResult(intent, ALBUM_CROP_PHOTO_2); // 启动裁剪程序
                }
                break;
            case ALBUM_CROP_PHOTO:  //从照片剪裁出来的
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    img_file1 = savePicture(bitmap);
                    user_back.setImageBitmap(bitmap);
                    User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
                    user.setBackground_img(img_file1);
                    userDao.update(user);
                }
                break;
            case ALBUM_CROP_PHOTO_2:
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    img_file2 = savePicture(bitmap);
                    user_img.setImageBitmap(bitmap);
                    //存入头像
                    User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
                    user.setImg(img_file2);
                    userDao.update(user);
                }
                break;
        }
    }

}
