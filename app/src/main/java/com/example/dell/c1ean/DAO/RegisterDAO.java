package com.example.dell.c1ean.DAO;

import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.Bean.Worker;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 李雯晴 on 2018/11/30.
 */

public class RegisterDAO {

    private BaseApplication baseApplication;
    private UserDao userDao;
    private CompanyDao companyDao;
    private WorkerDao workerDao;

    public RegisterDAO(BaseApplication baseApplication) {

        this.baseApplication = baseApplication;
        userDao = baseApplication.getUserDao();
        workerDao = baseApplication.getWorkerDao();
        companyDao = baseApplication.getCompanyDao();
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
     * 阿姨和公司进行注册时要判断他们是否已经注册(已设置了密码看做已注册)
     * @return
     */
    public boolean isRegister(String type,String tel,String code){
        QueryBuilder queryBuilder;
        if (type.equals("家政人员")){
            queryBuilder = workerDao.queryBuilder().where(WorkerDao.Properties.Worker_tel.eq(tel)); //找到表中电话符合条件的worker
            Worker worker = (Worker) queryBuilder.list().get(0);
            String pwd = worker.getPassword();
            if (pwd.isEmpty()){
                return false;   //未注册返回false
            }return true;
        }else {
            queryBuilder = companyDao.queryBuilder().where(CompanyDao.Properties.Company_tel.eq(tel),CompanyDao.Properties.Company_code.eq(code));
            Company company = (Company) queryBuilder.list().get(0);

            if (company.getPassword()==null){
                return false;
            }return true;
        }
    }

    public void setPassword(String type,String tel,String pwd){

        QueryBuilder queryBuilder;
        if (type.equals("家政人员")){
            queryBuilder = workerDao.queryBuilder().where(WorkerDao.Properties.Worker_tel.eq(tel)); //找到表中电话符合条件的worker
            Worker worker = (Worker) queryBuilder.list().get(0);
            worker.setPassword(pwd);    //设置密码
            workerDao.update(worker);
        }else {
            queryBuilder = companyDao.queryBuilder().where(CompanyDao.Properties.Company_tel.eq(tel));
            Company company = (Company) queryBuilder.list().get(0);
            company.setPassword(pwd);
            companyDao.update(company);
        }
    }

}
