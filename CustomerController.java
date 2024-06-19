import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController implements Searchable {
    private static ArrayList<Customer> customers = new ArrayList<>();

    public static void addCustomer(ArrayList<Customer> customers,Scanner scn) {
        System.out.print("Please enter your name: ");
        String name = scn.nextLine();
        System.out.print("Please enter your email address: ");
        String email = scn.nextLine();

        String phoneNo = "";
        while (true) {
            try {
                System.out.print("Please enter your phone number: ");
                phoneNo = scn.nextLine();
                validatePhoneNumber(phoneNo);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Customer customer = new Customer(email, phoneNo);
        customer.setId(customers.size() + 1);
        customer.setName(name);
        customers.add(customer);
        System.out.println("\nBelow is your information: ");
        System.out.println("---------------------------");
        customer.print();
        System.out.println("");
    }

    public static void printCustomerInfo(ArrayList<Customer> customers) {
        System.out.println("Customer information");
        for (Customer customer : customers) {
            System.out.println("--------------------------------");
            customer.print();
            System.out.println("--------------------------------");
            System.out.println();
        }
    }

    @Override
    public void search(int id) {
        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customer.print();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Customer not found!");
        }
    }

    public static void editCustomer(Scanner scn) {
        System.out.print("Please enter your id number: ");
        int id = scn.nextInt();
        scn.nextLine();
        System.out.println();

        Customer customer = customers.get(id - 1);

        System.out.print("Enter the new name: (enter 'no' to keep it): ");
        String name = scn.nextLine();
        if (name.equals("no")) {
            name = customer.getName();
        }

        System.out.print("Enter your email address: (enter 'no' to keep it): ");
        String email = scn.nextLine();
        if (email.equals("no")) {
            email = customer.getEmail();
        }

        boolean validPhoneNumber = false;
        String phoneNo = "";
        while (!validPhoneNumber) {
            System.out.print("Enter your phone number: (enter 'no' to keep it): ");
            phoneNo = scn.nextLine();
            if (phoneNo.equals("no")) {
                phoneNo = customer.getPhone();
                validPhoneNumber = true;
            } else {
                try {
                    validatePhoneNumber(phoneNo);
                    validPhoneNumber = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        customer.setName(name);
        customer.setEmail(email);
        customer.setphoneNo(phoneNo);
    }

    public static Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public static void validatePhoneNumber(String phoneNo) {
        if (!phoneNo.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid input. Please enter digits only.");
        }
    }
}
