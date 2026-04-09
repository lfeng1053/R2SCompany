package form;

import entity.Staff;
import util.ScannerUtil;
import util.ValidationUtil;

public class StaffForm {

    public static Staff inputNewStaff() {
        while (true) {
            String name = ScannerUtil.readNonEmptyString("Enter staff name: ");
            String role = ScannerUtil.readNonEmptyString("Enter staff role: ");
            String email = ScannerUtil.readNonEmptyString("Enter staff email: ");
            String phone = ScannerUtil.readNonEmptyString("Enter staff phone: ");
            int storeId = ScannerUtil.readInt("Enter store ID: ");

            if (ValidationUtil.isValidString(name)
                    && ValidationUtil.isValidString(role)
                    && ValidationUtil.isValidEmail(email)
                    && ValidationUtil.isValidString(phone)) {
                return new Staff(0, name, role, email, phone, storeId);
            } else {
                System.out.println("Invalid input. Name/role/phone must not be empty and email must be valid.");
            }
        }
    }

    public static Staff inputUpdateStaff() {
        while (true) {
            int id = ScannerUtil.readInt("Enter staff ID to update: ");
            String name = ScannerUtil.readNonEmptyString("Enter new staff name: ");
            String role = ScannerUtil.readNonEmptyString("Enter new staff role: ");
            String email = ScannerUtil.readNonEmptyString("Enter new staff email: ");
            String phone = ScannerUtil.readNonEmptyString("Enter new staff phone: ");
            int storeId = ScannerUtil.readInt("Enter new store ID: ");

            if (ValidationUtil.isValidString(name)
                    && ValidationUtil.isValidString(role)
                    && ValidationUtil.isValidEmail(email)
                    && ValidationUtil.isValidString(phone)) {
                return new Staff(id, name, role, email, phone, storeId);
            } else {
                System.out.println("Invalid input. Name/role/phone must not be empty and email must be valid.");
            }
        }
    }

    public static int inputStaffId(String action) {
        return ScannerUtil.readInt("Enter staff ID to " + action + ": ");
    }
}
