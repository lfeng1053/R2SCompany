package app;

import dao.StaffDaoImpl;
import dto.StaffDTO;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.StaffForm;
import mapper.StaffMapper;
import services.StaffService;
import util.Constants;

import java.util.List;
import java.util.Scanner;

public class StaffApp {
    public static void run() {
        StaffService staffService = new StaffService(new StaffDaoImpl());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== STAFF MENU =====");
            System.out.println("1. List all staffs");
            System.out.println("2. Add new staff");
            System.out.println("3. Update staff");
            System.out.println("4. Delete staff");
            System.out.println("5. Find staff by ID");
            System.out.println("0. Back to main menu");
            System.out.print("Choose: ");
            String choiceInput = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        String nameFilter = readOptionalFilter(scanner, "Filter by name (optional, Enter to skip): ");
                        String roleFilter = readOptionalFilter(scanner, "Filter by role (optional, Enter to skip): ");
                        Integer storeFilter = readOptionalIntFilter(scanner, "Filter by store ID (optional, Enter to skip): ");

                        List<StaffDTO> staffs = staffService.findByFilters(nameFilter, roleFilter, storeFilter);
                        if (staffs.isEmpty()) {
                            System.out.println("No staff found.");
                        } else {
                            System.out.println(Constants.STAFF_HEADER);
                            for (StaffDTO s : staffs) {
                                System.out.printf(
                                        Constants.STAFF_ROW_FORMAT,
                                        s.getStaffId(),
                                        s.getName(),
                                        s.getRole(),
                                        s.getEmail(),
                                        s.getPhone(),
                                        s.getStoreId()
                                );
                            }
                        }
                    }
                    case 2 -> {
                        staffService.create(StaffMapper.toDto(StaffForm.inputNewStaff()));
                        System.out.println("Staff created successfully.");
                    }
                    case 3 -> {
                        staffService.update(StaffMapper.toDto(StaffForm.inputUpdateStaff()));
                        System.out.println("Staff updated successfully.");
                    }
                    case 4 -> {
                        staffService.delete(StaffForm.inputStaffId("delete"));
                        System.out.println("Staff deleted successfully.");
                    }
                    case 5 -> {
                        StaffDTO staff = staffService.findById(StaffForm.inputStaffId("find"));
                        if (staff == null) {
                            System.out.println("Staff not found.");
                        } else {
                            System.out.println(Constants.STAFF_HEADER);
                            System.out.printf(
                                    Constants.STAFF_ROW_FORMAT,
                                    staff.getStaffId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getEmail(),
                                    staff.getPhone(),
                                    staff.getStoreId()
                            );
                        }
                    }
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (DAOException e) {
                GlobalExceptionHandler.handle(e);
            }
        }
    }

    private static String readOptionalFilter(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? null : input;
    }

    private static Integer readOptionalIntFilter(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }
}