package entity;

public class Staff {
    private int staffID;
    private String name;
    private String role;
    private String email;
    private String phone;
    private int storeId;

    public Staff(int staffID, String name, String role, String email, String phone, int storeId){
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.storeId = storeId;


    }

    public int getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffID=" + staffID +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
