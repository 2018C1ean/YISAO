package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 李雯晴 on 2018/11/28.
 * 所有用户的支出
 */

@Entity
public class UserOutaccount {

    @Id
    private Long id;
    private Long user_id;   //用户id
    private String payer;   //使用的支付方式（钱包/支付宝）
    @NotNull
    private String time;    //支出时间
    private String money;    //金额
    @NotNull
    private String Payee;   //收款方名字

    @Generated(hash = 1652562125)
    public UserOutaccount(Long id, Long user_id, String payer,
            @NotNull String time, String money, @NotNull String Payee) {
        this.id = id;
        this.user_id = user_id;
        this.payer = payer;
        this.time = time;
        this.money = money;
        this.Payee = Payee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getPayee() {
        return Payee;
    }

    public void setPayee(String payee) {
        Payee = payee;
    }

    public String getPayer() {
        return this.payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserOutaccount{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", payer='" + payer + '\'' +
                ", time='" + time + '\'' +
                ", money='" + money + '\'' +
                ", Payee='" + Payee + '\'' +
                '}';
    }
}
