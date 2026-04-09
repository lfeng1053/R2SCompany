package util;

public class Constants {
    public static final String BRAND_HEADER = String.format("%-10s | %-30s", "Brand ID", "Brand Name");
    public static final String BRAND_ROW_FORMAT = "%-10d | %-30s";
    public static final String STAFF_HEADER = String.format(
            "%-10s | %-20s | %-15s | %-30s | %-15s | %-10s",
            "Staff ID", "Name", "Role", "Email", "Phone", "Store ID"
    );
    public static final String STAFF_ROW_FORMAT = "%-10d | %-20s | %-15s | %-30s | %-15s | %-10d%n";
}