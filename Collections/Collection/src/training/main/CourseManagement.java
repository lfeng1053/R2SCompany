package training.main;

import training.entities.Course;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Course management console program - menu-driven.
 */
public class CourseManagement {

    private ArrayList<Course> courses;

    public CourseManagement() {
        this.courses = new ArrayList<>();
    }

    /**
     * Adds a new course by reading data from keyboard and appending to the list.
     */
    public void input(Scanner sc) {
        Course course = new Course();
        course.input(sc, courses);
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    /**
     * Searches courses by attribute (type) and value (data).
     * type: "code", "name", "status", "duration", "flag"
     */
    public ArrayList<Course> search(String type, Object data) {
        ArrayList<Course> result = new ArrayList<>();
        if (type == null || data == null) {
            return result;
        }
        String typeLower = type.trim().toLowerCase();
        for (Course c : courses) {
            boolean match = false;
            switch (typeLower) {
                case "code":
                    match = data.toString().equalsIgnoreCase(c.getCode());
                    break;
                case "name":
                    match = c.getName().toLowerCase().contains(data.toString().toLowerCase());
                    break;
                case "status":
                    boolean searchStatus = Boolean.parseBoolean(data.toString());
                    match = c.isStatus() == searchStatus;
                    break;
                case "duration":
                    try {
                        short d = Short.parseShort(data.toString().trim());
                        match = c.getDuration() == d;
                    } catch (NumberFormatException ignored) {
                    }
                    break;
                case "flag":
                    match = data.toString().equalsIgnoreCase(c.getFlag());
                    break;
                default:
                    break;
            }
            if (match) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Displays all courses filtered by flag.
     * If flag is empty or "all", displays every course.
     */
    public void displayAll(String flag) {
        if (courses.isEmpty()) {
            System.out.println("No courses yet.");
            return;
        }
        if (flag == null || flag.trim().isEmpty() || "all".equalsIgnoreCase(flag.trim())) {
            System.out.println("--- All courses ---");
            for (Course c : courses) {
                System.out.println(c);
            }
            return;
        }
        String flagFilter = flag.trim();
        System.out.println("--- Courses with flag = '" + flagFilter + "' ---");
        int count = 0;
        for (Course c : courses) {
            if (flagFilter.equalsIgnoreCase(c.getFlag())) {
                System.out.println(c);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No courses with this flag.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseManagement management = new CourseManagement();

        while (true) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Create a course and enter data from keyboard");
            System.out.println("2. Search courses by attribute");
            System.out.println("3. Display all courses by flag");
            System.out.println("4. Quit");
            System.out.print("Choose option (1-4): ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    management.input(sc);
                    break;

                case "2": {
                    System.out.println("Attributes: code, name, status, duration, flag");
                    System.out.print("Enter attribute name to search: ");
                    String type = sc.nextLine().trim();
                    System.out.print("Enter value to search: ");
                    String dataStr = sc.nextLine().trim();
                    Object data = dataStr;
                    if ("status".equalsIgnoreCase(type)) {
                        data = Boolean.parseBoolean(dataStr);
                    } else if ("duration".equalsIgnoreCase(type)) {
                        try {
                            data = Short.parseShort(dataStr);
                        } catch (NumberFormatException e) {
                            data = dataStr;
                        }
                    }
                    ArrayList<Course> found = management.search(type, data);
                    if (found.isEmpty()) {
                        System.out.println("No courses found.");
                    } else {
                        System.out.println("Found " + found.size() + " course(s):");
                        for (Course c : found) {
                            System.out.println(c);
                        }
                    }
                    break;
                }

                case "3": {
                    System.out.print("Enter flag to filter (optional/prerequisite/N/A or all to show all): ");
                    String flag = sc.nextLine().trim();
                    management.displayAll(flag);
                    break;
                }

                case "4":
                    System.out.println("Goodbye.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }
    }
}
