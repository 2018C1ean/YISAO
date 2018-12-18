package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.dell.c1ean.DAO.DaoSession;
import com.example.dell.c1ean.DAO.WorkerDao;
import com.example.dell.c1ean.DAO.CompanyDao;

/**
 * Created by Eskii on 2018/11/27.
 */

@Entity
public class Worker {

    @Id(autoincrement = true)
    private long worker_id; //阿姨id
    @NotNull
    private String worker_name; //阿姨名字
    private String img; //头像
    @NotNull
    private String worker_type; //服务类型
    @NotNull
    private String worker_tel;  //联系电话
    @NotNull
    private Long company_id;    //所属公司id
    @ToOne(joinProperty = "company_id")
    private Company company;
    private String password;    //密码
    private String introduction;    //个人介绍
    private String star;    //服务星级
    @Generated(hash = 1496811699)
    private transient Long company__resolvedKey;
    /** Used for active entity operations. */
    @Generated(hash = 1603328252)
    private transient WorkerDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public Worker() {
    }

    @Generated(hash = 1684492513)
    public Worker(long worker_id, @NotNull String worker_name, String img,
            @NotNull String worker_type, @NotNull String worker_tel,
            @NotNull Long company_id, String password, String introduction,
            String star) {
        this.worker_id = worker_id;
        this.worker_name = worker_name;
        this.img = img;
        this.worker_type = worker_type;
        this.worker_tel = worker_tel;
        this.company_id = company_id;
        this.password = password;
        this.introduction = introduction;
        this.star = star;
    }

    public long getWorker_id() {
        return worker_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getWorker_type() {
        return worker_type;
    }

    public void setWorker_type(String worker_type) {
        this.worker_type = worker_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorker_tel() {
        return worker_tel;
    }

    public void setWorker_tel(String worker_tel) {
        this.worker_tel = worker_tel;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public void setWorker_id(long worker_id) {
        this.worker_id = worker_id;
    }

    public Long getCompany_id() {
        return this.company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
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
    @Generated(hash = 972852814)
    public void setCompany(@NotNull Company company) {
        if (company == null) {
            throw new DaoException(
                    "To-one property 'company_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.company = company;
            company_id = company.getCompany_id();
            company__resolvedKey = company_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 533423294)
    public Company getCompany() {
        long __key = this.company_id;
        if (company__resolvedKey == null || !company__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CompanyDao targetDao = daoSession.getCompanyDao();
            Company companyNew = targetDao.load(__key);
            synchronized (this) {
                company = companyNew;
                company__resolvedKey = __key;
            }
        }
        return company;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 189527947)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWorkerDao() : null;
    }

}
