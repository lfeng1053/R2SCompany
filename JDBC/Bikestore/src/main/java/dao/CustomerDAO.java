package dao;

import entity.Customer;
import exception.DAOException;

import java.util.List;

public interface CustomerDAO {
    void insert(Customer customer) throws DAOException;

    List<Customer> findAll() throws DAOException;

    List<Customer> findByFilter(int customerId, String name, String gender, String phone, String email) throws DAOException;

    boolean hasLinkedOrder(int customerId) throws DAOException;

    void update(Customer customer) throws DAOException;

    void delete(int customerId) throws DAOException;

}