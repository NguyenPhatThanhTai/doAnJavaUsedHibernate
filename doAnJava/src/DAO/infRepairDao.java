package DAO;

import Model.InfRepairEntity;
import Until.hibernateUntil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
    public ObservableList<InfRepairEntity> getALl() {
        List<InfRepairEntity> groupList;
        Session session = hibernateUntil.getSession();
        Query query = session.createQuery("select c from InfRepairEntity c join fetch c.infCustomersByCustomerId");
        //query.setParameter("id", id);
        groupList = query.list();
        return FXCollections.observableArrayList(groupList);
    }
}
