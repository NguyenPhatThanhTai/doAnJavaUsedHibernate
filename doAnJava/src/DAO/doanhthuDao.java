package DAO;

import Model.DetailInfRepairEntity;
import Model.InfDoanhThuSuaEntity;
import Model.InfDoanhThuThangEntity;
import Until.hibernateUntil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class doanhthuDao {
    public InfDoanhThuSuaEntity getByDate(Date date){
        Session session = null;
        InfDoanhThuSuaEntity infDoanhThuSuaEntity = null;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(InfDoanhThuSuaEntity.class);
            Root<InfDoanhThuSuaEntity> root = query.from(InfDoanhThuSuaEntity.class);
            Predicate p = builder.equal(root.get("day"), date);
            infDoanhThuSuaEntity= (InfDoanhThuSuaEntity) session.createQuery(query.where(p)).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return infDoanhThuSuaEntity;
    }

    public boolean addNewDoanhThu(InfDoanhThuSuaEntity data){
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở doanh thu");
            return false;
        }
    }

    public boolean updateDoanhThu(InfDoanhThuSuaEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.update(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở doanh thu");
            return false;
        }
    }

    //Doanh thu thang

    public InfDoanhThuThangEntity getByDateDoanhThuThang(Date date){
        Session session = null;
        InfDoanhThuThangEntity infDoanhThuThangEntity = null;
        try {
            session = hibernateUntil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(InfDoanhThuThangEntity.class);
            Root<InfDoanhThuThangEntity> root = query.from(InfDoanhThuThangEntity.class);
            Predicate p = builder.equal(root.get("month"), date);
            infDoanhThuThangEntity= (InfDoanhThuThangEntity) session.createQuery(query.where(p)).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return infDoanhThuThangEntity;
    }

    public boolean addNewDoanhThuThang(InfDoanhThuThangEntity data){
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.save(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở doanh thu");
            return false;
        }
    }

    public boolean updateDoanhThuThang(InfDoanhThuThangEntity data) {
        try {
            Session s = hibernateUntil.getSession();
            Transaction t = s.beginTransaction();
            s.update(data);

            t.commit();
            s.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi ở doanh thu");
            return false;
        }
    }
}
