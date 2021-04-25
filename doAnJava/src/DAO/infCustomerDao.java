package DAO;

import Model.DetailInfRepairEntity;
import Model.InfCustomersEntity;
import Model.InfRepairEntity;
import Model.InfStaffEntity;
import Until.hibernateUntil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class infCustomerDao implements daoInterface<InfCustomersEntity> {

    @Override
    public boolean addData(InfCustomersEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở Customer");
            return false;
        }
    }

    @Override
    public boolean dellData(InfCustomersEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            InfCustomersEntity infCustomersEntity = s.load(InfCustomersEntity.class, data.getCustomerId());

            s.delete(infCustomersEntity);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở Customer");
            return false;
        }
    }

    @Override
    public boolean updateData(InfCustomersEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.saveOrUpdate(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở Customer");
            return false;
        }
    }

    @Override
    public InfCustomersEntity getDataById(String Id) {
        Session session = null;
        InfCustomersEntity infCustomersEntity = null;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(InfCustomersEntity.class);
            Root<InfCustomersEntity> root = query.from(InfCustomersEntity.class);
            Predicate p = builder.equal(root.get("customerId"),Id);
            infCustomersEntity= (InfCustomersEntity) session.createQuery(query.where(p)).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return infCustomersEntity;
    }

    @Override
    public List<InfCustomersEntity> getALl() {
        return null;
    }
}
