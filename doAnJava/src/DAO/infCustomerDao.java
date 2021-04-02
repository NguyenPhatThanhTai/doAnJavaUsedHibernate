package DAO;

import Model.InfCustomersEntity;
import Model.InfRepairEntity;
import Until.hibernateUntil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class infCustomerDao implements daoInterface<InfCustomersEntity> {
    @Override
    public int addData(InfCustomersEntity data) {
        return 0;
    }

    @Override
    public int dellData(InfCustomersEntity data) {
        return 0;
    }

    @Override
    public int updateData(InfCustomersEntity data) {
        return 0;
    }

    @Override
    public InfCustomersEntity getDataById(String Id) {
        return null;
    }

    @Override
    public ObservableList<InfCustomersEntity> getALl() {
        Session s = hibernateUntil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(InfCustomersEntity.class);
        query.from(InfCustomersEntity.class);

        List<InfCustomersEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }
}
