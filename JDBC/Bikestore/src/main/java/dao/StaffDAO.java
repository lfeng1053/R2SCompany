package dao;

import entity.Staff;
import exception.DAOException;

import java.util.List;

public interface StaffDAO {
    void insert(Staff staff) throws DAOException;
    List<Staff> findAll() throws DAOException;
    List<Staff> findByFilters(String name, String role, Integer storeId) throws DAOException;
    Staff findById(int staffId) throws DAOException;
    boolean existsByEmail(String email) throws DAOException;
    boolean existsByPhone(String phone) throws DAOException;
    boolean existsByEmailExceptId(String email, int staffId) throws DAOException;
    boolean existsByPhoneExceptId(String phone, int staffId) throws DAOException;
    boolean hasLinkedOrders(int staffId) throws DAOException;
    void update(Staff staff) throws DAOException;
    void delete(int staffId) throws DAOException;
}
