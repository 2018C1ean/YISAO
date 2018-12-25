package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 李雯晴 on 2018/11/28.
 * 用户钱包
 * （先不做钱包了）
 */

@Entity
public class UserWallet {

    @Id
    private Long id;
    private String user_type;
    private Long user_id;   //用户id(可为用户/阿姨/公司)
    private String alipay_account;  //支付宝账号
    private Float balance;  //余额

    @Generated(hash = 1947871223)
    public UserWallet(Long id, String user_type, Long user_id,
            String alipay_account, Float balance) {
        this.id = id;
        this.user_type = user_type;
        this.user_id = user_id;
        this.alipay_account = alipay_account;
        this.balance = balance;
    }

    @Generated(hash = 945413087)
    public UserWallet() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getAlipay_account() {
        return alipay_account;
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
