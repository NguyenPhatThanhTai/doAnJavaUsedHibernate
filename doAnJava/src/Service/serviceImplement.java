package Service;

import DAO.accountStaffDao;
import DAO.infStaffDao;
import Model.AccountStaffEntity;
import Model.InfStaffEntity;

public class serviceImplement implements serviceInterface {
    accountStaffDao aDao = new accountStaffDao();
    infStaffDao isDao = new infStaffDao();


    @Override
    public boolean checkLogin(String Id, String Password) {
        if (aDao.getDataById(Id) != null && aDao.getDataById(Id).getStaffAccount().equals(Id) &&
                aDao.getDataById(Id).getStaffPassword().equals(Password)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public InfStaffEntity getStaffData(String Id) {
        if (isDao.getDataById(Id) != null){
            return isDao.getDataById(Id);
        }
        return null;
    }
}
