package form;

import entity.Staff;
import util.ScannerUtil;
import util.ValidationUtil;

public class StaffForm {
    public static Staff inputNewStaff(){
        while(true) {
            String name = ScannerUtil.readNonEmptyString("Enter staff name: ");
            String role = ScannerUtil.readNonEmptyString("Enter staff role: ");
            String email = ScannerUtil.readNonEmptyString("Enter staff name: ");
            String phone = ScannerUtil.readNonEmptyString("Enter staff name: ");
            if(ValidationUtil.isValidString(name) && ValidationUtil.isValidString(role)){
                return new Staff (0, name);
            }
        }
    }
}
