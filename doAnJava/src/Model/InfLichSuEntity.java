package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Inf_LichSu", schema = "dbo", catalog = "ProjectOne")
public class InfLichSuEntity {
    private String customerId;
    private String customerName;
    private String customerSex;
    private Date customerBirth;
    private String customerEmail;
    private String customerPhone;
    private Date customerTimeAdd;
    private String repairId;
    private String laptopName;
    private String laptopStatus;
    private String repairReason;
    private String repairNote;
    private Date repairAppointment;
    private String repairMoney;
    private String repairStatus;
    private Date repairTimeEnd;
    private InfStaffEntity infStaffByStaffId;

    public String customerSex(){
        if (customerSex.equals("1")){
            return "Nam";
        }
        else {
            return "Nữ";
        }
    }

    public InfLichSuEntity() {
    }

    public InfLichSuEntity(String customerId, String customerName, String customerSex, Date customerBirth, String customerEmail, String customerPhone, Date customerTimeAdd, String repairId, String laptopName, String laptopStatus, String repairReason, String repairNote, Date repairAppointment, String repairMoney, String repairStatus, Date repairTimeEnd, InfStaffEntity infStaffByStaffId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSex = customerSex;
        this.customerBirth = customerBirth;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerTimeAdd = customerTimeAdd;
        this.repairId = repairId;
        this.laptopName = laptopName;
        this.laptopStatus = laptopStatus;
        this.repairReason = repairReason;
        this.repairNote = repairNote;
        this.repairAppointment = repairAppointment;
        this.repairMoney = repairMoney;
        this.repairStatus = repairStatus;
        this.repairTimeEnd = repairTimeEnd;
        this.infStaffByStaffId = infStaffByStaffId;
    }

    @Id
    @Column(name = "Customer_Id")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "Customer_Name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "Customer_Sex")
    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    @Basic
    @Column(name = "Customer_Birth")
    public Date getCustomerBirth() {
        return customerBirth;
    }

    public void setCustomerBirth(Date customerBirth) {
        this.customerBirth = customerBirth;
    }

    @Basic
    @Column(name = "Customer_Email")
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Basic
    @Column(name = "Customer_Phone")
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    @Column(name = "Customer_TimeAdd")
    public Date getCustomerTimeAdd() {
        return customerTimeAdd;
    }

    public void setCustomerTimeAdd(Date customerTimeAdd) {
        this.customerTimeAdd = customerTimeAdd;
    }

    @Basic
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
    @Column(name = "Repair_Reason")
    public String getRepairReason() {
        return repairReason;
    }

    public void setRepairReason(String repairReason) {
        this.repairReason = repairReason;
    }

    @Basic
    @Column(name = "Repair_Note")
    public String getRepairNote() {
        return repairNote;
    }

    public void setRepairNote(String repairNote) {
        this.repairNote = repairNote;
    }

    @Basic
    @Column(name = "Repair_Appointment")
    public Date getRepairAppointment() {
        return repairAppointment;
    }

    public void setRepairAppointment(Date repairAppointment) {
        this.repairAppointment = repairAppointment;
    }

    @Basic
    @Column(name = "Repair_Money")
    public String getRepairMoney() {
        return repairMoney;
    }

    public void setRepairMoney(String repairMoney) {
        this.repairMoney = repairMoney;
    }

    @Basic
    @Column(name = "Repair_Status")
    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    @Basic
    @Column(name = "Repair_Time_End")
    public Date getRepairTimeEnd() {
        return repairTimeEnd;
    }

    public void setRepairTimeEnd(Date repairTimeEnd) {
        this.repairTimeEnd = repairTimeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfLichSuEntity that = (InfLichSuEntity) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(customerSex, that.customerSex) &&
                Objects.equals(customerBirth, that.customerBirth) &&
                Objects.equals(customerEmail, that.customerEmail) &&
                Objects.equals(customerPhone, that.customerPhone) &&
                Objects.equals(customerTimeAdd, that.customerTimeAdd) &&
                Objects.equals(repairId, that.repairId) &&
                Objects.equals(laptopName, that.laptopName) &&
                Objects.equals(laptopStatus, that.laptopStatus) &&
                Objects.equals(repairReason, that.repairReason) &&
                Objects.equals(repairNote, that.repairNote) &&
                Objects.equals(repairAppointment, that.repairAppointment) &&
                Objects.equals(repairMoney, that.repairMoney) &&
                Objects.equals(repairStatus, that.repairStatus) &&
                Objects.equals(repairTimeEnd, that.repairTimeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerSex, customerBirth, customerEmail, customerPhone, customerTimeAdd, repairId, laptopName, laptopStatus, repairReason, repairNote, repairAppointment, repairMoney, repairStatus, repairTimeEnd);
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
