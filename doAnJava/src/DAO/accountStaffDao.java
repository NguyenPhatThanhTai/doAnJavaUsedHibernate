package DAO;

import Model.AccountStaffEntity;
import Model.InfStaffEntity;
import Until.hibernateUntil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class accountStaffDao implements daoInterface<AccountStaffEntity> {
    @Override
    public int addData(AccountStaffEntity data) {
        return 0;
    }

    @Override
    public int dellData(AccountStaffEntity data) {
        return 0;
    }

    @Override
    public int updateData(AccountStaffEntity data) {
        return 0;
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
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return accountStaffEntity;
    }

    @Override
    public List<AccountStaffEntity> getALl() {
        return null;
    }
}
