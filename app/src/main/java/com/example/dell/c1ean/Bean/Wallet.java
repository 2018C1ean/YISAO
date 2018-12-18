package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Eskii on 2018/11/28.
 * 用户钱包
 */

@Entity
public class Wallet {

    @Id
    private Long user_id;   //用户id(可为用户/阿姨/公司)
    private String bank_account;    //银行卡号
    private String alipay_account;  //支付宝账号
    private Float balance;  //余额

    @Generated(hash = 863114618)
    public Wallet(Long user_id, String bank_account, String alipay_account,
            Float balance) {
        this.user_id = user_id;
        this.bank_account = bank_account;
        this.alipay_account = alipay_account;
        this.balance = balance;
    }

    @Generated(hash = 1197745249)
    public Wallet() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
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
