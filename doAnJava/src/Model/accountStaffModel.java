package Model;

public class accountStaffModel {
    private String Staff_Id;
    private String Staff_Account;
    private String Staff_Password;
    private String Staff_Role;

    public accountStaffModel() {

    }

    public accountStaffModel(String staff_Account, String staff_Password) {
        Staff_Account = staff_Account;
        Staff_Password = staff_Password;
    }

    public accountStaffModel(String staff_Id, String staff_Account, String staff_Password, String staff_Role) {
        Staff_Id = staff_Id;
        Staff_Account = staff_Account;
        Staff_Password = staff_Password;
        Staff_Role = staff_Role;
    }

    public String getStaff_Id() {
        return Staff_Id;
    }

    public void setStaff_Id(String staff_Id) {
        Staff_Id = staff_Id;
    }

    public String getStaff_Account() {
        return Staff_Account;
    }

    public void setStaff_Account(String staff_Account) {
        Staff_Account = staff_Account;
    }

    public String getStaff_Password() {
        return Staff_Password;
    }

    public void setStaff_Password(String staff_Password) {
        Staff_Password = staff_Password;
    }

    public String getStaff_Role() {
        return Staff_Role;
    }

    public void setStaff_Role(String staff_Role) {
        Staff_Role = staff_Role;
    }
}
