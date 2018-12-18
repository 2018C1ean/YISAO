package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.dell.c1ean.DAO.DaoSession;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.DAO.WorkerDao;

/**
 * Created by Eskii on 2018/11/28.
 * 用户所下订单
 */

@Entity
public class Order {

    @Id(autoincrement = true)
    private Long id;    //订单id
    @NotNull
    private Long user_id;   //下订单的用户id
    @ToOne(joinProperty = "user_id")
    private User user;  //user_id为外键，参照user表的id
    @NotNull
    private Long worker_id; //提供服务的阿姨id
    @ToOne(joinProperty = "worker_id")
    private Worker worker;  //worker_id为外键，参照worker表的id
    private Long company_id;    //阿姨所属公司id
    @NotNull
    private String type;    //此订单服务类型
    @NotNull
    private Float area; //用户的房间大小
    @NotNull
    private String userLocation;    //用户地址
    @NotNull
    private Float money;    //订单金额
    @NotNull
    private String orderTime;   //下订单时间
    @NotNull
    private String bookingTime; //预约的服务时间
    @NotNull
    private String payTime; //用户付款时间
    @NotNull
    private int state;  //订单状态
    private String userEvaluation;  //用户评价
    private String workerEvaluation;    //阿姨回复
    private int star;   //用户评价星级
    @NotNull
    private int isActivity; //是否是商家发布的活动
    @Generated(hash = 1041702162)
    private transient Long worker__resolvedKey;
    @Generated(hash = 251390918)
    private transient Long user__resolvedKey;
    /** Used for active entity operations. */
    @Generated(hash = 949219203)
    private transient OrderDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public Order() {
    }

    @Generated(hash = 1767418604)
    public Order(Long id, @NotNull Long user_id, @NotNull Long worker_id,
            Long company_id, @NotNull String type, @NotNull Float area,
            @NotNull String userLocation, @NotNull Float money,
            @NotNull String orderTime, @NotNull String bookingTime,
            @NotNull String payTime, int state, String userEvaluation,
            String workerEvaluation, int star, int isActivity) {
        this.id = id;
        this.user_id = user_id;
        this.worker_id = worker_id;
        this.company_id = company_id;
        this.type = type;
        this.area = area;
        this.userLocation = userLocation;
        this.money = money;
        this.orderTime = orderTime;
        this.bookingTime = bookingTime;
        this.payTime = payTime;
        this.state = state;
        this.userEvaluation = userEvaluation;
        this.workerEvaluation = workerEvaluation;
        this.star = star;
        this.isActivity = isActivity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Keep
    public User getUser() {
        return user;
    }
    @Keep
    public void setUser(User user) {
        this.user = user;
    }

    @Keep
    public Worker getWorker() {
        return worker;
    }
    @Keep
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(Long worker_id) {
        this.worker_id = worker_id;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserEvaluation() {
        return userEvaluation;
    }

    public void setUserEvaluation(String userEvaluation) {
        this.userEvaluation = userEvaluation;
    }

    public String getWorkerEvaluation() {
        return workerEvaluation;
    }

    public void setWorkerEvaluation(String workerEvaluation) {
        this.workerEvaluation = workerEvaluation;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(int isActivity) {
        this.isActivity = isActivity;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 965731666)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrderDao() : null;
    }

}
