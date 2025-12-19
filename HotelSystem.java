import java.util.*;

public class HotelSystem {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        // Load Data
        hotel.loadRooms();
        hotel.loadBookings();

        // Preload Rooms (run once)
        if (!hotel.hasRooms()) {
            hotel.addRoom(new Room(101, "Standard", 1500, true));
            hotel.addRoom(new Room(102, "Deluxe", 2500, true));
            hotel.addRoom(new Room(103, "Suite", 4000, true));
            hotel.saveRooms();
        }

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter category (Standard/Deluxe/Suite): ");
                    String cat = sc.nextLine();
                    hotel.searchRooms(cat);
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter room number: ");
                    int rn = sc.nextInt();
                    hotel.bookRoom(name, rn);
                    break;

                case 3:
                    System.out.print("Enter customer name: ");
                    String cname = sc.nextLine();
                    System.out.print("Enter room number: ");
                    int crn = sc.nextInt();
                    hotel.cancelBooking(cname, crn);
                    break;

                case 4:
                    hotel.viewBookings();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    System.exit(0);
            }
        }
    }
}
