package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
/**
 * Created by Eskii on 2018/11/28.
 * 公司
 */

@Entity
public class Company {

    @Id(autoincrement = true)
    private Long company_id;    //公司id
    private String img;     //公司照片
    @NotNull
    private String company_name;    //公司名字
    @NotNull
    private String company_code;    //公司代码
    @NotNull
    private String company_location;    //公司地址
    @NotNull
    private String company_tel; //公司电话
    private String password;    //公司密码
    private String introduction;
    public Company() {
    }

    @Generated(hash = 45315274)
    public Company(Long company_id, String img, @NotNull String company_name,
            @NotNull String company_code, @NotNull String company_location,
            @NotNull String company_tel, String password, String introduction) {
        this.company_id = company_id;
        this.img = img;
        this.company_name = company_name;
        this.company_code = company_code;
        this.company_location = company_location;
        this.company_tel = company_tel;
        this.password = password;
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_location() {
        return company_location;
    }

    public void setCompany_location(String company_location) {
        this.company_location = company_location;
    }

    public String getCompany_tel() {
        return company_tel;
    }

    public void setCompany_tel(String company_tel) {
        this.company_tel = company_tel;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
