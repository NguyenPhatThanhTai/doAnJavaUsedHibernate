package Model;

import java.util.Date;

public class customerModel {
    private String Customer_Id;
    private String Customer_Name;
    private String Customer_Sex;
    private String Customer_Birth;
    private String Customer_Email;
    private String Customer_Phone;
    private Date Customer_TimeAdd;

    public customerModel() {

    }

    public customerModel(String customer_Id, String customer_Name, String customer_Sex, String customer_Birth, String customer_Email, String customer_Phone, Date customer_TimeAdd) {
        Customer_Id = customer_Id;
        Customer_Name = customer_Name;
        Customer_Sex = customer_Sex;
        Customer_Birth = customer_Birth;
        Customer_Email = customer_Email;
        Customer_Phone = customer_Phone;
        Customer_TimeAdd = customer_TimeAdd;
    }

    public String getCustomer_Id() {
        return Customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        Customer_Id = customer_Id;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getCustomer_Sex() {
        return Customer_Sex;
    }

    public void setCustomer_Sex(String customer_Sex) {
        Customer_Sex = customer_Sex;
    }

    public String getCustomer_Birth() {
        return Customer_Birth;
    }

    public void setCustomer_Birth(String customer_Birth) {
        Customer_Birth = customer_Birth;
    }

    public String getCustomer_Email() {
        return Customer_Email;
    }

    public void setCustomer_Email(String customer_Email) {
        Customer_Email = customer_Email;
    }

    public String getCustomer_Phone() {
        return Customer_Phone;
    }

    public void setCustomer_Phone(String customer_Phone) {
        Customer_Phone = customer_Phone;
    }

    public Date getCustomer_TimeAdd() {
        return Customer_TimeAdd;
    }

    public void setCustomer_TimeAdd(Date customer_TimeAdd) {
        Customer_TimeAdd = customer_TimeAdd;
    }
}
