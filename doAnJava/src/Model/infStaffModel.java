package Model;

import java.util.Date;

public class infStaffModel {
    private String Staff_Id;
    private String Staff_Name;
    private String Staff_Sex;
    private String Staff_Birth;
    private String Staff_Address;
    private String Staff_Phone;
    private String Staff_Deparment;
    private Date Staff_TimeAdd;

    public infStaffModel() {
    }

    public infStaffModel(String staff_Id, String staff_Name, String staff_Sex, String staff_Birth, String staff_Address, String staff_Phone, String staff_Deparment, Date staff_TimeAdd) {
        Staff_Id = staff_Id;
        Staff_Name = staff_Name;
        Staff_Sex = staff_Sex;
        Staff_Birth = staff_Birth;
        Staff_Address = staff_Address;
        Staff_Phone = staff_Phone;
        Staff_Deparment = staff_Deparment;
        Staff_TimeAdd = staff_TimeAdd;
    }

    public String getStaff_Id() {
        return Staff_Id;
    }

    public void setStaff_Id(String staff_Id) {
        Staff_Id = staff_Id;
    }

    public String getStaff_Name() {
        return Staff_Name;
    }

    public void setStaff_Name(String staff_Name) {
        Staff_Name = staff_Name;
    }

    public String getStaff_Sex() {
        return Staff_Sex;
    }

    public void setStaff_Sex(String staff_Sex) {
        Staff_Sex = staff_Sex;
    }

    public String getStaff_Birth() {
        return Staff_Birth;
    }

    public void setStaff_Birth(String staff_Birth) {
        Staff_Birth = staff_Birth;
    }

    public String getStaff_Address() {
        return Staff_Address;
    }

    public void setStaff_Address(String staff_Address) {
        Staff_Address = staff_Address;
    }

    public String getStaff_Phone() {
        return Staff_Phone;
    }

    public void setStaff_Phone(String staff_Phone) {
        Staff_Phone = staff_Phone;
    }

    public String getStaff_Deparment() {
        return Staff_Deparment;
    }

    public void setStaff_Deparment(String staff_Deparment) {
        Staff_Deparment = staff_Deparment;
    }

    public Date getStaff_TimeAdd() {
        return Staff_TimeAdd;
    }

    public void setStaff_TimeAdd(Date staff_TimeAdd) {
        Staff_TimeAdd = staff_TimeAdd;
    }
}
