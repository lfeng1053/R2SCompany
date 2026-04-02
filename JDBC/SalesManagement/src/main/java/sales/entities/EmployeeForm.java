package sales.entities;

import sales.client.Employee;

import java.util.Scanner;

public class EmployeeForm {
    private final Scanner sc;

    public EmployeeForm(Scanner sc){this.sc = sc;}
    public int getId(){
        int id;

        System.out.print("Enter ID: ");
        id = Integer.parseInt(sc.nextLine());
        return id;
    }

    public Employee getEmployee(){
        Employee employee = new Employee();
        System.out.print("Enter employee last name: ");
        employee.setLastName(sc.nextLine());
        System.out.print("Enter employee first name: ");
        employee.setFirstName(sc.nextLine());
        System.out.print("Enter employee birth date: ");
        employee.setBirthdate(sc.nextLine());
        System.out.print("Enter employee Supervisor ID: ");
        employee.setSupervisor(sc.nextInt());

        return employee;
    }
}
