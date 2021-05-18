package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Detail_Lk", schema = "dbo", catalog = "ProjectOne")
@IdClass(DetailLkEntityPK.class)
public class DetailLkEntity {
    private String detailId;
    private String lkId;
    private String entity;

    public DetailLkEntity() {
    }

    public DetailLkEntity(String detailId, String lkId, String entity) {
        this.detailId = detailId;
        this.lkId = lkId;
        this.entity = entity;
    }

    @Id
    @Column(name = "Detail_Id")
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    @Id
    @Column(name = "Lk_Id")
    public String getLkId() {
        return lkId;
    }

    public void setLkId(String lkId) {
        this.lkId = lkId;
    }

    @Basic
    @Column(name = "Entity")
    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailLkEntity that = (DetailLkEntity) o;
        return Objects.equals(detailId, that.detailId) &&
                Objects.equals(lkId, that.lkId) &&
                Objects.equals(entity, that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, lkId, entity);
    }
}
