package DAO;

import Model.AccountStaffEntity;
import Model.InfStaffEntity;
import Until.hibernateUntil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class infStaffDao implements daoInterface<InfStaffEntity>{
    @Override
    public int addData(InfStaffEntity data) {
        return 0;
    }

    @Override
    public int dellData(InfStaffEntity data) {
        return 0;
    }

    @Override
    public int updateData(InfStaffEntity data) {
        return 0;
    }

    @Override
    public InfStaffEntity getDataById(String Id) {
        Session session = null;
        InfStaffEntity infStaffEntity = null;
        try {
            session = hibernateUntil.getSession();
            infStaffEntity = (InfStaffEntity)session.load(InfStaffEntity.class, Id);
            Hibernate.initialize(infStaffEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return infStaffEntity;
    }

    @Override
    public List<InfStaffEntity> getALl() {
        return null;
    }
}
