package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Eskii on 2018/11/28.
 * 所有用户的支出
 */

@Entity
public class UserOutaccount {

    @Id
    private Long user_id;   //用户id(可为用户/阿姨/公司)
    @NotNull
    private String time;    //支出时间
    @NotNull
    private Float money;    //金额
    @NotNull
    private String Payee;   //收款方名字

    @Generated(hash = 1424502097)
    public UserOutaccount(Long user_id, @NotNull String time, @NotNull Float money,
            @NotNull String Payee) {
        this.user_id = user_id;
        this.time = time;
        this.money = money;
        this.Payee = Payee;
    }

    @Generated(hash = 2035708419)
    public UserOutaccount() {
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

    public String getPayee() {
        return Payee;
    }

    public void setPayee(String payee) {
        Payee = payee;
    }
}
