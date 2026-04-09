package dao;

import entity.Staff;
import exception.DAOException;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDAO {

    @Override
    public void insert(Staff staff) throws DAOException{
        String sql = "INSERT INTO staffs (name, role, email, phone, store_id) VALUES (?,?,?,?,?)";
        int index = 1;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(index++, staff.getName());
            ps.setString(index++, staff.getRole());
            ps.setString(index++, staff.getEmail());
            ps.setString(index++, staff.getPhone());
            ps.setInt(index++, staff.getStoreId());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Failed to insert brand.", e);
        }
    }

    @Override
    public List<Staff> selectAll() throws DAOException{
        List<Staff> staffs = new ArrayList<>();
        String sql = "SELECT * FROM staffs";
        int index = 1;
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                staffs.add(new Staff(rs.getInt("staff_id"), rs.getString("name"),
                        rs.getString("role"), rs.getString("email"), rs.getString("phone"),
                        rs.getInt("store_id")));
            }

        } catch (SQLException e){
            throw new DAOException("Failed to get staffs list.", e);
        } return staffs;

    }

    @Override
    public void update(Staff staff) throws DAOException{
        String sql = "UPDATE staffs SET name = ?, role = ?, email = ?, phone = ?, store_id = ? WHERE staff_id =?";
        int index = 1;
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(index++, staff.getName());
            ps.setString(index++, staff.getRole());
            ps.setString(index++, staff.getEmail());
            ps.setString(index++, staff.getPhone());
            ps.setInt(index++, staff.getStoreId());
            ps.setInt(index++, staff.getStaffID());
            ps.executeUpdate();

        } catch (SQLException e){
            throw new DAOException("Failed to update staff.", e);
        }
    }

    @Override
    public void delete(int staffId) throws DAOException{
        String sql = "DELETE FROM staffs WHERE staff_id=?";
        int index = 1;
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(index++, staffId);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Failed to delete staff.", e);
        }
    }
}
