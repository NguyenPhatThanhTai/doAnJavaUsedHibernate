package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Salary_Staff", schema = "dbo", catalog = "ProjectOne")
public class SalaryStaffEntity {
    private String staffId;
    private String staffDefaultSalary;
    private String staffSalaryPerHour;
    private String staffOt;
    private String staffReward;
    private String id;

    @Basic
    @Column(name = "Staff_Id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
    @Column(name = "Staff_Salary_Per_Hour")
    public String getStaffSalaryPerHour() {
        return staffSalaryPerHour;
    }

    public void setStaffSalaryPerHour(String staffSalaryPerHour) {
        this.staffSalaryPerHour = staffSalaryPerHour;
    }

    @Basic
    @Column(name = "Staff_OT")
    public String getStaffOt() {
        return staffOt;
    }

    public void setStaffOt(String staffOt) {
        this.staffOt = staffOt;
    }

    @Basic
    @Column(name = "Staff_Reward")
    public String getStaffReward() {
        return staffReward;
    }

    public void setStaffReward(String staffReward) {
        this.staffReward = staffReward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryStaffEntity that = (SalaryStaffEntity) o;
        return Objects.equals(staffId, that.staffId) &&
                Objects.equals(staffDefaultSalary, that.staffDefaultSalary) &&
                Objects.equals(staffSalaryPerHour, that.staffSalaryPerHour) &&
                Objects.equals(staffOt, that.staffOt) &&
                Objects.equals(staffReward, that.staffReward);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, staffDefaultSalary, staffSalaryPerHour, staffOt, staffReward);
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
