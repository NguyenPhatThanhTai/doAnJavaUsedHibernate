package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Account_Staff", schema = "dbo", catalog = "ProjectOne")
public class AccountStaffEntity {
    private String staffAccount;
    private String staffPassword;
    private String staffRole;

    @Id
    @Column(name = "Staff_Account")
    public String getStaffAccount() {
        return staffAccount;
    }

    public void setStaffAccount(String staffAccount) {
        this.staffAccount = staffAccount;
    }

    @Basic
    @Column(name = "Staff_Password")
    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    @Basic
    @Column(name = "Staff_Role")
    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStaffEntity that = (AccountStaffEntity) o;
        return Objects.equals(staffAccount, that.staffAccount) &&
                Objects.equals(staffPassword, that.staffPassword) &&
                Objects.equals(staffRole, that.staffRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffAccount, staffPassword, staffRole);
    }
}
