package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Inf_DoanhThuSua", schema = "dbo", catalog = "ProjectOne")
public class InfDoanhThuSuaEntity {
    private String mdt;
    private String entity;
    private String money;
    private Date day;
    private InfDoanhThuThangEntity infDoanhThuThangByDtt;

    public InfDoanhThuSuaEntity() {
    }

    public InfDoanhThuSuaEntity(String mdt) {
        this.mdt = mdt;
    }

    public InfDoanhThuSuaEntity(String mdt, String entity, String money, Date day, InfDoanhThuThangEntity infDoanhThuThangByDtt) {
        this.mdt = mdt;
        this.entity = entity;
        this.money = money;
        this.day = day;
        this.infDoanhThuThangByDtt = infDoanhThuThangByDtt;
    }

    @Id
    @Column(name = "MDT")
    public String getMdt() {
        return mdt;
    }

    public void setMdt(String mdt) {
        this.mdt = mdt;
    }

    @Basic
    @Column(name = "Entity")
    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Basic
    @Column(name = "Money")
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Basic
    @Column(name = "Day")
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfDoanhThuSuaEntity that = (InfDoanhThuSuaEntity) o;
        return Objects.equals(mdt, that.mdt) &&
                Objects.equals(entity, that.entity) &&
                Objects.equals(money, that.money) &&
                Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdt, entity, money, day);
    }

    @ManyToOne
    @JoinColumn(name = "DTT", referencedColumnName = "DTT")
    public InfDoanhThuThangEntity getInfDoanhThuThangByDtt() {
        return infDoanhThuThangByDtt;
    }

    public void setInfDoanhThuThangByDtt(InfDoanhThuThangEntity infDoanhThuThangByDtt) {
        this.infDoanhThuThangByDtt = infDoanhThuThangByDtt;
    }
}
