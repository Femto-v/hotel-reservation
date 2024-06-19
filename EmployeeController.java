import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeController {
    public static void addEmployee(ArrayList<Employee> employees, Scanner scn) {
        System.out.print("Please enter your name: ");
        String name = scn.nextLine();
        System.out.print("Please enter your job position: ");
        String job = scn.nextLine();

        // Enter salary with exception handling
        double salary = 0;
        while (true) {
            try {
                System.out.print("Please enter your salary: ");
                salary = scn.nextDouble();
                scn.nextLine(); // Consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for salary.");
                scn.nextLine(); // Consume the invalid input
            }
        }
        System.err.println("");
        
        Employee emp = new Employee(salary, job);
        emp.setId(employees.size() + 1);
        emp.setName(name);
        employees.add(emp);
    }

    public static void printEmployeeInfo(ArrayList<Employee> employees)
    {
        System.out.println("Employee information\n");
        if(employees.size() == 0){
            System.out.println("(None)\n");
        }
        for(Employee emp:employees)
        {
            
			emp.print();
			
			System.out.println();
        }
    }

    public static void searchEmployee(ArrayList<Employee> employees,Scanner scn)
    {
        System.out.println("Please enter your id number: ");
        int id = scn.nextInt();
        System.out.println();
        for(Employee emp: employees)
        {

            if(emp.getId() == id)
            {
                emp.print();
                System.out.println();
            }
            else
            {
                System.out.println("Employee not found!");
                System.out.println();
            }
        }
    }

    public static void editEmployee(ArrayList<Employee> employees,Scanner scn)
    {
        System.out.println("Please enter your id number: ");
        int id = scn.nextInt();
        System.out.println();
        Employee emp = employees.get(id-1);

        System.out.println("Enter the new name:  (enter 'no' to keep it)");
        String name = scn.next();
        if(name.equals("no"))
        {
            name = emp.getName();
        }

        System.out.println("Enter the new job description:  (enter 'no' to keep it)");
		String job = scn.next();
        if(job.equals("no"))
        {
            job = emp.getJob();
        }

        double salary = emp.getSalary();
        while (true) {
            try {
                System.out.println("Enter the new salary:  (enter '-1' to keep it)");
                double inputSalary = scn.nextDouble();
                scn.nextLine(); // Consume newline
                if (inputSalary != -1) {
                    salary = inputSalary;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for salary.");
                scn.nextLine(); // Consume the invalid input
            }
        }
        emp.setName(name);
        emp.setJob(job);;
        emp.setSalary(salary);

    }
}
