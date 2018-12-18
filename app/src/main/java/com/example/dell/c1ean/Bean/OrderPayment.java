package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.dell.c1ean.DAO.DaoSession;
import com.example.dell.c1ean.DAO.OrderPaymentDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.WorkerDao;

/**
 * Created by Eskii on 2018/11/28.
 * 订单金额分配表（主要参与者为阿姨和公司）
 */

@Entity
public class OrderPayment {

    @Id
    private Long order_id;  //订单id
    @ToOne(joinProperty = "order_id")
    private Order order;    //order_id为外键参照order表的主键id
    @NotNull
    private Float order_money;  //订单金额
    @NotNull
    private Long worker_id; //阿姨id
    @ToOne(joinProperty = "worker_id")
    private Worker worker;  //worker_id为外键参照worker表的主键id
    @NotNull
    private Float worker_money; //阿姨所得报酬
    @NotNull
    private Long company_id;    //公司id
    @NotNull
    private Float company_money;    //公司所抽成金额
    @NotNull
    private String order_time;  //下订单的时间
    private String paytoworker_time;    //发放报酬给阿姨的时间
    private String paytocompany_time;   //发放抽成给公司的时间
    @Generated(hash = 1041702162)
    private transient Long worker__resolvedKey;
    @Generated(hash = 219913283)
    private transient Long order__resolvedKey;
    /** Used for active entity operations. */
    @Generated(hash = 1627350023)
    private transient OrderPaymentDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 227001613)
    public OrderPayment(Long order_id, @NotNull Float order_money,
            @NotNull Long worker_id, @NotNull Float worker_money,
            @NotNull Long company_id, @NotNull Float company_money,
            @NotNull String order_time, String paytoworker_time,
            String paytocompany_time) {
        this.order_id = order_id;
        this.order_money = order_money;
        this.worker_id = worker_id;
        this.worker_money = worker_money;
        this.company_id = company_id;
        this.company_money = company_money;
        this.order_time = order_time;
        this.paytoworker_time = paytoworker_time;
        this.paytocompany_time = paytocompany_time;
    }

    @Generated(hash = 1869808785)
    public OrderPayment() {
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Float getOrder_money() {
        return order_money;
    }

    public void setOrder_money(Float order_money) {
        this.order_money = order_money;
    }

    public Long getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(Long worker_id) {
        this.worker_id = worker_id;
    }

    public Float getWorker_money() {
        return worker_money;
    }

    public void setWorker_money(Float worker_money) {
        this.worker_money = worker_money;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public Float getCompany_money() {
        return company_money;
    }

    public void setCompany_money(Float company_money) {
        this.company_money = company_money;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getPaytoworker_time() {
        return paytoworker_time;
    }

    public void setPaytoworker_time(String paytoworker_time) {
        this.paytoworker_time = paytoworker_time;
    }

    public String getPaytocompany_time() {
        return paytocompany_time;
    }

    public void setPaytocompany_time(String paytocompany_time) {
        this.paytocompany_time = paytocompany_time;
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
    @Generated(hash = 744508731)
    public void setWorker(@NotNull Worker worker) {
        if (worker == null) {
            throw new DaoException(
                    "To-one property 'worker_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.worker = worker;
            worker_id = worker.getWorker_id();
            worker__resolvedKey = worker_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1041680474)
    public Worker getWorker() {
        long __key = this.worker_id;
        if (worker__resolvedKey == null || !worker__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WorkerDao targetDao = daoSession.getWorkerDao();
            Worker workerNew = targetDao.load(__key);
            synchronized (this) {
                worker = workerNew;
                worker__resolvedKey = __key;
            }
        }
        return worker;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 114642759)
    public void setOrder(Order order) {
        synchronized (this) {
            this.order = order;
            order_id = order == null ? null : order.getId();
            order__resolvedKey = order_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 697111055)
    public Order getOrder() {
        Long __key = this.order_id;
        if (order__resolvedKey == null || !order__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OrderDao targetDao = daoSession.getOrderDao();
            Order orderNew = targetDao.load(__key);
            synchronized (this) {
                order = orderNew;
                order__resolvedKey = __key;
            }
        }
        return order;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2130147208)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrderPaymentDao() : null;
    }



}
