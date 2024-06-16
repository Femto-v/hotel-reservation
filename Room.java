import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Room {
    private int id;
    
    private int capacity;
    private String type;
    
    private double price;
    private ArrayList<String> reservedDates;

    public Room(int id,  int capacity, String type,  double price) {
        this.id = id;
        
        this.capacity = capacity;
        this.type = type;
        
        this.price = price;
        this.reservedDates = new ArrayList<>();
    }
    public Room() {
		reservedDates = new ArrayList<>();
	}
    public void setID(int id) {
        this.id = id;
    }

    

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    

    public void setPrice(double price) {
        this.price = price;
    }

    public int getID() {
        return id;
    }

    

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    

    public double getPrice() {
        return price;
    }

    public void Reserve(LocalDate startDate, LocalDate finishDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for (LocalDate date=startDate;date.isBefore(finishDate);date=date.plusDays(1)) {
			String d = date.format(formatter);
			reservedDates.add(d);
		}
	}
    public boolean isReserved(LocalDate startDate, LocalDate finishDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (LocalDate date = startDate; date.isBefore(finishDate); date = date.plusDays(1)) {
        String formattedDate = date.format(formatter);
        if (reservedDates.contains(formattedDate)) {
            return true; // Room is reserved on this date
            }
        }
        return false; // Room is not reserved for any date in the range
	}
    public void print() {
		System.out.println("id: "+id);
		System.out.println("Capacity: "+capacity);
		System.out.println("Type: "+type);
		System.out.println("Price: "+price);
	}
    
}
