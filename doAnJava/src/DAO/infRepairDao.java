package DAO;

import Model.DetailInfRepairEntity;
import Model.InfCustomersEntity;
import Model.InfRepairEntity;
import Until.hibernateUntil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class infRepairDao implements daoInterface {
    @Override
    public int addData(Object data) {
        return 0;
    }

    @Override
    public int dellData(Object data) {
        return 0;
    }

    @Override
    public int updateData(Object data) {
        return 0;
    }

    @Override
    public Object getDataById(String Id) {
        return null;
    }

    @Override
    public ObservableList<DetailInfRepairEntity> getALl() {
        Session s = hibernateUntil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(DetailInfRepairEntity.class);
        query.from(DetailInfRepairEntity.class);

        List<DetailInfRepairEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
}
}
