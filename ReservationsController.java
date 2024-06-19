import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationsController {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void createNewReservation(ArrayList<Customer> customers, ArrayList<Room> rooms, ArrayList<Reservation> reservations, Scanner scanner) {
        RoomsController.showAllRooms(rooms);

        // Enter customer id
        System.out.printf("Enter Customer id (int): ");
        int CustomerId = scanner.nextInt();
        scanner.nextLine();

        // Condition if customer id null
        Customer customer = CustomerController.findCustomerById(customers, CustomerId);

        if (customer == null) {
            System.out.println("Customer ID not found. Please register first.\n");
            return; // Exit the method if customer ID does not exist
        }

        // Enter check-in date with exception handling
        LocalDate arrivalDate = null;
        while (arrivalDate == null) {
            System.out.printf("Enter check-in date (yyyy-MM-dd): ");
            String arrDate = scanner.next();
            try {
                arrivalDate = LocalDate.parse(arrDate, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        // Enter check-out date with exception handling
        LocalDate departureDate = null;
        while (departureDate == null) {
            System.out.printf("Enter check-out date (yyyy-MM-dd): ");
            String depDate = scanner.next();
            try {
                departureDate = LocalDate.parse(depDate, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        // Enter number of rooms to reserve
        System.out.printf("Enter number of rooms to reserve: ");
        int numberOfRooms = scanner.nextInt();
        scanner.nextLine();

        // Loop for each room reservation
        ArrayList<Room> reservedRooms = new ArrayList<>();
        for (int i = 0; i < numberOfRooms; i++) {
            System.out.printf("Enter room ID for room %d: ", i + 1);
            int roomId = scanner.nextInt();
            scanner.nextLine();

            Room room = RoomsController.getRoomById(roomId, rooms);
            if (room.isReserved(arrivalDate, departureDate)) {
                System.out.println("This room is reserved!");
                return;
            } else {
                reservedRooms.add(room);
            }
        }

        // Calculate and display total price for each room
        int days = Period.between(arrivalDate, departureDate).getDays();
        double totalSum = 0;
        for (int i = 0; i < reservedRooms.size(); i++) {
            Room room = reservedRooms.get(i);
            double roomPrice = days * room.getPrice();
            totalSum += roomPrice;
            System.out.printf("Room %d\n", i + 1);
            System.out.printf("Room No: %d\n", room.getID());
            System.out.printf("Price per day: RM %.2f\n", room.getPrice());
            System.out.printf("Total price per room: RM %.2f\n\n", roomPrice);
        }

        System.out.printf("Total price: RM %.2f\n", totalSum);

        System.out.println("Will you pay now?\n1. Yes\n2. No\n");
        System.out.printf("Choose: ");
        int j = scanner.nextInt();
        scanner.nextLine();
        System.out.println("");
        String status;
        String paymentMethodUpdate = "not specified";
        if (j == 1) {
            System.out.println("Payment Method? (default = cash)\n1. Cash\n2. E-Wallet\n3. Credit Card\n");
            System.out.printf("Choose: ");
            int paymentMethodNumber = scanner.nextInt();
            scanner.nextLine();
            switch (paymentMethodNumber) {
                case 1:
                    paymentMethodUpdate = "Cash";
                    break;
                case 2:
                    paymentMethodUpdate = "E-Wallet";
                    break;
                case 3:
                    paymentMethodUpdate = "Credit Card";
                    break;
                default:
                    paymentMethodUpdate = "Cash";
                    break;
            }
            status = "Paid";
        } else {
            status = "Reserved";
        }

        // Create and add reservations
        for (Room room : reservedRooms) {
            Reservation r = new Reservation(arrivalDate, departureDate, days * room.getPrice(), status, customer, room, paymentMethodUpdate);
            reservations.add(r);
            r.print();
            System.out.println();
        }
    }

    public static void showAllReservations(ArrayList<Reservation> reservations, Scanner scanner) {
        for (Reservation r : reservations) {
            System.out.println("\n---------------------------------------");
            System.out.println("id: " + reservations.indexOf(r));
            System.out.println("Arrival Date: " + r.getArrivalDatetoString());
            System.out.println("Departure Date: " + r.getDepartureDatetoString());
            System.out.println("Customer Name: " + r.getCustomer().getName());
            System.out.println("Room id: " + r.getRoom().getID());
            System.out.println("Price: " + r.getPrice());
            System.out.println("Status: " + r.getStatus());
            if (r.getStatus().equals("Paid")) {
                System.out.println("Payment Method: " + r.getPaymentMethod());
            }
            System.out.println("---------------------------------------\n");
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
            if (CustomerId == id) r.print();
        }
    }

    public static void editReservation(ArrayList<Customer> customers, ArrayList<Room> rooms, ArrayList<Reservation> reservations, Scanner scanner) {
		System.out.printf("Enter reservation id (int)(-1 to show all reservations ids): ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		if (id == -1) {
			showAllReservations(reservations, scanner);
			System.out.printf("Enter reservation id (int): ");
			id = scanner.nextInt();
			scanner.nextLine();
			System.out.println();
		}
		Reservation reservation = reservations.get(id);
	
		LocalDate arrivalDate = null;
		while (arrivalDate == null) {
			System.out.printf("Enter arrival date (yyyy-MM-dd)(-1 to keep it): ");
			String arrDate = scanner.next();
			System.out.println();
			if (arrDate.equals("-1")) {
				arrDate = reservation.getArrivalDatetoString();
				try {
					arrivalDate = LocalDate.parse(arrDate, formatter);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date format stored. Please enter the date in yyyy-MM-dd format.");
				}
			} else {
				try {
					arrivalDate = LocalDate.parse(arrDate, formatter);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
				}
			}
		}
	
		LocalDate departureDate = null;
		while (departureDate == null) {
			System.out.printf("Enter departure date (yyyy-MM-dd)(-1 to keep it): ");
			String depDate = scanner.next();
			System.out.println();
			if (depDate.equals("-1")) {
				depDate = reservation.getDepartureDatetoString();
				try {
					departureDate = LocalDate.parse(depDate, formatter);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date format stored. Please enter the date in yyyy-MM-dd format.");
				}
			} else {
				try {
					departureDate = LocalDate.parse(depDate, formatter);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
				}
			}
		}
	
		System.out.printf("Enter room id (int): \n-1 to keep it\n-2 to show all rooms\n\n choose: ");
		int roomId = scanner.nextInt();
		System.out.println();
		if (roomId == -1) {
			roomId = reservation.getRoom().getID();
		} else if (roomId == -2) {
			RoomsController.showAllRooms(rooms);
			System.out.printf("Enter room id (int): ");
			roomId = scanner.nextInt();
			scanner.nextLine();
			System.out.println();
		}
	
		Room room = RoomsController.getRoomById(roomId, rooms);
		if (room.isReserved(arrivalDate, departureDate)) {
			System.out.println("This room is reserved!");
			return;
		} else {
			int days = Period.between(arrivalDate, departureDate).getDays();
			double sum = days * room.getPrice();
			System.out.println("Total price = " + sum + "\n");
			System.out.printf("Will you pay now?\n1. Yes (print receipt)\n2. No\n\n Choose: ");
	
			int j = scanner.nextInt();
			scanner.nextLine();
			System.out.println();
	
			String status;
			if (j == 1) {
				System.out.println("Payment Method? (default = cash)\n1. Cash\n2. E-Wallet\n3. Credit Card\n");
				System.out.printf("Choose: ");
				int paymentMethodNumberEdit = scanner.nextInt();
				scanner.nextLine();
				switch (paymentMethodNumberEdit) {
					case 1:
						reservation.setPaymentMethod("Cash");
						break;
					case 2:
						reservation.setPaymentMethod("E-Wallet");
						break;
					case 3:
						reservation.setPaymentMethod("Credit Card");
						break;
					default:
						reservation.setPaymentMethod("Cash");
						break;
				}
				status = "Paid";
			} else {
				status = "Unpaid,Reserved";
			}
			reservation.setArrivalDate(arrivalDate);
			reservation.setDepartureDate(departureDate);
			reservation.setRoom(room);
			reservation.setStatus(status);
			reservation.setPrice(sum);
			reservations.set(id, reservation);
	
			// Display detailed information for the updated reservation
			System.out.println("\nUpdated Reservation Details:");
			System.out.println("---------------------------------------");
			System.out.printf("Room No: %d\n", room.getID());
			System.out.printf("Price per day: RM %.2f\n", room.getPrice());
			System.out.printf("Total price per room: RM %.2f\n", sum);
			System.out.println("Arrival Date: " + reservation.getArrivalDatetoString());
			System.out.println("Departure Date: " + reservation.getDepartureDatetoString());
			System.out.println("Customer Name: " + reservation.getCustomer().getName());
			System.out.println("Payment Method: " + reservation.getPaymentMethod());
			System.out.println("Status: " + reservation.getStatus());
			System.out.println("---------------------------------------\n");
		}
	}
	

    public static void payReservation(ArrayList<Reservation> reservations, Scanner scanner) {
        System.out.println("Enter reservation id (int): \n-1 to show all reservations ids");
        int id = scanner.nextInt();
        if (id == -1) {
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
