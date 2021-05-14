package DAO;

import Model.AccountStaffEntity;
import Model.SalaryStaffEntity;
import Until.hibernateUntil;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class salaryStaffDao implements daoInterface<SalaryStaffEntity> {
    @Override
    public boolean addData(SalaryStaffEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở Salary");
            return false;
        }
    }

    @Override
    public boolean dellData(SalaryStaffEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();

            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(SalaryStaffEntity.class);
            Root<SalaryStaffEntity> root = query.from(SalaryStaffEntity.class);
            Predicate p = builder.equal(root.get("salaStaffId"),data.getSalaStaffId());
            SalaryStaffEntity salaryStaffEntity= (SalaryStaffEntity) s.createQuery(query.where(p)).getSingleResult();

            s.delete(salaryStaffEntity);

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
    public boolean updateData(SalaryStaffEntity data) {
        return false;
    }

    @Override
    public SalaryStaffEntity getDataById(String Id) {
        return null;
    }

    @Override
    public ObservableList<SalaryStaffEntity> getALl() {
        return null;
    }
}
