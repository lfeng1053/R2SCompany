package services;

import dao.StaffDAO;
import dto.StaffDTO;
import entity.Staff;
import exception.DAOException;
import mapper.StaffMapper;

import java.util.ArrayList;
import java.util.List;

public class StaffService {
    private final StaffDAO staffDAO;

    public StaffService(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    public List<StaffDTO> findByFilters(String name, String role, Integer storeId) throws DAOException {
        List<StaffDTO> out = new ArrayList<>();
        for (Staff s : staffDAO.findByFilters(name, role, storeId)) {
            out.add(StaffMapper.toDto(s));
        }
        return out;
    }

    public StaffDTO findById(int staffId) throws DAOException {
        return StaffMapper.toDto(staffDAO.findById(staffId));
    }

    public void create(StaffDTO dto) throws DAOException {
        Staff staff = StaffMapper.toEntity(dto);
        if (staffDAO.existsByEmail(staff.getEmail())) {
            throw new IllegalArgumentException("Cannot create staff: email already exists.");
        }
        if (staffDAO.existsByPhone(staff.getPhone())) {
            throw new IllegalArgumentException("Cannot create staff: phone already exists.");
        }
        staffDAO.insert(staff);
    }

    public void update(StaffDTO dto) throws DAOException {
        Staff staff = StaffMapper.toEntity(dto);
        if (staffDAO.findById(staff.getStaffID()) == null) {
            throw new IllegalArgumentException("Cannot update: staff ID does not exist.");
        }
        if (staffDAO.existsByEmailExceptId(staff.getEmail(), staff.getStaffID())) {
            throw new IllegalArgumentException("Cannot update: email already belongs to another staff.");
        }
        if (staffDAO.existsByPhoneExceptId(staff.getPhone(), staff.getStaffID())) {
            throw new IllegalArgumentException("Cannot update: phone already belongs to another staff.");
        }
        staffDAO.update(staff);
    }

    public void delete(int staffId) throws DAOException {
        if (staffDAO.findById(staffId) == null) {
            throw new IllegalArgumentException("Cannot delete: staff ID does not exist.");
        }
        if (staffDAO.hasLinkedOrders(staffId)) {
            throw new IllegalArgumentException("Cannot delete staff linked to orders.");
        }
        staffDAO.delete(staffId);
    }
}