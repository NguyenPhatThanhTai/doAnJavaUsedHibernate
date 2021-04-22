package DAO;

import Model.DetailInfRepairEntity;
import Model.InfLkEntity;
import Model.InfRepairEntity;
import Model.InfStaffEntity;
import Until.hibernateUntil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class infLKDao implements daoInterface<InfLkEntity> {
    @Override
    public boolean addData(InfLkEntity data) {
        return false;
    }

    @Override
    public boolean dellData(InfLkEntity data) {
        return false;
    }

    @Override
    public boolean updateData(InfLkEntity data) {
        return false;
    }

    @Override
    public InfLkEntity getDataById(String lkName) {
        return null;
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
}
