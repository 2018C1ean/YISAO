package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Eskii on 2018/11/28.
 * 所有用户的钱包收入
 */

@Entity
public class UserInaccount {

    @Id
    private Long user_id;   //用户id(可为用户/阿姨/公司)
    @NotNull
    private String time;    //充钱时间
    @NotNull
    private Float money;    //金额
    @NotNull
    private String Payer;   //支付方名字

    @Generated(hash = 573035434)
    public UserInaccount(Long user_id, @NotNull String time, @NotNull Float money,
            @NotNull String Payer) {
        this.user_id = user_id;
        this.time = time;
        this.money = money;
        this.Payer = Payer;
    }

    @Generated(hash = 1042195721)
    public UserInaccount() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getPayer() {
        return Payer;
    }

    public void setPayer(String payer) {
        Payer = payer;
    }
}
