package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Inf_LK", schema = "dbo", catalog = "ProjectOne")
public class InfLkEntity {
    private String lkId;
    private String lkName;
    private String lkNumber;
    private String lkProducer;
    private String lkPrice;
    private Date lkTimeAdd;

    public InfLkEntity() {
    }

    public InfLkEntity(String lkId, String lkName, String lkNumber, String lkProducer, String lkPrice, Date lkTimeAdd) {
        this.lkId = lkId;
        this.lkName = lkName;
        this.lkNumber = lkNumber;
        this.lkProducer = lkProducer;
        this.lkPrice = lkPrice;
        this.lkTimeAdd = lkTimeAdd;
    }

    @Id
    @Column(name = "LK_Id")
    public String getLkId() {
        return lkId;
    }

    public void setLkId(String lkId) {
        this.lkId = lkId;
    }

    @Basic
    @Column(name = "LK_Name")
    public String getLkName() {
        return lkName;
    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    @Basic
    @Column(name = "LK_Number")
    public String getLkNumber() {
        return lkNumber;
    }

    public void setLkNumber(String lkNumber) {
        this.lkNumber = lkNumber;
    }

    @Basic
    @Column(name = "LK_Producer")
    public String getLkProducer() {
        return lkProducer;
    }

    public void setLkProducer(String lkProducer) {
        this.lkProducer = lkProducer;
    }

    @Basic
    @Column(name = "LK_Price")
    public String getLkPrice() {
        return lkPrice;
    }

    public void setLkPrice(String lkPrice) {
        this.lkPrice = lkPrice;
    }

    @Basic
    @Column(name = "LK_Time_Add")
    public Date getLkTimeAdd() {
        return lkTimeAdd;
    }

    public void setLkTimeAdd(Date lkTimeAdd) {
        this.lkTimeAdd = lkTimeAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfLkEntity that = (InfLkEntity) o;
        return Objects.equals(lkId, that.lkId) &&
                Objects.equals(lkName, that.lkName) &&
                Objects.equals(lkNumber, that.lkNumber) &&
                Objects.equals(lkProducer, that.lkProducer) &&
                Objects.equals(lkPrice, that.lkPrice) &&
                Objects.equals(lkTimeAdd, that.lkTimeAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lkId, lkName, lkNumber, lkProducer, lkPrice, lkTimeAdd);
    }
}
