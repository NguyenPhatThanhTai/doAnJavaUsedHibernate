package DAO;

import Model.DetailInfRepairEntity;
import Model.InfLichSuEntity;
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
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            DetailInfRepairEntity detailInfRepairEntity = s.load(DetailInfRepairEntity.class, data.getDetailId());

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
    public boolean updateData(DetailInfRepairEntity data) {
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
    public DetailInfRepairEntity getDataById(String Id) {
        Session session = null;
        DetailInfRepairEntity detailInfRepairEntity = null;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(DetailInfRepairEntity.class);
            Root<DetailInfRepairEntity> root = query.from(DetailInfRepairEntity.class);
            Predicate p = builder.equal(root.get("detailId"),Id);
            detailInfRepairEntity= (DetailInfRepairEntity) session.createQuery(query.where(p)).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return detailInfRepairEntity;
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

    public ObservableList<InfLichSuEntity> getAllLichSu(){
        Session s = hibernateUntil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(InfLichSuEntity.class);
        query.from(InfLichSuEntity.class);

        List<InfLichSuEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }
}
