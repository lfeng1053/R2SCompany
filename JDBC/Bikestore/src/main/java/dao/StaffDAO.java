package dao;

import com.mysql.cj.xdevapi.XDevAPIError;
import entity.Staff;
import exception.DAOException;

import java.util.List;

public interface StaffDAO {
    void insert(Staff staff) throws DAOException;
    List<Staff> selectAll() throws DAOException;
    void update(Staff staff) throws DAOException;
    void delete(int staffId) throws DAOException;
}
