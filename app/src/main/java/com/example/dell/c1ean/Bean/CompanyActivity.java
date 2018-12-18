package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Eskii on 2018/11/28.
 * 家政公司发布的活动
 */

@Entity
public class CompanyActivity {

    @Id(autoincrement = true)
    private Long id;    //活动id
    @NotNull
    private String title;   //活动标题
    @NotNull
    private String type;    //活动类型
    @NotNull
    private String img; //活动照片
    @NotNull
    private String start_time;  //活动开始时间
    @NotNull
    private String end_time;    //活动结束时间
    @NotNull
    private Float price;    //所定价格(price/h)
    @NotNull
    private Long company_id;    //举办的公司id
    @NotNull
    private int uses;   //用户可使用次数


    @Generated(hash = 2144064723)
    public CompanyActivity(Long id, @NotNull String title, @NotNull String type,
            @NotNull String img, @NotNull String start_time,
            @NotNull String end_time, @NotNull Float price,
            @NotNull Long company_id, int uses) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.img = img;
        this.start_time = start_time;
        this.end_time = end_time;
        this.price = price;
        this.company_id = company_id;
        this.uses = uses;
    }

    @Generated(hash = 1389486425)
    public CompanyActivity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }
}
