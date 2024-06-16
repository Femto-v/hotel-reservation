import java.util.ArrayList;
import java.util.Scanner;

public class RoomsController {

    public static ArrayList<Room> defaultRoomMenu() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1001, 1, "Single Room", 80));
        rooms.add(new Room(1002, 2, "Double Room", 100));
        rooms.add(new Room(1003, 3, "Family Room", 200));
        rooms.add(new Room(1004, 4, "Studio Room", 300));
        return rooms;
    }

    public static void addNewRoom(ArrayList<Room> rooms, Scanner scanner) {
        System.out.printf("Enter capacity (int): ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.printf("Enter type (String): ");
        String type = scanner.nextLine();

        System.out.printf("Enter price (double): ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        int id = 1000 + rooms.size() + 1; // Ensuring unique IDs for new rooms
        Room room = new Room(id, capacity, type, price);
        rooms.add(room);

        System.out.println("\nRoom added successfully!");
        System.out.println();
    }

    public static void showAllRooms(ArrayList<Room> rooms) {
        for (Room room : rooms) {
            System.out.println("--------------------------------");
            room.print();
            System.out.println("--------------------------------");
            System.out.println();
        }
    }

    public static void editRoom(ArrayList<Room> rooms, Scanner scanner) {
        System.out.printf("Enter room id (-1 to show all rooms): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (id == -1) {
            showAllRooms(rooms);
            System.out.printf("Enter room id: ");
            id = scanner.nextInt();
            scanner.nextLine();
        }
        Room room = getRoomById(id, rooms);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.printf("Enter capacity (int)(-1 to keep it): ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        if (capacity == -1) capacity = room.getCapacity();

        System.out.printf("Enter room type (String)(-1 to keep it): ");
        String type = scanner.nextLine();
        if (type.equals("-1")) type = room.getType();

        System.out.printf("Enter room price (double)(-1 to keep it): ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if (price == -1) price = room.getPrice();

        room.setCapacity(capacity);
        room.setType(type);
        room.setPrice(price);

        System.out.println("\nRoom updated successfully!\n");
    }

    public static Room getRoomById(int id, ArrayList<Room> rooms) {
		Room room = new Room();
		for (Room r : rooms) {
			if (r.getID()==id) {
				room = r;
				break;
			}
		}
		return room;
	}

}
