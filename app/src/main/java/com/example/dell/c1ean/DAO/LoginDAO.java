package com.example.dell.c1ean.DAO;

import com.example.dell.c1ean.Application.BaseAppliction;
import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.Bean.Worker;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eskii on 2018/11/30.
 * 登录验证方法
 */

public class LoginDAO {

    private BaseAppliction baseAppliction;
    private UserDao userDao;
    private CompanyDao companyDao;
    private WorkerDao workerDao;

    public LoginDAO(BaseAppliction baseAppliction) {

        this.baseAppliction = baseAppliction;
        userDao = baseAppliction.getUserDao();
        companyDao = baseAppliction.getCompanyDao();
        workerDao = baseAppliction.getWorkerDao();
    }

    /**
     * 检验该用户是否存在
     * @param type  用户类型
     * @param tel   电话号码
     * @return
     */
    public boolean existValid(String type,String tel){

        QueryBuilder queryBuilder = null;
         if (type.equals("用户")) {
             queryBuilder = userDao.queryBuilder().where(UserDao.Properties.Tel.eq(tel));
             List<User> userList = queryBuilder.list();
             if (userList.size() > 0) {
                 return true;
             }
             return false;
         }
         else if (type.equals("家政人员")) {
             queryBuilder = workerDao.queryBuilder().where(WorkerDao.Properties.Worker_tel.eq(tel));
             List<Worker> workerList = queryBuilder.list();
             if (workerList.size() > 0) {
                 return true;
             }
             return false;
         }else{
                 queryBuilder = companyDao.queryBuilder().where(CompanyDao.Properties.Company_tel.eq(tel));
                 List<Company> companyList = queryBuilder.list();
                 if (companyList.size() > 0) {
                     return true;
                 }
                 return false;
         }
    }

    /**
     * 检验密码是否正确
     * @param type  用户类型
     * @param tel   电话号码
     * @param password  密码
     * @return
     */
    public boolean passwordValid(String type,String tel,String password){

        QueryBuilder queryBuilder = null;
        switch (type){
            case "用户":
                queryBuilder = userDao.queryBuilder().where(UserDao.Properties.Tel.eq(tel),UserDao.Properties.Password.eq(password));
                break;
            case "家政人员":
                queryBuilder = workerDao.queryBuilder().where(WorkerDao.Properties.Worker_tel.eq(tel),WorkerDao.Properties.Password.eq(password));
                break;
            case "家政公司":
                queryBuilder = companyDao.queryBuilder().where(CompanyDao.Properties.Company_tel.eq(tel),CompanyDao.Properties.Password.eq(password));
                break;
        }
        List list = queryBuilder.list();
        if (list.size() > 0){
            return true;
        }else return false;

    }
}
