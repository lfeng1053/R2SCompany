package util;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.matches;
import static util.Constants.EMAIL_REGEX;

import util.Constants;
public class ValidationUtil {

    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
}