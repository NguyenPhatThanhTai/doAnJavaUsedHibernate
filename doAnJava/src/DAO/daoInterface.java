package DAO;

import Model.AccountStaffEntity;
import Model.InfCustomersEntity;
import javafx.collections.ObservableList;

import java.util.List;

public interface daoInterface<T> {

    public boolean addData(T data);
    public boolean dellData(T data);
    public boolean updateData(T data);
    public T getDataById(String Id);

    List<T> getALl();
}
