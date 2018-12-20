package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by 李雯晴 on 2018/11/28.
 * 用户
 */

@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;    //用户id
    private String name;    //用户名
    private String img;     //用户头像
    @NotNull
    private String password;    //用户密码
    @NotNull
    private String tel; //用户电话
    private String location1;   //地址1
    private String location2;   //地址2
    private String location3;   //地址3
    private String background_img;
    public User() {
    }

    @Generated(hash = 1387168852)
    public User(Long id, String name, String img, @NotNull String password,
            @NotNull String tel, String location1, String location2,
            String location3, String background_img) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.password = password;
        this.tel = tel;
        this.location1 = location1;
        this.location2 = location2;
        this.location3 = location3;
        this.background_img = background_img;
    }

    public String getBackground_img() {
        return background_img;
    }

    public void setBackground_img(String background_img) {
        this.background_img = background_img;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getLocation3() {
        return location3;
    }

    public void setLocation3(String location3) {
        this.location3 = location3;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return getName()+"\n"+getImg()+"\n"+getBackground_img();
    }
}
