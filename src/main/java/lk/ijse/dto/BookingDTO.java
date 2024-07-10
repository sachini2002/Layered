package lk.ijse.dto;

public class BookingDTO {
    private String bookingId;
    private String roomId;
    private String customerId;
    private String packageId;

    public BookingDTO() {}

    public BookingDTO(String bookingId, String roomId, String customerId, String packageId) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.customerId = customerId;
        this.packageId = packageId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", packageId='" + packageId + '\'' +
                '}';
    }
}
