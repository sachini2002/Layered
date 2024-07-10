package lk.ijse.dto;

public class Inventory {
    private String inventoryId;
    private String category;
    private String qty;
    private String price;

    public Inventory() {}

    public Inventory(String inventoryId, String category, String qty, String price) {
        this.inventoryId = inventoryId;
        this.category = category;
        this.qty = qty;
        this.price = price;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId='" + inventoryId + '\'' +
                ", category='" + category + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
