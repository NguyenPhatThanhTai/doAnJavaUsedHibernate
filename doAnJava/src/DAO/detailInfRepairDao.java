package DAO;

import Model.DetailInfRepairEntity;
import Until.hibernateUntil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class detailInfRepairDao implements daoInterface<DetailInfRepairEntity> {

    @Override
    public boolean addData(DetailInfRepairEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

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
    public boolean dellData(DetailInfRepairEntity data) {
        return false;
    }

    @Override
    public boolean updateData(DetailInfRepairEntity data) {
        return false;
    }

    @Override
    public DetailInfRepairEntity getDataById(String Id) {
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
