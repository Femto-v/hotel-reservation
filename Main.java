import java.util.ArrayList;

import java.util.Scanner;
class LoginError extends Exception {
    public LoginError(String message) {
        super(message);
    }
}
public class Main {
    //Attribute
    private static ArrayList<Customer> customers;
    private static ArrayList<Employee> employees;
    private static ArrayList<Room> rooms; 
    private static ArrayList<Reservation> reservations;

    // Main
    public static void main(String[] args) {
        
        // Init Scanner
        Scanner scanner = new Scanner(System.in);
        
        
        // init int for max attempts
        int maxAttempts = 3;
        int attempts = 0;
        
        // init arraylist of employees
        employees = new ArrayList<>();
        
        // init arraylist of reservation
        reservations = new ArrayList<>();
        
        // Creating Default Rooms
        rooms = RoomsController.defaultRoomMenu();
        customers = new ArrayList<>();

        while (true) {
            // Homepage display
            Homepage();
            // Scan type of user
            int UserOpt = scanner.nextInt();
            scanner.nextLine(); 
            System.out.println();
            
            // Condition (after choose number) by using if else
            if(UserOpt == 1)
            {
                CustomerMenu(scanner, customers, reservations);
            }
            else if(UserOpt == 2)
            {
                
                while(attempts < maxAttempts)
                {
                    System.out.printf("Please enter the password: ");
                    String pw = scanner.next();
                    System.out.println("");

                    try{
                        if(pw.equals("admin123"))
                        {
                            AdminMenu(scanner, employees);
                            break;
                        }
                        else
                        {
                            attempts++;
                            throw new LoginError("Wrong password,please try again\n");
                        }
                    }catch(LoginError e)
                    {
                        System.out.println(e.getMessage());
                        if (attempts == maxAttempts) {
                            System.out.println("Too many failed attempts.\nFORCE EXIT (Security)\n");
                            return;
                            
                        }
                    }
                }
            }else if (UserOpt == 3){
                System.out.println("Exiting the system. Goodbye!\n");
                break;
            }else {
                System.out.println("Invalid option. Please try again.\n");
            }
        }
        
        
        

    }

    //Method
    public static void Homepage()
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ WELCOME TO THE HOTEL MANAGEMENT SYSTEM +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Please select your role: ");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.println("3. Exit");
        System.out.print("\nEnter your choice: ");
    }

    public static void CustomerMenu(Scanner scanner, ArrayList<Customer> customers, ArrayList<Reservation> reservations)
    {
        int i = 0;
        while (i !=7) 
        {
            System.out.println("-----------------");
            System.out.println("| CUSTOMER MENU |");
            System.out.println("-----------------");
            System.out.println("1. Register as the guest for the hotel");
            System.out.println("2. Create new reservation");// including make payment // have to display the room information menu
            System.out.println("3. Edit reservation");
            System.out.println("4. Edit profile");
            System.out.println("5. View your profile");
            System.out.println("6. View your reservation");
            System.out.println("7. Pay");
            System.out.println("8. Exit\n");
            System.out.printf("Enter your choice: ");
            i = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (i) {
                case 1:
                    CustomerController.addCustomer(customers, scanner);
                    break;
                case 2:
                    ReservationsController.createNewReservation(customers, rooms, reservations, scanner);
                    break;
                case 3:
                    ReservationsController.editReservation(customers, rooms, reservations, scanner);
                    break;
                case 4:
                    CustomerController.editCustomer(customers, scanner);
                    break;
                case 5:
                    CustomerController.printCustomerInfo(customers);
                    break;
                case 6:
                    ReservationsController.showAllReservations(reservations, scanner);
                    break;
                case 7:
                    ReservationsController.payReservation(reservations, scanner);
                    break;
                case 8:
                    System.out.println("Returning to Home Menu...\n");
                    return;
                default:
                    break;
            }
        }
        
        

    }

    public static void AdminMenu(Scanner scanner, ArrayList<Employee> employees)
    {
        int i =0;
        while (i !=7) 
        {
            System.out.println("--------------");
            System.out.println("| ADMIN MENU |");
            System.out.println("--------------");
            System.out.println("1. Add new employee");
            System.out.println("2. Edit employee profile");
            System.out.println("3. View all employees");
            System.out.println("4. Add new room");
            System.out.println("5. View all rooms information");
            System.out.println("6. Edit rooms data");
            System.out.println("7. Exit\n");
            System.out.printf("Enter your choice: ");
            i = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (i) {
                case 1:
                    EmployeeController.addEmployee(employees, scanner);
                    break;
                case 2:
                    EmployeeController.editEmployee(employees, scanner);
                    break;
                case 3:
                    EmployeeController.printEmployeeInfo(employees);
                    break;
                case 4:
                    RoomsController.addNewRoom(rooms, scanner);
                    break;
                case 5:
                    RoomsController.showAllRooms(rooms);
                    break;
                case 6: 
                    RoomsController.editRoom(rooms, scanner);
                    break;
                case 7:
                    System.out.println("Returning to Home Menu...\n");
                    return; 
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
    }
        }
        
        
}



