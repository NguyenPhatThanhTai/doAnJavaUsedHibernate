package Service;

import Model.AccountStaffEntity;
import Model.InfCustomersEntity;
import Model.InfStaffEntity;

public interface serviceInterface {
    public boolean checkLogin(String Id, String Password);
    public InfStaffEntity getStaffData(String Id);
}
