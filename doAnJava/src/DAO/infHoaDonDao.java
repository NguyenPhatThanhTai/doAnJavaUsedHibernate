package DAO;

import Model.DetailInfRepairEntity;
import Model.InfHoaDonEntity;
import Until.hibernateUntil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class infHoaDonDao implements daoInterface<InfHoaDonEntity> {
    @Override
    public boolean addData(InfHoaDonEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở HoaDon");
            return false;
        }
    }

    @Override
    public boolean dellData(InfHoaDonEntity data) {
        return false;
    }

    public boolean dellData(String id) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            InfHoaDonEntity infHoaDonEntity = s.load(InfHoaDonEntity.class, id);

            s.delete(infHoaDonEntity);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở Detail");
            return false;
        }
    }

    @Override
    public boolean updateData(InfHoaDonEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.update(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở hoadon");
            return false;
        }
    }

    @Override
    public InfHoaDonEntity getDataById(String Id) {
        return null;
    }

    @Override
    public List<InfHoaDonEntity> getALl() {
        return null;
    }
}
