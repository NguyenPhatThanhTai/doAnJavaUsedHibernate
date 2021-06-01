package DAO;

import Model.*;
import Until.hibernateUntil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class infLKDao implements daoInterface<InfLkEntity> {
    @Override
    public boolean addData(InfLkEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở LK");
            return false;
        }
    }

    @Override
    public boolean dellData(InfLkEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            InfLkEntity infCustomersEntity = s.load(InfLkEntity.class, data.getLkId());

            s.delete(infCustomersEntity);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở LK");
            return false;
        }
    }


    @Override
    public boolean updateData(InfLkEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.saveOrUpdate(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở LK");
            return false;
        }
    }

    @Override
    public InfLkEntity getDataById(String lkName) {
        Session session = null;
        InfLkEntity infLkEntity;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(InfLkEntity.class);
            Root<InfLkEntity> root = query.from(InfLkEntity.class);
            Predicate p = builder.equal(root.get("lkName"), lkName);
            infLkEntity= (InfLkEntity) session.createQuery(query.where(p)).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return infLkEntity;
    }

    public InfLkEntity getDataByName(String lkName) {
        Session session = null;
        InfLkEntity infLkEntity;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(InfLkEntity.class);
            Root<InfLkEntity> root = query.from(InfLkEntity.class);
            Predicate p = builder.equal(root.get("lkId"), lkName);
            infLkEntity= (InfLkEntity) session.createQuery(query.where(p)).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return infLkEntity;
    }

    @Override
    public ObservableList<InfLkEntity> getALl() {
        Session s = hibernateUntil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(InfLkEntity.class);
        query.from(InfLkEntity.class);

        List<InfLkEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }

    public ObservableList<InfLkEntity> getFilterNameLk(String lkName) {
        Session session = null;
        List<InfLkEntity> clist = null;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(InfLkEntity.class);
            Root<InfLkEntity> root = query.from(InfLkEntity.class);
            Predicate p = builder.equal(root.get("lkProducer"),lkName);
            clist = (List<InfLkEntity>) session.createQuery(query.where(p)).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return FXCollections.observableArrayList(clist);
    }

    public boolean addDetailLK(DetailLkEntity data){
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("Lỗi ở detailLK");
            return false;
        }
    }

    public ObservableList<DetailLkEntity> getAllByDetailId(String detailId){
        Session session = null;
        List<DetailLkEntity> clist = null;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(DetailLkEntity.class);
            Root<DetailLkEntity> root = query.from(DetailLkEntity.class);
            Predicate p = builder.equal(root.get("detailId"), detailId);
            clist = (List<DetailLkEntity>) session.createQuery(query.where(p)).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return FXCollections.observableArrayList(clist);
    }

    public boolean dellOldData(String detailId) {
        Session session = null;
        DetailLkEntity detailLkEntity;
        try {
            session = hibernateUntil.getSession();

            String stringQuery = "DELETE FROM DetailLkEntity where detailId = '" + detailId +"'";
            Query query = session.createQuery(stringQuery);

            Transaction t = session.beginTransaction();

            query.executeUpdate();
//            session.delete(detailLkEntity);

            t.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return true;
    }

    public ObservableList<InfLkEntity> getLkByDate(String date){
        Session session = null;
        List<InfLkEntity> clist = null;
        try {
            session = hibernateUntil.getSession();

            String stringQuery = "FROM InfLkEntity E where E.lkTimeAdd like '%"+date+"%'";
            Query query = session.createQuery(stringQuery);

            Transaction t = session.beginTransaction();
            clist= query.getResultList();
            t.commit();
            return FXCollections.observableArrayList(clist);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
