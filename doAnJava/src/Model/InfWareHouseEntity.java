package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Inf_WareHouse", schema = "dbo", catalog = "ProjectOne")
public class InfWareHouseEntity {
    private String wareHouseId;
    private String wareHouseName;
    private String wareHouseAddress;
    private Integer wareHouseCapacity;
    private Integer wareHouseTotal;

    @Id
    @Column(name = "WareHouse_Id")
    public String getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(String wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    @Basic
    @Column(name = "WareHouse_Name")
    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    @Basic
    @Column(name = "WareHouse_Address")
    public String getWareHouseAddress() {
        return wareHouseAddress;
    }

    public void setWareHouseAddress(String wareHouseAddress) {
        this.wareHouseAddress = wareHouseAddress;
    }

    @Basic
    @Column(name = "WareHouse_Capacity")
    public Integer getWareHouseCapacity() {
        return wareHouseCapacity;
    }

    public void setWareHouseCapacity(Integer wareHouseCapacity) {
        this.wareHouseCapacity = wareHouseCapacity;
    }

    @Basic
    @Column(name = "Ware_House_Total")
    public Integer getWareHouseTotal() {
        return wareHouseTotal;
    }

    public void setWareHouseTotal(Integer wareHouseTotal) {
        this.wareHouseTotal = wareHouseTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfWareHouseEntity that = (InfWareHouseEntity) o;
        return Objects.equals(wareHouseId, that.wareHouseId) &&
                Objects.equals(wareHouseName, that.wareHouseName) &&
                Objects.equals(wareHouseAddress, that.wareHouseAddress) &&
                Objects.equals(wareHouseCapacity, that.wareHouseCapacity) &&
                Objects.equals(wareHouseTotal, that.wareHouseTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wareHouseId, wareHouseName, wareHouseAddress, wareHouseCapacity, wareHouseTotal);
    }
}
