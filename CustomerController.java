import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController {
    public static void addCustomer(ArrayList<Customer> customers,Scanner scn)
    {
        System.out.print("Please enter your name: ");
        String name = scn.next();
        System.out.print("Please enter your email address: ");
        String email = scn.next();
        System.out.print("Please enter your phone number: ");
        String phoneNo = scn.next();

        Customer customer = new Customer(email, phoneNo);
        customer.setId(customers.size()+1);
        customer.setName(name);
        customers.add(customer);
        System.out.println("\nBelow is your information: ");
        System.out.println("---------------------------");
        customer.print();
        System.out.println("");
    }

    public static void printCustomerInfo(ArrayList<Customer> customers)
    {
        System.out.println("Customer information");
        for(Customer customer:customers)
        {
            System.out.println("--------------------------------");
			customer.print();
			System.out.println("--------------------------------");
			System.out.println();
        }
    }
    public static void searchCustomer(ArrayList<Customer> customers,Scanner scn)
    {
        System.out.println("Please enter your id number: ");
        int id = scn.nextInt();
        System.out.println();
        for(Customer customer: customers)
        {

            if(customer.getId() == id)
            {
                customer.print();
                System.out.println();
            }
            else
            {
                System.out.println("Customer not found!");
                System.out.println();
            }
        }
    }

    public static void editCustomer(ArrayList<Customer> customers,Scanner scn)
    {
        System.out.println("Please enter your id number: ");
        int id = scn.nextInt();
        System.out.println();
        Customer customer = customers.get(id-1);

        System.out.println("Enter the new name:  (enter 'no' to keep it)");
        String name = scn.next();
        if(name.equals("no"))
        {
            name = customer.getName();
        }

        System.out.println("Enter your email address:  (enter 'no' to keep it)");
		String email = scn.next();
        if(email.equals("no"))
        {
            email = customer.getEmail();
        }

        System.out.println("Enter your phone number : \n (enter 'no' to keep it)");
        String phoneNo = scn.next();
        if(phoneNo.equals("no"))
        {
            phoneNo = customer.getPhone();
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
