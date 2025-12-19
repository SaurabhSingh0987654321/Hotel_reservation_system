import java.io.*;
import java.util.*;

public class Hotel {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> bookings = new ArrayList<>();

    // Load rooms from file
    public void loadRooms() throws Exception {
        File file = new File("data/rooms.txt");
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] r = line.split(",");
            rooms.add(new Room(
                    Integer.parseInt(r[0]),
                    r[1],
                    Double.parseDouble(r[2]),
                    Boolean.parseBoolean(r[3])
            ));
        }
        br.close();
    }

    // Save rooms to file
    public void saveRooms() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/rooms.txt"));
        for (Room r : rooms) bw.write(r + "\n");
        bw.close();
    }

    // Load Bookings
    public void loadBookings() throws Exception {
        File file = new File("data/bookings.txt");
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] b = line.split(",");
            bookings.add(new Reservation(b[0], Integer.parseInt(b[1]), b[2], Double.parseDouble(b[3])));
        }
        br.close();
    }

    // Save Bookings
    public void saveBookings() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/bookings.txt"));
        for (Reservation r : bookings) bw.write(r + "\n");
        bw.close();
    }

    // Add room manually
    public void addRoom(Room r) {
        rooms.add(r);
    }

    // Check if rooms list is empty
    public boolean hasRooms() {
        return !rooms.isEmpty();
    }

    // Search Available Rooms by Category
    public void searchRooms(String category) {
        System.out.println("\nAvailable Rooms (" + category + "): ");
        for (Room r : rooms) {
            if (r.getCategory().equalsIgnoreCase(category) && r.isAvailable()) {
                System.out.println("Room " + r.getRoomNumber() + " | Price: " + r.getPrice());
            }
        }
    }

    // Book Room
    public void bookRoom(String name, int roomNumber) throws Exception {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber && r.isAvailable()) {
                // Payment Simulation
                System.out.println("\nProcessing payment...");
                Thread.sleep(1000);
                System.out.println("✔ Payment Successful! Amount Paid: " + r.getPrice());

                r.setAvailable(false);

                bookings.add(new Reservation(name, roomNumber, r.getCategory(), r.getPrice()));

                saveRooms();
                saveBookings();

                System.out.println("✔ Room booked successfully!");
                return;
            }
        }
        System.out.println("❌ Room not available!");
    }

    // Cancel Booking
    public void cancelBooking(String name, int roomNumber) throws Exception {
        Reservation toRemove = null;

        for (Reservation r : bookings) {
            if (r.getCustomerName().equalsIgnoreCase(name) && r.getRoomNumber() == roomNumber) {
                toRemove = r;
                break;
            }
        }

        if (toRemove != null) {
            bookings.remove(toRemove);

            for (Room rm : rooms) {
                if (rm.getRoomNumber() == roomNumber)
                    rm.setAvailable(true);
            }

            saveRooms();
            saveBookings();

            System.out.println("✔ Booking cancelled!");
        } else {
            System.out.println("❌ No booking found!");
        }
    }

    // View all bookings
    public void viewBookings() {
        System.out.println("\n📘 All Bookings:");
        for (Reservation r : bookings) {
            System.out.println(r);
        }
    }
}
