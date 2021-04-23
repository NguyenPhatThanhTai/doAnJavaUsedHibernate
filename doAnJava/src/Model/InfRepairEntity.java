package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Inf_Repair", schema = "dbo", catalog = "ProjectOne")
public class InfRepairEntity {
    private String repairId;
    private String laptopName;
    private String laptopStatus;
    private InfCustomersEntity infCustomersByCustomerId;
    private InfStaffEntity infStaffByStaffId;

    public InfRepairEntity() {
    }

    public InfRepairEntity(String repairId, String laptopName, String laptopStatus, InfCustomersEntity infCustomersByCustomerId, InfStaffEntity infStaffByStaffId) {
        this.repairId = repairId;
        this.laptopName = laptopName;
        this.laptopStatus = laptopStatus;
        this.infCustomersByCustomerId = infCustomersByCustomerId;
        this.infStaffByStaffId = infStaffByStaffId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfRepairEntity that = (InfRepairEntity) o;
        return Objects.equals(repairId, that.repairId) &&
                Objects.equals(laptopName, that.laptopName) &&
                Objects.equals(laptopStatus, that.laptopStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repairId, laptopName, laptopStatus);
    }

    @ManyToOne
    @JoinColumn(name = "Customer_Id", referencedColumnName = "Customer_Id", nullable = false)
    public InfCustomersEntity getInfCustomersByCustomerId() {
        return infCustomersByCustomerId;
    }

    public void setInfCustomersByCustomerId(InfCustomersEntity infCustomersByCustomerId) {
        this.infCustomersByCustomerId = infCustomersByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "Staff_Id", referencedColumnName = "Staff_Id")
    public InfStaffEntity getInfStaffByStaffId() {
        return infStaffByStaffId;
    }

    public void setInfStaffByStaffId(InfStaffEntity infStaffByStaffId) {
        this.infStaffByStaffId = infStaffByStaffId;
    }
}
