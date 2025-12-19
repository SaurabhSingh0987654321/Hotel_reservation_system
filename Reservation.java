public class Reservation {
    private String customerName;
    private int roomNumber;
    private String category;
    private double amountPaid;

    public Reservation(String customerName, int roomNumber, String category, double amountPaid) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.amountPaid = amountPaid;
    }

    public String getCustomerName() { return customerName; }
    public int getRoomNumber() { return roomNumber; }

    @Override
    public String toString() {
        return customerName + "," + roomNumber + "," + category + "," + amountPaid;
    }
}
