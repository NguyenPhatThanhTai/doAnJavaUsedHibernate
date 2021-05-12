package DAO;

import Model.AccountStaffEntity;
import Model.DetailInfRepairEntity;
import Model.InfStaffEntity;
import Until.hibernateUntil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class infStaffDao implements daoInterface<InfStaffEntity>{
    @Override
    public boolean addData(InfStaffEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở Account");
            return false;
        }
    }

    @Override
    public boolean dellData(InfStaffEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            InfStaffEntity detailInfRepairEntity = s.load(InfStaffEntity.class, data.getStaffId());

            s.delete(detailInfRepairEntity);

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
    public boolean updateData(InfStaffEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.update(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở detail");
            return false;
        }
    }

    @Override
    public InfStaffEntity getDataById(String Id) {
        Session session = null;
        InfStaffEntity infStaffEntity = null;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(InfStaffEntity.class);
            Root<InfStaffEntity> root = query.from(InfStaffEntity.class);
            Predicate p = builder.equal(root.get("staffId"),Id);
            infStaffEntity= (InfStaffEntity) session.createQuery(query.where(p)).getSingleResult();
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
