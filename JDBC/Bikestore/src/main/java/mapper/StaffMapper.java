package mapper;

import dto.StaffDTO;
import entity.Staff;

public final class StaffMapper {
    private StaffMapper() {}

    public static StaffDTO toDto(Staff staff) {
        if (staff == null) return null;
        return new StaffDTO(
                staff.getStaffID(),
                staff.getName(),
                staff.getRole(),
                staff.getEmail(),
                staff.getPhone(),
                staff.getStoreId()
        );
    }

    public static Staff toEntity(StaffDTO dto) {
        if (dto == null) return null;
        return new Staff(
                dto.getStaffId(),
                dto.getName(),
                dto.getRole(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getStoreId()
        );
    }
}