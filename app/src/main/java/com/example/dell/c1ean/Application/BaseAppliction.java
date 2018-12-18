package com.example.dell.c1ean.Application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.DaoMaster;
import com.example.dell.c1ean.DAO.DaoSession;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.OrderPaymentDao;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.DAO.UserInaccountDao;
import com.example.dell.c1ean.DAO.UserOutaccountDao;
import com.example.dell.c1ean.DAO.WalletDao;
import com.example.dell.c1ean.DAO.WorkerDao;
import com.example.dell.c1ean.DAO.WorkerTimeTableDao;

/**
 * Created by Eskii on 2018/11/28.
 *
 */

public class BaseAppliction extends Application {

    private CompanyActivityDao companyActivityDao;
    private CompanyDao companyDao;
    private OrderDao orderDao;
    private OrderPaymentDao orderPaymentDao;
    private UserDao userDao;
    private UserInaccountDao userInaccountDao;
    private UserOutaccountDao userOutaccountDao;
    private WalletDao walletDao;
    private WorkerDao workerDao;
    private WorkerTimeTableDao workerTimeTableDao;

    @Override
    public void onCreate() {
        super.onCreate();

        initDbHelp();
    }

    private void initDbHelp(){

        companyActivityDao = getDaoSession("COMPANY_ACTIVITY").getCompanyActivityDao();
        companyDao = getDaoSession("COMPANY").getCompanyDao();
        orderDao = getDaoSession("ORDER").getOrderDao();
        orderPaymentDao = getDaoSession("ORDER_PAYMENT").getOrderPaymentDao();
        userDao = getDaoSession("USER").getUserDao();
        userInaccountDao = getDaoSession("USER_INACCOUNT").getUserInaccountDao();
        userOutaccountDao = getDaoSession("USER_OUTACCOUNT").getUserOutaccountDao();
        walletDao = getDaoSession("WALLET").getWalletDao();
        workerDao = getDaoSession("WORKER").getWorkerDao();
        workerTimeTableDao = getDaoSession("WORKER_TIME_TABLE").getWorkerTimeTableDao();

    }

    private DaoSession getDaoSession(String tbName){

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, tbName, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        return daoSession;
    }
    public com.example.dell.c1ean.DAO.CompanyActivityDao getCompanyActivityDao() {
        return companyActivityDao;
    }

    public void setCompanyActivityDao(com.example.dell.c1ean.DAO.CompanyActivityDao companyActivityDao) {
        this.companyActivityDao = companyActivityDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderPaymentDao getOrderPaymentDao() {
        return orderPaymentDao;
    }

    public void setOrderPaymentDao(OrderPaymentDao orderPaymentDao) {
        this.orderPaymentDao = orderPaymentDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserInaccountDao getUserInaccountDao() {
        return userInaccountDao;
    }

    public void setUserInaccountDao(UserInaccountDao userInaccountDao) {
        this.userInaccountDao = userInaccountDao;
    }

    public UserOutaccountDao getUserOutaccountDao() {
        return userOutaccountDao;
    }

    public void setUserOutaccountDao(UserOutaccountDao userOutaccountDao) {
        this.userOutaccountDao = userOutaccountDao;
    }

    public WalletDao getWalletDao() {
        return walletDao;
    }

    public void setWalletDao(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    public WorkerDao getWorkerDao() {
        return workerDao;
    }

    public void setWorkerDao(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    public WorkerTimeTableDao getWorkerTimeTableDao() {
        return workerTimeTableDao;
    }

    public void setWorkerTimeTableDao(WorkerTimeTableDao workerTimeTableDao) {
        this.workerTimeTableDao = workerTimeTableDao;
    }
}
