import java.util.ArrayList;

import java.util.Scanner;

public class CustomerController {
    public static void addCustomer(ArrayList<Customer> customers, Scanner scn) {
        System.out.print("Please enter your name: ");
        String name = scn.nextLine();
        System.out.print("Please enter your email address: ");
        String email = scn.nextLine();

        // Enter phone number with exception handling
        boolean validPhoneNumber = false;
        String phoneNo = "";
        while (!validPhoneNumber) {
            System.out.print("Please enter your phone number: ");
            phoneNo = scn.nextLine();
            if (phoneNo.matches("\\d+")) {
                validPhoneNumber = true;
            } else {
                System.out.println("Invalid input. Please enter digits only.");
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

    public static void searchCustomer(ArrayList<Customer> customers, Scanner scn) {
        System.out.println("Please enter your id number: ");
        int id = scn.nextInt();
        System.out.println();
        boolean customerFound = false;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customer.print();
                System.out.println();
                customerFound = true;
                break;
            }
        }
        if (!customerFound) {
            System.out.println("Customer not found!");
            System.out.println();
        }
    }

    public static void editCustomer(ArrayList<Customer> customers, Scanner scn) {
        System.out.println("Please enter your id number: ");
        int id = scn.nextInt();
        scn.nextLine(); // Consume newline
        System.out.println();
        Customer customer = customers.get(id - 1);

        System.out.println("Enter the new name:  (enter 'no' to keep it)");
        String name = scn.nextLine();
        if (name.equals("no")) {
            name = customer.getName();
        }

        System.out.println("Enter your email address:  (enter 'no' to keep it)");
        String email = scn.nextLine();
        if (email.equals("no")) {
            email = customer.getEmail();
        }

        // Enter phone number with exception handling
        boolean validPhoneNumber = false;
        String phoneNo = "";
        while (!validPhoneNumber) {
            System.out.println("Enter your phone number:  (enter 'no' to keep it)");
            phoneNo = scn.nextLine();
            if (phoneNo.equals("no")) {
                phoneNo = customer.getPhone();
                validPhoneNumber = true;
            } else if (phoneNo.matches("\\d+")) {
                validPhoneNumber = true;
            } else {
                System.out.println("Invalid input. Please enter digits only.");
            }
        }

        customer.setName(name);
        customer.setEmail(email);
        customer.setphoneNo(phoneNo);
    }

    public static Customer findCustomerById(ArrayList<Customer> customers, int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}
