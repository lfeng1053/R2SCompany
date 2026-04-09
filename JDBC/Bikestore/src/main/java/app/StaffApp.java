package app;

import dao.StaffDAO;
import dao.StaffDaoImpl;
import entity.Staff;

import java.util.List;
import java.util.Scanner;

public class StaffApp {
    public static void run(){
        StaffDAO staffDAO = new StaffDaoImpl();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n===== STAFF MENU =====");
            System.out.println("1. List all staffs");
            System.out.println("2. Add new staff");
            System.out.println("3. Update staff");
            System.out.println("4. Delete staff");
            String choiceInput = scanner.nextLine();

            int choice;
            try{
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e){
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            try{
                switch (choice) {
                    case 1 -> {
                        List<Staff> staffs = staffDAO.selectAll();
                        System.out.println();
                        for (Staff s : staffs){
                            System.out.print(Constants.STAFF_ROW_FORMAT)
                        }
                    }
                    case 2 -> staffDAO.insert(StaffForm.)
                    case 3 -> staffDAO.update();
                    case 4 -> staffDAO.delete();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.")
                }
            }
        }
    }
}
