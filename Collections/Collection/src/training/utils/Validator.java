package training.utils;

import training.entities.Course;

import java.util.ArrayList;

/**
 * Utility class for validating course data.
 */
public class Validator {

    /**
     * Validates course code: 5 characters, starts with "RA", followed by 3 digits.
     */
    public static boolean validateCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (!code.startsWith("RA")) {
            return false;
        }
        String digits = code.substring(2);
        for (int i = 0; i < digits.length(); i++) {
            if (!Character.isDigit(digits.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the course code already exists in the list.
     */
    public static boolean isDuplicatedCode(String code, ArrayList<Course> courses) {
        if (courses == null || code == null) {
            return false;
        }
        for (Course c : courses) {
            if (code.equalsIgnoreCase(c.getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Status accepts only true (active) or false (in-active).
     */
    public static boolean validateStatus(boolean status) {
        return true; // boolean is always true or false
    }

    /**
     * Flag accepts only: 'optional', 'prerequisite', 'N/A'.
     */
    public static boolean validateFlag(String flag) {
        if (flag == null) {
            return false;
        }
        return "optional".equalsIgnoreCase(flag)
                || "prerequisite".equalsIgnoreCase(flag)
                || "N/A".equalsIgnoreCase(flag);
    }

    /**
     * Duration must be a number greater than 0.
     */
    public static boolean validateDuration(short duration) {
        return duration > 0;
    }
}
