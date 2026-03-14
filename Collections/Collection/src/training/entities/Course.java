package training.entities;

import training.utils.Validator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entity class representing a course.
 */
public class Course {

    private String code;
    private String name;
    private boolean status;
    private short duration;
    private String flag;

    public Course() {
    }

    public Course(String code, String name, boolean status, short duration, String flag) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status + " (" + (status ? "active" : "in-active") + ")" +
                ", duration=" + duration +
                ", flag='" + flag + '\'' +
                '}';
    }

    /**
     * Reads course data from keyboard; prompts for re-entry when input is invalid.
     */
    public void input(Scanner sc, ArrayList<Course> courses) {
        // Code: 5 chars, RA + 3 digits, unique
        while (true) {
            System.out.print("Enter course code (RA + 3 digits, e.g. RA001): ");
            String inputCode = sc.nextLine().trim();
            if (!Validator.validateCode(inputCode)) {
                System.out.println("Invalid code! Must be 5 characters, starting with RA and 3 digits.");
                continue;
            }
            if (Validator.isDuplicatedCode(inputCode, courses)) {
                System.out.println("Code already exists! Please enter another code.");
                continue;
            }
            this.code = inputCode.toUpperCase();
            break;
        }

        // Name: non-empty
        while (true) {
            System.out.print("Enter course name: ");
            String inputName = sc.nextLine().trim();
            if (inputName.isEmpty()) {
                System.out.println("Name cannot be empty.");
                continue;
            }
            this.name = inputName;
            break;
        }

        // Status: true (active) or false (in-active)
        while (true) {
            System.out.print("Enter status (true = active, false = in-active): ");
            String inputStatus = sc.nextLine().trim().toLowerCase();
            if ("true".equals(inputStatus) || "1".equals(inputStatus)) {
                this.status = true;
                break;
            }
            if ("false".equals(inputStatus) || "0".equals(inputStatus)) {
                this.status = false;
                break;
            }
            System.out.println("Only true/false (or 1/0) accepted.");
        }

        // Duration: number > 0
        while (true) {
            System.out.print("Enter duration (integer > 0): ");
            try {
                short d = Short.parseShort(sc.nextLine().trim());
                if (!Validator.validateDuration(d)) {
                    System.out.println("Duration must be greater than 0.");
                    continue;
                }
                this.duration = d;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }

        // Flag: optional, prerequisite, N/A
        while (true) {
            System.out.print("Enter flag (optional / prerequisite / N/A): ");
            String inputFlag = sc.nextLine().trim();
            if (!Validator.validateFlag(inputFlag)) {
                System.out.println("Flag only accepts: optional, prerequisite, N/A.");
                continue;
            }
            this.flag = inputFlag;
            break;
        }
    }
}
