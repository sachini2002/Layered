package lk.ijse.entity;

public class Room {
    private String roomId;
    private String roomNumber;
    private String roomType;
    private String roomRate;
    private String roomStatus;

    // Constructor with all parameters
    public Room(String roomId, String roomNumber, String roomType, String roomRate, String roomStatus) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomRate = roomRate;
        this.roomStatus = roomStatus;
    }

    // Getters and Setters
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(String roomRate) {
        this.roomRate = roomRate;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    // toString() method
    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                ", roomRate=" + roomRate +
                ", roomStatus='" + roomStatus + '\'' +
                '}';
    }
}
