package DAO;

import java.util.List;

public interface daoInterface<T> {

    public int addData(T data);
    public int dellData(T data);
    public int updateData(T data);

    List<T> getALl();
}
