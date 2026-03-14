import java.util.Objects;
import java.util.Scanner;

public class TraineeManagement {
    private Scanner scanner = new Scanner(System.in);
    private TraineeForm traineeForm;
    private Trainee[] listOfTrainees = new Trainee[100];
    private byte count;

    public TraineeManagement() {
        this.traineeForm = new TraineeForm(scanner);
    }

    public static void main(String[] args) {
        TraineeManagement tm = new TraineeManagement();
        tm.Menu();
    }

    private void Menu() {
        while (true) {
            System.out.println("\n1. Create trainee\n2. Display all\n3. Find by ID\n4. Find by name\n5. Update by ID\n0. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) break;
            switch (choice) {
                case "1" -> addTrainee();
                case "2" -> displayAllTrainees();
                case "3" -> {
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine().trim();
                    Trainee t = findTraineeById(id);
                    if (t != null) printTrainee(t); else System.out.println("Not found.");
                }
                case "4" -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();
                    Trainee[] arr = findTraineeByName(name);
                    if (arr.length == 0) System.out.println("Not found.");
                    else for (Trainee t : arr) printTrainee(t);
                }
                case "5" -> {
                    System.out.print("Enter ID to update: ");
                    String id = scanner.nextLine().trim();
                    Trainee t = findTraineeById(id);
                    if (t == null) { System.out.println("Not found."); break; }
                    Trainee newTrainee = traineeForm.getTrainee();
                    try { newTrainee.setId(id); } catch (Exception e) { System.out.println(e.getMessage()); break; }
                    updateTrainee(id, newTrainee);
                    System.out.println("Updated.");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printTrainee(Trainee t) {
        System.out.println(t.getId() + " " + t.getName() + " " + t.getAge() + " " + t.getGender());
    }

    private void addTrainee() {
        if (count >= listOfTrainees.length) {
            System.out.println("Full! Can't add trainee");
            return;
        }
        String id = traineeForm.getID();
        if (id.isEmpty()) {
            System.out.println("ID is empty!");
            return;
        }
        if (findTraineeById(id) != null) {
            System.out.println("ID already exists");
            return;
        }
        Trainee t = traineeForm.getTrainee();
            try{
                t.setId(id);
            } catch (Exception e){
                System.out.println(e.getMessage());
                return;
            }
            listOfTrainees[count++] = t;
            System.out.println("Add successfully.");

    }

    private void displayAllTrainees() {
        if (count == 0) {
            System.out.println("No trainees");
            return;
        }
        for (int i = 0; i <count; i++){
            printTrainee(listOfTrainees[i]);
        }
    }

    private Trainee findTraineeById(String id) {
        if (id == null) return null;
        for (int i = 0; i < count; i++) {
            if (Objects.equals(listOfTrainees[i].getId(), id)) return listOfTrainees[i];
        }
        return null;
    }


    private Trainee[] findTraineeByName(String name) {
        if (name == null) return new Trainee[0];
        int n = 0;
        for (int i = 0; i < count; i++) {
            String n2 = listOfTrainees[i].getName();
            if (n2 != null && n2.equalsIgnoreCase(name)) n++;
        }
        Trainee[] result = new Trainee[n];
        int j = 0;
        for (int i = 0; i < count; i++) {
            String n2 = listOfTrainees[i].getName();
            if (n2 != null && n2.equalsIgnoreCase(name)) result[j++] = listOfTrainees[i];
        }
        return result;
    }

    private void updateTrainee(String id, Trainee newTrainee) {
        for (int i = 0; i < count; i++) {
            if (Objects.equals(listOfTrainees[i].getId(), id)) {
                listOfTrainees[i] = newTrainee;
                return;
            }
        }
    }

}
