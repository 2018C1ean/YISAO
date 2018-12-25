package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 李雯晴 on 2018/12/21.
 */

@Entity
public class CompanyInaccount {

    @Id
    private Long id;
    private Long company_id;   //用户id
    @NotNull
    private String time;    //充钱时间
    @NotNull
    private Float money;    //金额
    @NotNull
    private String Payer;   //支付方名字

    @Generated(hash = 351849520)
    public CompanyInaccount(Long id, Long company_id, @NotNull String time,
            @NotNull Float money, @NotNull String Payer) {
        this.id = id;
        this.company_id = company_id;
        this.time = time;
        this.money = money;
        this.Payer = Payer;
    }

    @Generated(hash = 1648505155)
    public CompanyInaccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
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
