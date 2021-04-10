package DAO;

import Model.InfRepairEntity;
import Until.hibernateUntil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class infRepairDao implements daoInterface<InfRepairEntity> {
    @Override
    public boolean addData(InfRepairEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở infRepair");
            return false;
        }
    }

    @Override
    public boolean dellData(InfRepairEntity data) {
        return false;
    }

    @Override
    public boolean updateData(InfRepairEntity data) {
        return false;
    }

    @Override
    public InfRepairEntity getDataById(String Id) {
        return null;
    }

    @Override
    public List<InfRepairEntity> getALl() {
        return null;
    }
}
