package dto;

public class StaffDTO {
    private int staffId;
    private String name;
    private String role;
    private String email;
    private String phone;
    private int storeId;

    public StaffDTO() {}

    public StaffDTO(int staffId, String name, String role, String email, String phone, int storeId) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.storeId = storeId;
    }

    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getStoreId() { return storeId; }
    public void setStoreId(int storeId) { this.storeId = storeId; }
}