package DAO;

import Model.AccountStaffEntity;
import Model.InfCustomersEntity;
import javafx.collections.ObservableList;

import java.util.List;

public interface daoInterface<T> {

    public int addData(T data);
    public int dellData(T data);
    public int updateData(T data);
    public T getDataById(String Id);

    List<T> getALl();
}
