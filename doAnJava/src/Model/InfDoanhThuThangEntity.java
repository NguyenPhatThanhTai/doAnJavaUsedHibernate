package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Inf_DoanhThuThang", schema = "dbo", catalog = "ProjectOne")
public class InfDoanhThuThangEntity {
    private String dtt;
    private String inputMoney;
    private String outputMoney;
    private String staffSalary;
    private String profitMoney;
    private Date month;

    @Id
    @Column(name = "DTT")
    public String getDtt() {
        return dtt;
    }

    public void setDtt(String dtt) {
        this.dtt = dtt;
    }

    @Basic
    @Column(name = "Input_Money")
    public String getInputMoney() {
        return inputMoney;
    }

    public void setInputMoney(String inputMoney) {
        this.inputMoney = inputMoney;
    }

    @Basic
    @Column(name = "Output_Money")
    public String getOutputMoney() {
        return outputMoney;
    }

    public void setOutputMoney(String outputMoney) {
        this.outputMoney = outputMoney;
    }

    @Basic
    @Column(name = "Staff_Salary")
    public String getStaffSalary() {
        return staffSalary;
    }

    public void setStaffSalary(String staffSalary) {
        this.staffSalary = staffSalary;
    }

    @Basic
    @Column(name = "Profit_Money")
    public String getProfitMoney() {
        return profitMoney;
    }

    public void setProfitMoney(String profitMoney) {
        this.profitMoney = profitMoney;
    }

    @Basic
    @Column(name = "Month")
    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfDoanhThuThangEntity that = (InfDoanhThuThangEntity) o;
        return Objects.equals(dtt, that.dtt) &&
                Objects.equals(inputMoney, that.inputMoney) &&
                Objects.equals(outputMoney, that.outputMoney) &&
                Objects.equals(staffSalary, that.staffSalary) &&
                Objects.equals(profitMoney, that.profitMoney) &&
                Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dtt, inputMoney, outputMoney, staffSalary, profitMoney, month);
    }
}
