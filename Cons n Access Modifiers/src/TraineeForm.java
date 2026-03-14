import java.util.Scanner;
public class TraineeForm {
    private Scanner scanner;

    public TraineeForm(Scanner scanner) {
        this.scanner = scanner;
    }
    public String getID(){
        System.out.println("Enter ID: ");
        return scanner.nextLine().trim();
    }
    public Trainee getTrainee(){
        Trainee t = new Trainee();
        while(true){
            try{
                System.out.println("Enter name: ");
                t.setName(scanner.nextLine().trim());
                System.out.println("Enter age: ");
                t.setAge(Byte.parseByte(scanner.nextLine()));
                System.out.println("Enter gender: ");
                t.setGender(scanner.nextLine().trim());
                return t;

            } catch (Exception e){
                System.out.println("Invalid: " + e.getMessage() + " Try again.");
            }
        }
    }


}
