package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Inf_Customers", schema = "dbo", catalog = "ProjectOne")
public class InfCustomersEntity {
    private String customerId;
    private String customerName;
    private String customerSex;
    private Date customerBirth;
    private String customerEmail;
    private String customerPhone;
    private Date customerTimeAdd;

    public InfCustomersEntity() {
    }

    public InfCustomersEntity(String customerId) {
        this.customerId = customerId;
    }

    public InfCustomersEntity(String customerId, String customerName, String customerSex, Date customerBirth, String customerEmail, String customerPhone, Date customerTimeAdd) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSex = customerSex;
        this.customerBirth = customerBirth;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerTimeAdd = customerTimeAdd;
    }

    @Override
    public String toString() {
        if (customerSex.equals("1")){
            return "Nam";
        }
        else {
            return "Nữ";
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfCustomersEntity that = (InfCustomersEntity) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(customerSex, that.customerSex) &&
                Objects.equals(customerBirth, that.customerBirth) &&
                Objects.equals(customerEmail, that.customerEmail) &&
                Objects.equals(customerPhone, that.customerPhone) &&
                Objects.equals(customerTimeAdd, that.customerTimeAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerSex, customerBirth, customerEmail, customerPhone, customerTimeAdd);
    }
}
