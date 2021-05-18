package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Inf_Staff", schema = "dbo", catalog = "ProjectOne")
public class InfStaffEntity {
    private String staffId;
    private String staffName;
    private String staffSex;
    private Date staffBirth;
    private String staffAddress;
    private String staffPhone;
    private String staffDeparment;
    private Date staffTimeAdd;

    public InfStaffEntity() {
    }

    public InfStaffEntity(String staffId) {
        this.staffId = staffId;
    }

    public String staffSex(){
        if (staffSex.equals("1")){
            return "Nam";
        }
        else {
            return "Nữ";
        }
    }

    public String staffDepartment(){
        if (staffDeparment.equals("1")){
            return "Quản lý";
        }
        else if (staffDeparment.equals("2")){
            return "Nhân viên";
        }
        else {
            return "Kế toán";
        }
    }

    public InfStaffEntity(String staffId, String staffName, String staffSex, Date staffBirth, String staffAddress, String staffPhone, String staffDeparment, Date staffTimeAdd) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffSex = staffSex;
        this.staffBirth = staffBirth;
        this.staffAddress = staffAddress;
        this.staffPhone = staffPhone;
        this.staffDeparment = staffDeparment;
        this.staffTimeAdd = staffTimeAdd;
    }

    @Id
    @Column(name = "Staff_Id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "Staff_Name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "Staff_Sex")
    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex;
    }

    @Basic
    @Column(name = "Staff_Birth")
    public Date getStaffBirth() {
        return staffBirth;
    }

    public void setStaffBirth(Date staffBirth) {
        this.staffBirth = staffBirth;
    }

    @Basic
    @Column(name = "Staff_Address")
    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

    @Basic
    @Column(name = "Staff_Phone")
    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    @Basic
    @Column(name = "Staff_Deparment")
    public String getStaffDeparment() {
        return staffDeparment;
    }

    public void setStaffDeparment(String staffDeparment) {
        this.staffDeparment = staffDeparment;
    }

    @Basic
    @Column(name = "Staff_TimeAdd")
    public Date getStaffTimeAdd() {
        return staffTimeAdd;
    }

    public void setStaffTimeAdd(Date staffTimeAdd) {
        this.staffTimeAdd = staffTimeAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfStaffEntity that = (InfStaffEntity) o;
        return Objects.equals(staffId, that.staffId) &&
                Objects.equals(staffName, that.staffName) &&
                Objects.equals(staffSex, that.staffSex) &&
                Objects.equals(staffBirth, that.staffBirth) &&
                Objects.equals(staffAddress, that.staffAddress) &&
                Objects.equals(staffPhone, that.staffPhone) &&
                Objects.equals(staffDeparment, that.staffDeparment) &&
                Objects.equals(staffTimeAdd, that.staffTimeAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, staffName, staffSex, staffBirth, staffAddress, staffPhone, staffDeparment, staffTimeAdd);
    }
}
