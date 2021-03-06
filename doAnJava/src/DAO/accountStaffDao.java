package DAO;

import Model.AccountStaffEntity;
import Model.DetailInfRepairEntity;
import Model.InfStaffEntity;
import Model.SalaryStaffEntity;
import Until.hibernateUntil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class accountStaffDao implements daoInterface<AccountStaffEntity> {
    @Override
    public boolean addData(AccountStaffEntity data) {
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
    public boolean dellData(AccountStaffEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();

            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(AccountStaffEntity.class);
            Root<AccountStaffEntity> root = query.from(AccountStaffEntity.class);
            Predicate p = builder.equal(root.get("staffAccount"),data.getStaffAccount());
            AccountStaffEntity accountStaffEntity= (AccountStaffEntity) s.createQuery(query.where(p)).getSingleResult();

            s.delete(accountStaffEntity);

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
    public boolean updateData(AccountStaffEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.update(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở account");
            return false;
        }
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
        }
        return accountStaffEntity;
    }

    @Override
    public ObservableList<AccountStaffEntity> getALl() {
        Session s = hibernateUntil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(AccountStaffEntity.class);
        query.from(AccountStaffEntity.class);

        List<AccountStaffEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }

    public ObservableList<SalaryStaffEntity> getAllSalary(){
        Session s = hibernateUntil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(SalaryStaffEntity.class);
        query.from(SalaryStaffEntity.class);

        List<SalaryStaffEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }

    public SalaryStaffEntity getSalaryById(String Id) {
        Session session = null;
        SalaryStaffEntity salaryStaffEntity = null;
        try {
            session = hibernateUntil.getSession();
            salaryStaffEntity = (SalaryStaffEntity)session.load(SalaryStaffEntity.class, Id);
            Hibernate.initialize(salaryStaffEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return salaryStaffEntity;
    }

    public boolean updateDataSalary(SalaryStaffEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.update(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở account");
            return false;
        }
    }
}
