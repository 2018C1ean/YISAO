package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.dell.c1ean.DAO.DaoSession;
import com.example.dell.c1ean.DAO.WorkerTimeTableDao;
import com.example.dell.c1ean.DAO.WorkerDao;

/**
 * Created by Eskii on 2018/11/27.
 */

@Entity
public class WorkerTimeTable {

    @Id
    private Long worker_id;
    @ToOne(joinProperty = "worker_id")
    private Worker worker;
    private boolean am8;
    private boolean am9;
    private boolean am10;
    private boolean am11;
    private boolean am12;
    private boolean pm1;
    private boolean pm2;
    private boolean pm3;
    private boolean pm4;
    private boolean pm5;
    private boolean pm6;
    private boolean pm8;
    private boolean pm9;
    private boolean pm10;
    @Generated(hash = 1041702162)
    private transient Long worker__resolvedKey;
    /** Used for active entity operations. */
    @Generated(hash = 51139829)
    private transient WorkerTimeTableDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 2101463967)
    public WorkerTimeTable(Long worker_id, boolean am8, boolean am9, boolean am10,
            boolean am11, boolean am12, boolean pm1, boolean pm2, boolean pm3, boolean pm4,
            boolean pm5, boolean pm6, boolean pm8, boolean pm9, boolean pm10) {
        this.worker_id = worker_id;
        this.am8 = am8;
        this.am9 = am9;
        this.am10 = am10;
        this.am11 = am11;
        this.am12 = am12;
        this.pm1 = pm1;
        this.pm2 = pm2;
        this.pm3 = pm3;
        this.pm4 = pm4;
        this.pm5 = pm5;
        this.pm6 = pm6;
        this.pm8 = pm8;
        this.pm9 = pm9;
        this.pm10 = pm10;
    }

    @Generated(hash = 1820375793)
    public WorkerTimeTable() {
    }

    public Long getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(Long worker_id) {
        this.worker_id = worker_id;
    }

    @Keep
    public Worker getWorker() {
        return worker;
    }

    @Keep
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public boolean isAm8() {
        return am8;
    }

    public void setAm8(boolean am8) {
        this.am8 = am8;
    }

    public boolean isAm9() {
        return am9;
    }

    public void setAm9(boolean am9) {
        this.am9 = am9;
    }

    public boolean isAm10() {
        return am10;
    }

    public void setAm10(boolean am10) {
        this.am10 = am10;
    }

    public boolean isAm11() {
        return am11;
    }

    public void setAm11(boolean am11) {
        this.am11 = am11;
    }

    public boolean isAm12() {
        return am12;
    }

    public void setAm12(boolean am12) {
        this.am12 = am12;
    }

    public boolean isPm1() {
        return pm1;
    }

    public void setPm1(boolean pm1) {
        this.pm1 = pm1;
    }

    public boolean isPm2() {
        return pm2;
    }

    public void setPm2(boolean pm2) {
        this.pm2 = pm2;
    }

    public boolean isPm3() {
        return pm3;
    }

    public void setPm3(boolean pm3) {
        this.pm3 = pm3;
    }

    public boolean isPm4() {
        return pm4;
    }

    public void setPm4(boolean pm4) {
        this.pm4 = pm4;
    }

    public boolean isPm5() {
        return pm5;
    }

    public void setPm5(boolean pm5) {
        this.pm5 = pm5;
    }

    public boolean isPm6() {
        return pm6;
    }

    public void setPm6(boolean pm6) {
        this.pm6 = pm6;
    }

    public boolean isPm8() {
        return pm8;
    }

    public void setPm8(boolean pm8) {
        this.pm8 = pm8;
    }

    public boolean isPm9() {
        return pm9;
    }

    public void setPm9(boolean pm9) {
        this.pm9 = pm9;
    }

    public boolean isPm10() {
        return pm10;
    }

    public void setPm10(boolean pm10) {
        this.pm10 = pm10;
    }

    public boolean getPm10() {
        return this.pm10;
    }

    public boolean getPm9() {
        return this.pm9;
    }

    public boolean getPm8() {
        return this.pm8;
    }

    public boolean getPm6() {
        return this.pm6;
    }

    public boolean getPm5() {
        return this.pm5;
    }

    public boolean getPm4() {
        return this.pm4;
    }

    public boolean getPm3() {
        return this.pm3;
    }

    public boolean getPm2() {
        return this.pm2;
    }

    public boolean getPm1() {
        return this.pm1;
    }

    public boolean getAm12() {
        return this.am12;
    }

    public boolean getAm11() {
        return this.am11;
    }

    public boolean getAm10() {
        return this.am10;
    }

    public boolean getAm9() {
        return this.am9;
    }

    public boolean getAm8() {
        return this.am8;
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
    @Generated(hash = 749261088)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWorkerTimeTableDao() : null;
    }
}
