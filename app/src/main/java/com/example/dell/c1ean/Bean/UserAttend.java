package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.dell.c1ean.DAO.DaoSession;
import com.example.dell.c1ean.DAO.UserAttendDao;
import com.example.dell.c1ean.DAO.UserDao;

/**
 * Created by DELL on 2018/12/21.
 * 用户签到表(待做)
 */

@Entity
public class UserAttend {

    @Id
    private Long id;
    private String attend_time;
    private Long user_id;
    @ToOne(joinProperty = "user_id")
    private User user;
    @Generated(hash = 251390918)
    private transient Long user__resolvedKey;
    /** Used for active entity operations. */
    @Generated(hash = 710841202)
    private transient UserAttendDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;


    @Generated(hash = 1813845464)
    public UserAttend(Long id, String attend_time, Long user_id) {
        this.id = id;
        this.attend_time = attend_time;
        this.user_id = user_id;
    }

    @Generated(hash = 1530092294)
    public UserAttend() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttend_time() {
        return attend_time;
    }

    public void setAttend_time(String attend_time) {
        this.attend_time = attend_time;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Keep
    public User getUser() {
        return user;
    }

    @Keep
    public void setUser(User user) {
        this.user = user;
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
    @Generated(hash = 1896745350)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserAttendDao() : null;
    }
}
