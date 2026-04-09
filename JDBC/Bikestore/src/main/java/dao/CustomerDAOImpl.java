package dao;

import entity.Customer;
import exception.DAOException;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void insert(Customer customer) throws DAOException {
        String sql = "Insert into customers (name,gender,phone,email) values (?,?,?,?)";
        int index = 1;
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(index++, customer.getName());
            ps.setString(index++, customer.getGender());
            ps.setString(index++, customer.getPhone());
            ps.setString(index++, customer.getEmail());

            ps.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Failed to insert customer", e);
        }
    }

    @Override
    public List<Customer> findAll() throws DAOException {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from cusotmers";
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                customers.add(mapCustomer(rs));
            }
        } catch (SQLException e){
            throw new DAOException("Failed to get customers list", e);
        }
        return customers;
    }

    @Override
    public List<Customer> findByFilter(int customerId, String name, String gender, String phone, String email) throws DAOException {
        List<Customer> customers = new ArrayList<>();
        StringBuilder sql = new StringBuilder("Select * from customes where 1=1");
        List<Object> params = new ArrayList<>();

        if (customerId > 0) {
            sql.append(" AND customer_id = ?");
            params.add(customerId);
        }
        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name.trim() + "%");
        }
        if (gender != null && !gender.trim().isEmpty()) {
            sql.append(" AND gender = ?");
            params.add(gender.trim());
        }
        if (phone != null && !phone.trim().isEmpty()) {
            sql.append(" AND phone LIKE ?");
            params.add("%" + phone.trim() + "%");
        }
        if (email != null && !email.trim().isEmpty()) {
            sql.append(" AND email LIKE ?");
            params.add("%" + email.trim() + "%");
        }

        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())){
            int index = 1;
            for(Object param : params){
                if(param instanceof String s){
                    ps.setString(index++, s);
                } else if (param instanceof Integer i){
                    ps.setInt(index++, i);
                }
            }
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    customers.add(mapCustomer(rs));
                }
            }

        } catch (SQLException e){
                throw new DAOException("Failed to filter customer list", e);
        }
        return customers;

    }

    @Override
    public boolean hasLinkedOrder(int customerId) throws DAOException {
        return false;
    }

    @Override
    public void update(Customer customer) throws DAOException {
        String sql = "update customes set name = ?, gender = ?, phone = ?, email =? where customer_id = ?";
        int index = 1;
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(index++, customer.getName());
            ps.setString(index++, customer.getGender());
            ps.setString(index++, customer.getPhone());
            ps.setString(index++, customer.getEmail());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Failed to update customer.", e);
        }


    }

    @Override
    public void delete(int customerId) throws DAOException {
        String sql = "delete from customers where customer_id = ?";
        int index = 1;
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(index++, customerId);
            ps.executeUpdate();
        } catch(SQLException e){
            throw new DAOException("Failed to delete staff.", e);
        }
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException{
        return new Customer(
                rs.getInt("customer_id"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("phone"),
                rs.getString("email")
        );
    }
}

