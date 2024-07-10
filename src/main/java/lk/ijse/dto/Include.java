package lk.ijse.dto;

public class Include {
    private String orderId;
    private String inventoryId;

    public Include() {}

    public Include(String orderId, String inventoryId) {
        this.orderId = orderId;
        this.inventoryId = inventoryId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Override
    public String toString() {
        return "Include{" +
                "orderId='" + orderId + '\'' +
                ", inventoryId='" + inventoryId + '\'' +
                '}';
    }
}
