

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationsController {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void createNewReservation(ArrayList<Customer> customers, ArrayList<Room> rooms, ArrayList<Reservation> reservations, Scanner scanner) {
		RoomsController.showAllRooms(rooms);

		

		//enter customer id
		System.out.printf("Enter Customer id (int): ");
		int CustomerId = scanner.nextInt();
		scanner.nextLine();

		//condition if customer id null
		Customer customer = CustomerController.findCustomerById(customers, CustomerId);
		
		if (customer == null) {
		    System.out.println("Customer ID not found. Please register first.\n");
		    return; // Exit the method if customer ID does not exist
		}
		
		//enter date
		System.out.printf("Enter check-in date (yyyy-MM-dd): ");
		String arrDate = scanner.next();

		//enter date
		System.out.printf("Enter check-out date (yyyy-MM-dd): ");
		String depDate = scanner.next();

		System.out.printf("Enter room ID (int): ");
		int roomId = scanner.nextInt();
		scanner.nextLine();
		LocalDate arrivalDate = LocalDate.parse(arrDate, formatter);
		LocalDate departureDate = LocalDate.parse(depDate, formatter);
		//init customer once
		
		
		Room room = RoomsController.getRoomById(roomId, rooms);
		if (room.isReserved(arrivalDate, departureDate)) {
			System.out.println("This room is reserved!");
			return;
		} else {
			int days = Period.between(arrivalDate, departureDate).getDays();
			double sum = days*room.getPrice();
			//double total = sum - sum*Customer.getDiscount()/100;
			System.out.println("\nTotal before discount = "+sum + "\n");
			//System.out.println("Total after discount = "+total);
			System.out.println("Will you pay now?\n1. Yes\n2. No\n");
			System.out.printf("Choose: ");
			int j = scanner.nextInt();
			scanner.nextLine();
			System.out.println("");
			String status;
			if (j==1) {
				status = "Paid";
			} else {
				status = "Reserved";
			}
			Reservation r = new Reservation(arrivalDate, departureDate, sum, status, customer, room);
			reservations.add(r);
			r.print();
			System.out.println();
		}
	}
	
	public static void showAllReservations(ArrayList<Reservation> reservations, Scanner scanner) {
		for (Reservation r : reservations) {
			System.out.println("\n---------------------------------------");
			System.out.println("id: "+reservations.indexOf(r));
			System.out.println("Arrival Date: " + r.getArrivalDatetoString());
			System.out.println("Departure Date: " + r.getDepartureDatetoString());
			System.out.println("Customer Name: " + r.getCustomer().getName());
			System.out.println("Room id: " + r.getRoom().getID());
			System.out.println("Price: "+ r.getPrice());
			System.out.println("Status: "+r.getStatus());
			System.out.println("---------------------------------------");
		}
	}
	
	public static void getReservationbyCustomerName(ArrayList<Reservation> reservations, Scanner scanner) {
		System.out.println("Enter Customer name: ");
		String CustomerName = scanner.next();
		for (Reservation r : reservations) {
			String name = r.getCustomer().getName();
			if (CustomerName.equals(name)) r.print();
		}
	}
	
	public static void getReservationbyCustomerId(ArrayList<Reservation> reservations, Scanner scanner) {
		System.out.println("Enter Customer id (int): ");
		int CustomerId = scanner.nextInt();
		for (Reservation r : reservations) {
			int id = r.getCustomer().getId();
			if (CustomerId==id) r.print();
		}
	}
	
	public static void editReservation(ArrayList<Customer> customers, ArrayList<Room> rooms, ArrayList<Reservation> reservations, Scanner scanner) {
		System.out.println("Enter rservation id (int): \n-1 to show all reservations ids");
		int id = scanner.nextInt();
		if (id==-1) {
			showAllReservations(reservations, scanner);
			System.out.println("Enter rservation id (int): ");
			id = scanner.nextInt();
		}
		Reservation reservation = reservations.get(id);
		
		System.out.println("Enter arrival date (yyyy-MM-dd): \n-1 to keep it");
		String arrDate = scanner.next();
		if (arrDate.equals("-1")) {
			arrDate = reservation.getArrivalDatetoString();
		}
		
		System.out.println("Enter departure date (yyyy-MM-dd): \n-1 to keep it");
		String depDate = scanner.next();
		if (depDate.equals("-1")) {
			depDate = reservation.getDepartureDatetoString();
		}
		
		System.out.println("Enter room id (int): \n-1 to keep it\n-2 to show all rooms");
		int roomId = scanner.nextInt();
		if (roomId==-1) {
			roomId = reservation.getRoom().getID();
		} else if (roomId==-2) {
			RoomsController.showAllRooms(rooms);
			System.out.println("Enter room id (int): ");
			roomId = scanner.nextInt();
		}
		
		LocalDate arrivalDate = LocalDate.parse(arrDate, formatter);
		LocalDate departureDate = LocalDate.parse(depDate, formatter);
		
		Customer Customer = reservation.getCustomer();
		Room room = RoomsController.getRoomById(roomId, rooms);
		if (room.isReserved(arrivalDate, departureDate)) {
			System.out.println("This room is reserved!");
			return;
		} else {
			int days = Period.between(arrivalDate, departureDate).getDays();
			double sum = days*room.getPrice();
			//double total = sum - sum*Customer.getDiscount()/100;
			System.out.println("Total before discount = "+sum);
			//System.out.println("Total after discount = "+sum);
			System.out.println("Will you pay now?\n1. Yes (print receipt)\n2. No");

			int j = scanner.nextInt();
			String status;
			if (j==1) {
				status = "Paid";
			} else {
				status = "Reserved";
			}
			reservation.setArrivalDate(arrivalDate);
			reservation.setDepartureDate(departureDate);
			reservation.setRoom(room);
			reservation.setStatus(status);
			reservation.setPrice(sum);
			reservations.set(id, reservation);
			reservation.print();
			System.out.println();
		}
	}
	
	public static void payReservation(ArrayList<Reservation> reservations, Scanner scanner) {
		System.out.println("Enter reservation id (int): \n-1 to show all reservations ids");
		int id = scanner.nextInt();
		if (id==-1) {
			showAllReservations(reservations, scanner);
			System.out.println("Enter reservation id (int): ");
			id = scanner.nextInt();
		}
		
		Reservation reservation = reservations.get(id);
		if (reservation.getStatus().equals("Reserved")) {
			reservation.setStatus("Paid");
			System.out.println("Reservation paid successfully!");
		} else {
			System.out.println("This reservation is already paid!");
		}
	}

}
