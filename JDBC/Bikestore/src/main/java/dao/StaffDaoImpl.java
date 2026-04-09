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
    public void insert(Staff staff) throws DAOException {
        String sql = "INSERT INTO staffs (name, role, email, phone, store_id) VALUES (?,?,?,?,?)";
        int index = 1;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(index++, staff.getName());
            ps.setString(index++, staff.getRole());
            ps.setString(index++, staff.getEmail());
            ps.setString(index++, staff.getPhone());
            ps.setInt(index++, staff.getStoreId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to insert staff.", e);
        }
    }

    @Override
    public List<Staff> findAll() throws DAOException {
        List<Staff> staffs = new ArrayList<>();
        String sql = "SELECT * FROM staffs";
        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                staffs.add(mapStaff(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to get staffs list.", e);
        }
        return staffs;
    }

    @Override
    public List<Staff> findByFilters(String name, String role, Integer storeId) throws DAOException {
        List<Staff> staffs = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM staffs WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name.trim() + "%");
        }
        if (role != null && !role.trim().isEmpty()) {
            sql.append(" AND role LIKE ?");
            params.add("%" + role.trim() + "%");
        }
        if (storeId != null) {
            sql.append(" AND store_id = ?");
            params.add(storeId);
        }

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int index = 1;
            for (Object param : params) {
                if (param instanceof String s) {
                    ps.setString(index++, s);
                } else if (param instanceof Integer i) {
                    ps.setInt(index++, i);
                }
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    staffs.add(mapStaff(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to filter staff list.", e);
        }
        return staffs;
    }

    @Override
    public Staff findById(int staffId) throws DAOException {
        String sql = "SELECT * FROM staffs WHERE staff_id = ?";
        int index = 1;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(index++, staffId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapStaff(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find staff by ID.", e);
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) throws DAOException {
        return existsByUniqueField("email", email);
    }

    @Override
    public boolean existsByPhone(String phone) throws DAOException {
        return existsByUniqueField("phone", phone);
    }

    @Override
    public boolean existsByEmailExceptId(String email, int staffId) throws DAOException {
        return existsByUniqueFieldExceptId("email", email, staffId);
    }

    @Override
    public boolean existsByPhoneExceptId(String phone, int staffId) throws DAOException {
        return existsByUniqueFieldExceptId("phone", phone, staffId);
    }

    @Override
    public boolean hasLinkedOrders(int staffId) throws DAOException {
        String sql = "SELECT COUNT(*) FROM orders WHERE staff_id = ?";
        int index = 1;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(index++, staffId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to check staff linked orders.", e);
        }
    }

    @Override
    public void update(Staff staff) throws DAOException {
        String sql = "UPDATE staffs SET name = ?, role = ?, email = ?, phone = ?, store_id = ? WHERE staff_id =?";
        int index = 1;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(index++, staff.getName());
            ps.setString(index++, staff.getRole());
            ps.setString(index++, staff.getEmail());
            ps.setString(index++, staff.getPhone());
            ps.setInt(index++, staff.getStoreId());
            ps.setInt(index++, staff.getStaffID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to update staff.", e);
        }
    }

    @Override
    public void delete(int staffId) throws DAOException {
        String sql = "DELETE FROM staffs WHERE staff_id=?";
        int index = 1;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(index++, staffId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to delete staff.", e);
        }
    }

    private boolean existsByUniqueField(String column, String value) throws DAOException {
        String sql = "SELECT COUNT(*) FROM staffs WHERE " + column + " = ?";
        int index = 1;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(index++, value);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to check duplicate " + column + ".", e);
        }
    }

    private boolean existsByUniqueFieldExceptId(String column, String value, int staffId) throws DAOException {
        String sql = "SELECT COUNT(*) FROM staffs WHERE " + column + " = ? AND staff_id <> ?";
        int index = 1;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(index++, value);
            ps.setInt(index++, staffId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to check duplicate " + column + ".", e);
        }
    }

    private Staff mapStaff(ResultSet rs) throws SQLException {
        return new Staff(
                rs.getInt("staff_id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getInt("store_id")
        );
    }
}
