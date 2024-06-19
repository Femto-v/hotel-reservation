import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeController implements Searchable {
    // Define employees as a class-level variable
    private static ArrayList<Employee> employees = new ArrayList<>();

    public static void addEmployee(Scanner scn) {
        System.out.print("Please enter the employee's name: ");
        String name = scn.nextLine();
        System.out.print("Please enter the employee's job position: ");
        String job = scn.nextLine();

        // Enter salary with exception handling
        double salary = 0;
        while (true) {
            try {
                System.out.print("Please enter the employee's salary: ");
                salary = scn.nextDouble();
                scn.nextLine(); // Consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for salary.");
                scn.nextLine(); // Consume the invalid input
            }
        }

        Employee emp = new Employee(salary, job);
        emp.setId(employees.size() + 1);
        emp.setName(name);
        employees.add(emp);
    }

    public static void printEmployeeInfo() {
        System.out.println("Employee information\n");
        if (employees.size() == 0) {
            System.out.println("(None)\n");
        }
        for (Employee emp : employees) {
            emp.print();
        }
    }

    @Override
    public void search(int id) {
        boolean found = false;
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.print();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found!");
        }
    }

    public static void editEmployee(Scanner scn) {
        System.out.print("Please enter the employee's ID: ");
        int id = scn.nextInt();
        scn.nextLine(); // Consume newline
        System.out.println();

        boolean found = false;
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                found = true;

                System.out.print("Enter the new name (enter 'no' to keep it): ");
                String name = scn.nextLine();
                if (!name.equals("no")) {
                    emp.setName(name);
                }

                System.out.print("Enter the new job description (enter 'no' to keep it): ");
                String job = scn.nextLine();
                if (!job.equals("no")) {
                    emp.setJob(job);
                }

                //double salary = emp.getSalary();
                while (true) {
                    try {
                        System.out.print("Enter the new salary (enter '-1' to keep it): ");
                        double inputSalary = scn.nextDouble();
                        scn.nextLine(); // Consume newline
                        if (inputSalary != -1) {
                            emp.setSalary(inputSalary);
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for salary.");
                        scn.nextLine(); // Consume the invalid input
                    }
                }

                System.out.println("Employee details updated successfully.\n");
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found!\n");
        }
    }
}
