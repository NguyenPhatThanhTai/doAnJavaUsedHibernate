package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Inf_Repair", schema = "dbo", catalog = "ProjectOne")
public class InfRepairEntity {
    private String repairId;
    private String laptopName;
    private String laptopStatus;
    private String staffId;
    private InfCustomersEntity infCustomersByCustomerId;

    public InfRepairEntity(String repairId, String laptopName, String laptopStatus, String staffId, InfCustomersEntity infCustomersByCustomerId) {
        this.repairId = repairId;
        this.laptopName = laptopName;
        this.laptopStatus = laptopStatus;
        this.staffId = staffId;
        this.infCustomersByCustomerId = infCustomersByCustomerId;
    }

    public InfRepairEntity() {

    }

    @Id
    @Column(name = "Repair_Id")
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    @Basic
    @Column(name = "Laptop_Name")
    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    @Basic
    @Column(name = "Laptop_Status")
    public String getLaptopStatus() {
        return laptopStatus;
    }

    public void setLaptopStatus(String laptopStatus) {
        this.laptopStatus = laptopStatus;
    }

    @Basic
    @Column(name = "Staff_Id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfRepairEntity that = (InfRepairEntity) o;
        return Objects.equals(repairId, that.repairId) &&
                Objects.equals(laptopName, that.laptopName) &&
                Objects.equals(laptopStatus, that.laptopStatus) &&
                Objects.equals(staffId, that.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repairId, laptopName, laptopStatus, staffId);
    }

    @OneToOne
    @JoinColumn(name = "Customer_Id", referencedColumnName = "Customer_Id", nullable = false)
    public InfCustomersEntity getInfCustomersByCustomerId() {
        return infCustomersByCustomerId;
    }

    public void setInfCustomersByCustomerId(InfCustomersEntity infCustomersByCustomerId) {
        this.infCustomersByCustomerId = infCustomersByCustomerId;
    }
}
