package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Salary_Staff", schema = "dbo", catalog = "ProjectOne")
public class SalaryStaffEntity {
    private String salaStaffId;
    private String staffDefaultSalary;
    private String staffReward;
    private String numberRepair;
    private String currentMoney;
    private InfStaffEntity infStaffByStaffId;

    public SalaryStaffEntity() {
    }

    public SalaryStaffEntity(String salaStaffId, String staffDefaultSalary, String staffReward, String numberRepair, String currentMoney, InfStaffEntity infStaffByStaffId) {
        this.salaStaffId = salaStaffId;
        this.staffDefaultSalary = staffDefaultSalary;
        this.staffReward = staffReward;
        this.numberRepair = numberRepair;
        this.currentMoney = currentMoney;
        this.infStaffByStaffId = infStaffByStaffId;
    }

    @Id
    @Column(name = "Sala_Staff_Id")
    public String getSalaStaffId() {
        return salaStaffId;
    }

    public void setSalaStaffId(String salaStaffId) {
        this.salaStaffId = salaStaffId;
    }

    @Basic
    @Column(name = "Staff_Default_Salary")
    public String getStaffDefaultSalary() {
        return staffDefaultSalary;
    }

    public void setStaffDefaultSalary(String staffDefaultSalary) {
        this.staffDefaultSalary = staffDefaultSalary;
    }

    @Basic
    @Column(name = "Staff_Reward")
    public String getStaffReward() {
        return staffReward;
    }

    public void setStaffReward(String staffReward) {
        this.staffReward = staffReward;
    }

    @Basic
    @Column(name = "Number_Repair")
    public String getNumberRepair() {
        return numberRepair;
    }

    public void setNumberRepair(String numberRepair) {
        this.numberRepair = numberRepair;
    }

    @Basic
    @Column(name = "Current_Money")
    public String getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(String currentMoney) {
        this.currentMoney = currentMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryStaffEntity that = (SalaryStaffEntity) o;
        return Objects.equals(salaStaffId, that.salaStaffId) &&
                Objects.equals(staffDefaultSalary, that.staffDefaultSalary) &&
                Objects.equals(staffReward, that.staffReward) &&
                Objects.equals(numberRepair, that.numberRepair) &&
                Objects.equals(currentMoney, that.currentMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaStaffId, staffDefaultSalary, staffReward, numberRepair, currentMoney);
    }

    @ManyToOne
    @JoinColumn(name = "Staff_Id", referencedColumnName = "Staff_Id", nullable = false)
    public InfStaffEntity getInfStaffByStaffId() {
        return infStaffByStaffId;
    }

    public void setInfStaffByStaffId(InfStaffEntity infStaffByStaffId) {
        this.infStaffByStaffId = infStaffByStaffId;
    }
}
