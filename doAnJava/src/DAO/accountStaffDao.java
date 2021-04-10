package DAO;

import Model.AccountStaffEntity;
import Model.InfStaffEntity;
import Until.hibernateUntil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateError;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import java.util.List;

public class accountStaffDao implements daoInterface<AccountStaffEntity> {
    @Override
    public boolean addData(AccountStaffEntity data) {
        return true;
    }

    @Override
    public boolean dellData(AccountStaffEntity data) {
        return true;
    }

    @Override
    public boolean updateData(AccountStaffEntity data) {
        return true;
    }

    @Override
    public AccountStaffEntity getDataById(String Id) {
        Session session = null;
        AccountStaffEntity accountStaffEntity = null;
        try {
            session = hibernateUntil.getSession();
            accountStaffEntity = (AccountStaffEntity)session.load(AccountStaffEntity.class, Id);
            Hibernate.initialize(accountStaffEntity);
        } catch (Exception e) {
            return null;
        }
        return accountStaffEntity;
    }

    @Override
    public List<AccountStaffEntity> getALl() {
        return null;
    }
}
