package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DetailLkEntityPK implements Serializable {
    private String detailId;
    private String lkId;

    @Column(name = "Detail_Id")
    @Id
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    @Column(name = "Lk_Id")
    @Id
    public String getLkId() {
        return lkId;
    }

    public void setLkId(String lkId) {
        this.lkId = lkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailLkEntityPK that = (DetailLkEntityPK) o;
        return Objects.equals(detailId, that.detailId) &&
                Objects.equals(lkId, that.lkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, lkId);
    }
}
