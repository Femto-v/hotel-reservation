import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeController {
    public static void addEmployee(ArrayList<Employee> employees, Scanner scn)
    {
        System.out.print("Please enter your name: ");
        String name = scn.next();
        System.out.print("Please enter your job position: ");
        String job = scn.next();
        System.out.print("Please enter your salary: ");
        double salary = scn.nextDouble();
        scn.nextLine();
        System.err.println("");
        Employee emp = new Employee(salary, job);
        emp.setId(employees.size()+1);
        emp.setName(name);
        employees.add(emp);
    }

    public static void printEmployeeInfo(ArrayList<Employee> employees)
    {
        System.out.println("Employee information");
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

        System.out.println("Enter the new salary : \n (enter '-1' to keep it)");
        double salary = scn.nextDouble();
        if(salary == -1)
        {
            salary = emp.getSalary();
        }

        emp.setName(name);
        emp.setJob(job);;
        emp.setSalary(salary);

    }
}
