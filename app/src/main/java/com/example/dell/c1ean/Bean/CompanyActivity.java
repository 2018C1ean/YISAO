package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 李雯晴 on 2018/11/28.
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
    private String img1; //活动照片1
    private String img2; //活动照片2
    private String img3; //活动照片3
    private String unit;    //计价单位
    @NotNull
    private String start_time;  //活动开始时间
    @NotNull
    private String end_time;    //活动结束时间
    @NotNull
    private Float price;    //所定价格(price/h)
    @NotNull
    private Long company_id;    //举办的公司id
    @NotNull
    private String uses;   //用户可使用次数
    @NotNull
    private String activity_decribes;

    @Generated(hash = 1501661408)
    public CompanyActivity(Long id, @NotNull String title, @NotNull String type, @NotNull String img1, String img2, String img3, String unit,
            @NotNull String start_time, @NotNull String end_time, @NotNull Float price, @NotNull Long company_id, @NotNull String uses,
            @NotNull String activity_decribes) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.unit = unit;
        this.start_time = start_time;
        this.end_time = end_time;
        this.price = price;
        this.company_id = company_id;
        this.uses = uses;
        this.activity_decribes = activity_decribes;
    }

    @Generated(hash = 1389486425)
    public CompanyActivity() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getActivity_decribes() {
        return activity_decribes;
    }

    public void setActivity_decribes(String activity_decribes) {
        this.activity_decribes = activity_decribes;
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

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    @Override
    public String toString() {
        return getId()+"\n"+getImg1()+"\n"+getImg2()+"\n"+getImg3()+"\n"+getTitle()+"\n"+getType()+"\n"+getStart_time()+"\n"+getStart_time()+"\n"
                +getPrice()+"\n"+getUses()+"\n"+getCompany_id()+"\n";
    }
}
