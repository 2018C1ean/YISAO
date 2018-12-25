package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by DELL on 2018/12/21.
 */

@Entity
public class WokerInaccount {

    @Id
    private Long id;
    private Long worker_id;   //用户id
    @NotNull
    private String time;    //充钱时间
    @NotNull
    private Float money;    //金额
    @NotNull
    private String Payer;   //支付方名字

    @Generated(hash = 89896774)
    public WokerInaccount(Long id, Long worker_id, @NotNull String time,
            @NotNull Float money, @NotNull String Payer) {
        this.id = id;
        this.worker_id = worker_id;
        this.time = time;
        this.money = money;
        this.Payer = Payer;
    }

    @Generated(hash = 1473421605)
    public WokerInaccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(Long worker_id) {
        this.worker_id = worker_id;
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
